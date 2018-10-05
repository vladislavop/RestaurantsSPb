package com.nixbyte.project.activities.updateuserdata;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.nixbyte.project.R;
import com.nixbyte.project.model.userpojo.User;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

public class UpdateActivity extends AbstractActivity<UpdateView> {


    EditText ed_email, ed_password, ed_name, ed_surname;

    @Override
    public void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_update);
        super.onCreate(savedInstanceState);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_name = findViewById(R.id.ed_name);
        ed_surname = findViewById(R.id.ed_surname);

        User userAuth;
        view.onAttach(this);
        SharedPreferences preferences = getSharedPreferences("authorization", MODE_PRIVATE);
        String userObject = preferences.getString("userdata", "");
        userAuth = new Gson().fromJson(userObject, User.class);
        ed_email.setText(userAuth.email);
        ed_password.setText(userAuth.password);
        ed_name.setText(userAuth.name);
        ed_surname.setText(userAuth.surname);
        findViewById(R.id.update_button).setOnClickListener((onview)->{
            User user = new User(userAuth.id,
                    ed_name.getText().toString(), ed_surname.getText().toString(),
                    ed_email.getText().toString(), ed_password.getText().toString());
            ((UpdatePresenter)presenter).updateUser(user);
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return view.actionBar.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return  view.actionBar.onOptionsItemSelected(menuItem);
    }


    @Override
    protected Presenter initPresenter() {
        return new UpdatePresenter();
    }

    @Override
    protected UpdateView initView() {
        return new UpdateView(this, findViewById(android.R.id.content));
    }
}
