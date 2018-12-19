package com.example.administrator.xingyi.getStars.stepCount;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.StepNumDAO;
import com.example.administrator.xingyi.dao.StepNumDetailsDAO;
import com.example.administrator.xingyi.model.StepNum;
import com.example.administrator.xingyi.model.StepNumDetails;
import com.example.administrator.xingyi.util.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StepService extends Service implements SensorEventListener {
    private SharedPreferencesUtils sp;
    private StepNum stepNum;
    private StepNumDAO stepNumDAO;
    private List<StepNumDetails> stepNumDetailsList;
    private StepNumDetailsDAO stepNumDetailsDAO;
    private int userId;
    private String date;
    private String TAG = "StepService";
    /**
     * 默认为5秒进行一次存储
     */
    private static int duration = 5 * 1000;
    /**
     * 当前的日期
     */
    private static String CURRENT_DATE = "";
    /**
     * 传感器管理对象
     */
    private SensorManager sensorManager;
    /**
     * 广播接受者
     */
    private BroadcastReceiver mBatInfoReceiver;
    /**
     * 保存记步计时器
     */
    private TimeCount time;
    /**
     * 当前所走的步数
     */
    private int CURRENT_STEP;
    /**
     * 计步传感器类型  Sensor.TYPE_STEP_COUNTER或者Sensor.TYPE_STEP_DETECTOR
     */
    private static int stepSensorType = -1;
    /**
     * 每次第一次启动记步服务时是否从系统中获取了已有的步数记录
     */
    private boolean hasRecord = false;
    /**
     * 系统中获取到的已有的步数
     */
    private int hasStepCount = 0;
    /**
     * 上一次的步数
     */
    private int previousStepCount = 0;
    /**
     * 通知管理对象
     */
    private NotificationManager mNotificationManager;
    /**
     * IBinder对象，向Activity传递数据的桥梁
     */
    private StepBinder stepBinder = new StepBinder();
    /**
     * 记步Notification的ID
     */
    int notifyId_Step = 100;
    /**
     * 通知构建者
     */
    private Notification.Builder mBuilder;

    /**
     * UI监听器对象
     */
    private UpdateUiCallBack mCallback;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = new SharedPreferencesUtils(this);
        userId = (int) sp.getParam("user_id",0);
        date = getTodayDate();
        stepNumDAO = new StepNumDAO(this);
        stepNum = stepNumDAO.query(userId,date);
        stepNumDetailsDAO = new StepNumDetailsDAO(this);
        stepNumDetailsList = stepNumDetailsDAO.getScrollData(stepNum.get_id(),date);
        initNotification();
        initTodayData();
        initBroadcastReceiver();
        new Thread(new Runnable() {
            public void run() {
                startStepDetector();
            }
        }).start();
        startTimeCount();
    }

    /**
     * 注册UI更新监听
     *
     * @param paramICallback
     */

    public void registerCallback(UpdateUiCallBack paramICallback) {
        this.mCallback = paramICallback;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stepBinder;
    }

    /**
     * 向Activity传递数据的纽带
     */
    public class StepBinder extends Binder {

        /**
         * 获取当前service对象
         *
         * @return StepService
         */
        public StepService getService() {
            return StepService.this;
        }
    }
    /**
     * 获取当前步数
     *
     * @return
     */
    public int getStepCount() {
        return CURRENT_STEP;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    /**
     * 获取当天日期
     *
     * @return
     */
    private String getTodayDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 初始化通知栏
     */
    private void initNotification() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String id = String.valueOf(notifyId_Step);
            String name = "channel_1";
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(mChannel);
            mBuilder = new Notification.Builder(this,id);
        }else {
            mBuilder = new Notification.Builder(this);
        }

        mBuilder.setContentTitle("星益")
                .setContentText("今日步数" + CURRENT_STEP + " 步")
                .setContentIntent(getDefalutIntent(Notification.FLAG_ONGOING_EVENT))
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setAutoCancel(false)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(true)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setSmallIcon(R.drawable.ic_logo);
        Notification notification = mBuilder.build();
        startForeground(notifyId_Step, notification);
    }
    /**
     * 初始化当天的步数
     */
    private void initTodayData() {
        CURRENT_STEP = stepNum.getStepNums();
        updateNotification();
    }
    /**
     * 注册广播
     */
    private void initBroadcastReceiver() {
        final IntentFilter filter = new IntentFilter();
        // 屏幕灭屏广播
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        //关机广播
        filter.addAction(Intent.ACTION_SHUTDOWN);
        // 屏幕亮屏广播
        filter.addAction(Intent.ACTION_SCREEN_ON);
        // 屏幕解锁广播
        filter.addAction(Intent.ACTION_USER_PRESENT);
        // 当长按电源键弹出“关机”对话或者锁屏时系统会发出这个广播
        // example：有时候会用到系统对话框，权限可能很高，会覆盖在锁屏界面或者“关机”对话框之上，
        // 所以监听这个广播，当收到时就隐藏自己的对话，如点击pad右下角部分弹出的对话框
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        //监听日期变化
        filter.addAction(Intent.ACTION_DATE_CHANGED);
        filter.addAction(Intent.ACTION_TIME_CHANGED);
        filter.addAction(Intent.ACTION_TIME_TICK);

        mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                String action = intent.getAction();
                if (Intent.ACTION_SCREEN_ON.equals(action)) {
                    Log.d(TAG, "screen on");
                } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                    Log.d(TAG, "screen off");
                    //改为30秒一存储
                    duration = 30000;
                } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                    Log.d(TAG, "screen unlock");
                    save();
                    //改为5秒一存储
                    duration = 5000;
                } else if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(intent.getAction())) {
                    Log.i(TAG, " receive Intent.ACTION_CLOSE_SYSTEM_DIALOGS");
                    //保存一次
                    save();
                } else if (Intent.ACTION_SHUTDOWN.equals(intent.getAction())) {
                    Log.i(TAG, " receive ACTION_SHUTDOWN");
                    save();
                } else if (Intent.ACTION_DATE_CHANGED.equals(action)) {//日期变化步数重置为0
//                    Logger.d("重置步数" + StepDcretor.CURRENT_STEP);
                    save();
                    isNewDay();
                } else if (Intent.ACTION_TIME_CHANGED.equals(action)) {
                    //时间变化步数重置为0

                    save();
                    isNewDay();
                } else if (Intent.ACTION_TIME_TICK.equals(action)) {//日期变化步数重置为0

//                    Logger.d("重置步数" + StepDcretor.CURRENT_STEP);
                    save();
                    isNewDay();
                }
            }
        };
        registerReceiver(mBatInfoReceiver, filter);
    }
    /**
     * 监听晚上0点变化初始化数据
     */
    private void isNewDay() {
        String time = "00:00";
        if (time.equals(new SimpleDateFormat("HH:mm").format(new Date())) || !CURRENT_DATE.equals(getTodayDate())) {
            initTodayData();
        }
    }
    /**
     * 开始保存记步数据
     */
    private void startTimeCount() {
        if (time == null) {
            time = new TimeCount(duration, 1000);
        }
        time.start();
    }
    /**
     * 保存记步数据
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            // 如果计时器正常结束，则开始计步
            time.cancel();
            save();
            startTimeCount();
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }
    }
    /**
     * @获取默认的pendingIntent,为了防止2.3及以下版本报错
     * @flags属性: 在顶部常驻:Notification.FLAG_ONGOING_EVENT
     * 点击去除： Notification.FLAG_AUTO_CANCEL
     */
    public PendingIntent getDefalutIntent(int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(), flags);
        return pendingIntent;
    }
    /**
     * 更新步数通知
     */
    private void updateNotification() {
        //设置点击跳转
        Intent hangIntent = new Intent(this, StepCountActivity.class);
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = mBuilder.setContentTitle("星益")
                .setContentText("今日步数" + CURRENT_STEP + " 步")
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setContentIntent(hangPendingIntent)
                .build();
        mNotificationManager.notify(notifyId_Step, notification);
        if (mCallback != null) {
            mCallback.updateUi(CURRENT_STEP);
        }
    }

    /**
     * 获取传感器实例
     */
    private void startStepDetector() {
        if (sensorManager != null) {
            sensorManager = null;
        }
        // 获取传感器管理器的实例
        sensorManager = (SensorManager) this
                .getSystemService(SENSOR_SERVICE);
        //android4.4以后可以使用计步传感器
            addCountStepListener();
    }
    /**
     * 添加传感器监听
     * 1. TYPE_STEP_COUNTER API的解释说返回从开机被激活后统计的步数，当重启手机后该数据归零，
     * 该传感器是一个硬件传感器所以它是低功耗的。
     * 为了能持续的计步，请不要反注册事件，就算手机处于休眠状态它依然会计步。
     * 当激活的时候依然会上报步数。该sensor适合在长时间的计步需求。
     * <p>
     * 2.TYPE_STEP_DETECTOR翻译过来就是走路检测，
     * API文档也确实是这样说的，该sensor只用来监监测走步，每次返回数字1.0。
     * 如果需要长事件的计步请使用TYPE_STEP_COUNTER。
     */
    private void addCountStepListener() {
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (countSensor != null) {
            stepSensorType = Sensor.TYPE_STEP_COUNTER;
            Log.v(TAG, "Sensor.TYPE_STEP_COUNTER");
            sensorManager.registerListener(StepService.this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else if (detectorSensor != null) {
            stepSensorType = Sensor.TYPE_STEP_DETECTOR;
            Log.v(TAG, "Sensor.TYPE_STEP_DETECTOR");
            sensorManager.registerListener(StepService.this, detectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    /**
     * 传感器监听回调
     * 记步的关键代码
     * 1. TYPE_STEP_COUNTER API的解释说返回从开机被激活后统计的步数，当重启手机后该数据归零，
     * 该传感器是一个硬件传感器所以它是低功耗的。
     * 为了能持续的计步，请不要反注册事件，就算手机处于休眠状态它依然会计步。
     * 当激活的时候依然会上报步数。该sensor适合在长时间的计步需求。
     * <p>
     * 2.TYPE_STEP_DETECTOR翻译过来就是走路检测，
     * API文档也确实是这样说的，该sensor只用来监监测走步，每次返回数字1.0。
     * 如果需要长事件的计步请使用TYPE_STEP_COUNTER。
     *
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (stepSensorType == Sensor.TYPE_STEP_COUNTER) {
            //获取当前传感器返回的临时步数
            int tempStep = (int) event.values[0];
            //首次如果没有获取手机系统中已有的步数则获取一次系统中APP还未开始记步的步数
            if (!hasRecord) {
                hasRecord = true;
                hasStepCount = tempStep;
            } else {
                //获取APP打开到现在的总步数=本次系统回调的总步数-APP打开之前已有的步数
                int thisStepCount = tempStep - hasStepCount;
                //本次有效步数=（APP打开后所记录的总步数-上一次APP打开后所记录的总步数）
                int thisStep = thisStepCount - previousStepCount;
                //总步数=现有的步数+本次有效步数
                CURRENT_STEP += (thisStep);
                //记录最后一次APP打开到现在的总步数
                previousStepCount = thisStepCount;
            }
        } else if (stepSensorType == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0) {
                CURRENT_STEP++;
            }
        }
        updateNotification();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    /**
     * 保存记步数据
     */
    private void save() {
        int tempStep = CURRENT_STEP;
        int blockStep = 0;
        stepNum.setStepNums(tempStep);
        stepNumDAO.update(stepNum);
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        for (int a = 0;a < hour;a++){
            blockStep = blockStep + stepNumDetailsList.get(a).getStepNum();
        }
        stepNumDetailsList.get(hour).setStepNum(CURRENT_STEP - blockStep);
        stepNumDetailsDAO.update(stepNumDetailsList.get(hour));
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消前台进程
        stopForeground(true);
        unregisterReceiver(mBatInfoReceiver);
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
