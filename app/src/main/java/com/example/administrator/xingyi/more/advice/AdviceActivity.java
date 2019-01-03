package com.example.administrator.xingyi.more.advice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.xingyi.R;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class AdviceActivity extends AppCompatActivity {

    private TitleBar titleBar;
    private EditText etAdvice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        titleBar = (TitleBar)findViewById(R.id.title_advice);
        etAdvice = (EditText)findViewById(R.id.et_advice);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
        titleBar.getRightView().setEnabled(false);
        etAdvice.addTextChangedListener(new TextWatcher() {

            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(temp.length() > 0){
                    titleBar.setRightColor(getResources().getColor(R.color.tab_checked,null));
                    titleBar.getRightView().setEnabled(true);
                    titleBar.setOnTitleBarListener(new OnTitleBarListener() {
                        @Override
                        public void onLeftClick(View v) {
                            finish();
                        }

                        @Override
                        public void onTitleClick(View v) {

                        }

                        @Override
                        public void onRightClick(View v) {
                            Toast.makeText(AdviceActivity.this,"提交成功！",Toast.LENGTH_SHORT).show();
                            etAdvice.setText("");
                            titleBar.getRightView().setEnabled(false);
                            titleBar.setRightColor(getResources().getColor(R.color.gray,null));
                        }
                    });
                }else {
                    titleBar.setRightColor(getResources().getColor(R.color.gray,null));
                    titleBar.getRightView().setEnabled(false);
                }
            }
        });

    }
}
