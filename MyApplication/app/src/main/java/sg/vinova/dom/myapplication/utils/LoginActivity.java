package sg.vinova.dom.myapplication.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sg.vinova.dom.myapplication.MyCustomView.MyCustomView;
import sg.vinova.dom.myapplication.loginFeature.Login;
import sg.vinova.dom.myapplication.loginFeature.LoginPresenterImpl;
import sg.vinova.dom.myapplication.R;

public class LoginActivity extends AppCompatActivity implements Login.View {

    private LoginPresenterImpl loginPresenter;
    MyCustomView myCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenterImpl(this);
        findViewById(R.id.ivLogin).startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink));

        myCustomView = (MyCustomView) findViewById(R.id.mcvUsername);
        myCustomView.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.logIn(myCustomView.getUsername(), myCustomView.getPassword());
                Log.i("Login Activity", "Log in.");
            }
        });
        myCustomView.btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginSuccess("Guest");
            }
        });
    }

    @Override
    public void onLoginSuccess(String username) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("welcome", "Hello " + username);
        startActivity(intent);


        finish();
    }

    @Override
    public void onLoginFail(String message) {
        myCustomView.setMessage(message);
    }
}