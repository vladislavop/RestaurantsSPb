package com.nixbyte.project.activities.Authorization;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.Authorization.registration.RegistrationActivity;
import com.nixbyte.project.activities.main.MainActivity;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

public class LoginActivity extends AbstractActivity<LoginView> {

    EditText login, password;
    LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        login = (EditText)findViewById(R.id.ed_login);
        findViewById(R.id.register_button).setOnClickListener(view->{
            Intent intent = new Intent(this, RegistrationActivity.class);
            this.startActivity(intent);
        });
        password = findViewById(R.id.ed_password);
        presenter = (LoginPresenter)initPresenter();
        presenter.onViewAttached(initView());
        if(userEntered()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        findViewById(R.id.enter_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getUser(login.getText().toString(), password.getText().toString());
            }
        });

        findViewById(R.id.vk_login_button).setOnClickListener(view->{
            String[] scope = new String[]{VKScope.EMAIL};
            VKSdk.login(this,scope);
            VKSdk.logout();
        });


    }

    @Override
    protected Presenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected LoginView initView() {
        return new LoginView(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
