package sg.vinova.dom.myapplication.utils;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sg.vinova.dom.myapplication.MyCustomView.MyCustomView;
import sg.vinova.dom.myapplication.loadImageFeature.LoadImage;
import sg.vinova.dom.myapplication.loginFeature.Login;
import sg.vinova.dom.myapplication.loginFeature.LoginPresenterImpl;
import sg.vinova.dom.myapplication.R;

public class LoginActivity extends AppCompatActivity implements Login.View, View.OnClickListener {

    private LoginPresenterImpl loginPresenter;
    MyCustomView myCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenterImpl(this);
        findViewById(R.id.ivLogin).startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink));

        myCustomView = (MyCustomView) findViewById(R.id.mcvUsername);
        myCustomView.btnSignup.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {

//        int x = fab.getLeft() + ((fab.getRight() - fab.getLeft()) / 2);
//        int y = fab.getTop() + ((fab.getBottom() - fab.getTop()) / 2);
//
//        int hypotenuse = (int) Math.hypot(ivBg.getWidth(), ivBg.getHeight());
//
//        if (!flag) {
//
//            Animator anim = ViewAnimationUtils.createCircularReveal(ivReveal, x, y, 0, hypotenuse);
//            anim.setDuration(700);
//
//            ivReveal.setVisibility(View.VISIBLE);
//            anim.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animator) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animator) {
//                    layoutButtons.setVisibility(View.VISIBLE);
//                    layoutButtons.startAnimation(alphaAnimation);
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animator) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animator) {
//
//                }
//            });
//            anim.start();
//        } else {
//            Animator anim = ViewAnimationUtils.createCircularReveal(ivReveal, x, y, hypotenuse, 0);
//            anim.setDuration(400);
//            anim.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animator) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animator) {
//                    ivReveal.setVisibility(View.GONE);
//                    layoutButtons.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animator) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animator) {
//
//                }
//            });
//            anim.start();
//        }
//        flag = !flag;

    }
}