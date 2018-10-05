package com.nixbyte.project.activities.Authorization.registration;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.main.MainPresenter;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

public class RegistrationActivity extends AbstractActivity<RegistrationView> {

    Button regist_button;
    EditText ed_email, ed_password, ed_name, ed_surname;
    RegistrationPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_name = findViewById(R.id.ed_name);
        ed_surname = findViewById(R.id.ed_surname);
        view.onAttach(null);
        presenter = (RegistrationPresenter) initPresenter();
        presenter.onViewAttached(initView());
        findViewById(R.id.registrate_button).setOnClickListener(view -> {
            presenter.registUser(ed_email.getText().toString(), ed_password.getText().toString(),
                    ed_name.getText().toString(), ed_surname.getText().toString());
        });

    }

    @Override
    protected Presenter initPresenter() {
        return new RegistrationPresenter();
    }

    @Override
    protected RegistrationView initView() {
        return new RegistrationView(this,findViewById(android.R.id.content));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return view.actionBar.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return  view.actionBar.onOptionsItemSelected(menuItem);
    }
}
