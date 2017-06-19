package sg.vinova.dom.example_mvp_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sg.vinova.dom.example_mvp_login.model.Account;
import sg.vinova.dom.example_mvp_login.module.LoginFeature;
import sg.vinova.dom.example_mvp_login.module.LoginPresenterImpl;

public class MainActivity extends AppCompatActivity implements LoginFeature.View {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private LoginPresenterImpl presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        presenter = new LoginPresenterImpl(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkValidate(edtUsername.getText().toString(), edtPassword.getText().toString());
            }
        });

    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFail(String message) {
        if (message.equals("Username"))
            edtUsername.setError("Username error");
        if (message.equals("Password"))
            edtPassword.setError("Password error");
        if (message.equals("Wrong"))
            Toast.makeText(this, "Wrong username or wrong password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkDataConstraint(Account account) {
        presenter.login(account);
    }
}