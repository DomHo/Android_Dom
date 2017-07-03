package sg.vinova.dom.example_mvp_login.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
    private LoginPresenterImpl presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = MyApplication.presenter;
        presenter.setLoginView(this);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        cbSave = (CheckBox) findViewById(R.id.cbSave);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkValidate(edtUsername.getText().toString(), edtPassword.getText().toString(), cbSave.isChecked());
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                presenter.signup(edtUsername.getText().toString(), edtPassword.getText().toString(), cbSave.isChecked());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        edtPassword.getText().clear();
    }

    @Override
    public void onLoginSuccess(Account account) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("account", account);
        startActivity(intent);
    }

    @Override
    public void onLoginFail(String message) {
        if (message.equals("username empty"))
            edtUsername.setError("Username empty");
        if (message.equals(" "))
            edtUsername.setError("Username contains space");
        if (message.equals(";"))
            edtUsername.setError("Username contains ;");
        if (message.equals("password empty"))
            edtPassword.setError("Password empty");
        if (message.equals("Wrong"))
            edtPassword.setError("Wrong password");
        if (message.equals("NonExist")) {
            edtUsername.setError("Account doesn't exist");
            edtPassword.getText().clear();
        }
    }

    @Override
    public void logout(Account account) {
        if (cbSave.isChecked()) {
            edtUsername.setText(account.getUsername());
            edtPassword.setText(account.getPassword());
        }
    }

//    public void saveImage(Context context, Bitmap b, String imageName) {
//        FileOutputStream foStream;
//        try {
//            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
//            b.compress(Bitmap.CompressFormat.PNG, 100, foStream);
//            foStream.close();
//        } catch (Exception e) {
//            Log.d("saveImage", "Exception 2, Something went wrong!");
//            e.printStackTrace();
//        }
//    }

}