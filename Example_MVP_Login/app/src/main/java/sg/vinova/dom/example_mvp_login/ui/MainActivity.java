package sg.vinova.dom.example_mvp_login.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import sg.vinova.dom.example_mvp_login.R;
import sg.vinova.dom.example_mvp_login.model.Account;
import sg.vinova.dom.example_mvp_login.module.LoginFeature;
import sg.vinova.dom.example_mvp_login.module.LoginPresenterImpl;

public class MainActivity extends AppCompatActivity implements LoginFeature.View {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSignin;
    private Button btnSignup;
    private CheckBox cbSave;
    public static Context mainContext;
    public static LoginPresenterImpl presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        cbSave = (CheckBox) findViewById(R.id.cbSave);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        mainContext = getApplicationContext();
        presenter = new LoginPresenterImpl(this);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkValidate(edtUsername.getText().toString(), edtPassword.getText().toString());
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signup(edtUsername.getText().toString(), edtPassword.getText().toString());
            }
        });

        SharedPreferences pre = getSharedPreferences("my_data", MODE_PRIVATE);
        SharedPreferences.Editor edit = pre.edit();
        for (int i = 0; i < 10; i++) {
            edit.putString("username" + i, "g" + i);
            edit.putString("password" + i, "g" + i);
            edit.putBoolean("save" + i, false);
        }
        edit.putInt("size", 10);
        edit.apply();

    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("user", edtUsername.getText().toString());
        startActivity(intent);
        edtPassword.getText().clear();
    }

    @Override
    public void onLoginFail(String message) {
        if (message.equals("Username"))
            edtUsername.setError("Username error");
        if (message.equals("Password"))
            edtPassword.setError("Password error");
        if (message.equals("Wrong"))
            edtPassword.setError("Wrong password");
        if (message.equals("NonExist")) {
            edtUsername.setError("Account doesn't exist");
            edtPassword.getText().clear();
        }
    }

    @Override
    public void checkDataConstraint(Account account) {
        presenter.login(account, cbSave.isChecked());
    }

    @Override
    public void logout(Account account) {
        if (cbSave.isChecked())
            edtUsername.setText(account.getUsername());
        edtPassword.setText(account.getPassword());
    }
}