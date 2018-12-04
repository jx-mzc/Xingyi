package com.example.administrator.xingyi.more;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.login.LoginActivity;
import com.example.administrator.xingyi.more.person.PersonActivity;
import com.hjq.bar.TitleBar;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/27 0027
 * Author:  Infinity
 */
public class MoreFragment extends Fragment {

    private View view;
    private MoreRecyclerView moreRecyclerView;
    private RelativeLayout personRelativeLayout;
    private RoundImageView touxiang;
    private TextView userName;
    private SharedPreferences pref;
    private String account;
    private Boolean logining;
    private Boolean autuLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more,null);
        moreRecyclerView = new MoreRecyclerView(view,getContext());
        personRelativeLayout = view.findViewById(R.id.rl_person);
        touxiang = view.findViewById(R.id.person_round_img);
        userName = view.findViewById(R.id.tv_more_username);
        pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        moreRecyclerView.setLayoutManager();
        account = pref.getString("user_name","");
        logining = pref.getBoolean("logining",false);
        autuLogin = pref.getBoolean("auto_login",false);
        if (autuLogin){
            logining = true;
        }
        if (logining){
            touxiang.setImageResource(R.drawable.touxiang);
            userName.setText(account);
            personRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), PersonActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            personRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        return view;
    }

}
