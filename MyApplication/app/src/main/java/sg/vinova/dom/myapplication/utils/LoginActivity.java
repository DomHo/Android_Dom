package sg.vinova.dom.myapplication.utils;

import android.animation.Animator;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import sg.vinova.dom.myapplication.MyCustomView.MyCustomView;
import sg.vinova.dom.myapplication.loginFeature.Login;
import sg.vinova.dom.myapplication.loginFeature.LoginPresenterImpl;
import sg.vinova.dom.myapplication.R;

public class LoginActivity extends AppCompatActivity implements Login.View, View.OnClickListener {

    private LoginPresenterImpl loginPresenter;

    private ConstraintLayout clLogin;
    MyCustomView myCustomView;
    private FrameLayout flSignup;
    private LinearLayout llSignup;
    private Button btnSingup1;

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenterImpl(this);
        findViewById(R.id.ivLogin).startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink));

        clLogin = (ConstraintLayout) findViewById(R.id.clLogin);
        myCustomView = (MyCustomView) findViewById(R.id.mcvLogin);
        flSignup = (FrameLayout) findViewById(R.id.flSignup);
        llSignup = (LinearLayout) findViewById(R.id.llSignup);
        btnSingup1 = (Button) findViewById(R.id.btnSingup1);

        myCustomView.btnSignup.setOnClickListener(this);
        myCustomView.btnLogin.setOnClickListener(this);
        myCustomView.btnGuest.setOnClickListener(this);
        btnSingup1.setOnClickListener(this);
        flag = false;
    }

    @Override
    public void onBackPressed() {
        if (flag) {
            onClick(findViewById(R.id.btnSingup1));
        } else
            super.onBackPressed();
    }

    @Override
    public void onLoginSuccess(String username) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Welcome", "Hello " + username);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFail(String message) {
        myCustomView.setMessage(message);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            loginPresenter.logIn(myCustomView.getUsername(), myCustomView.getPassword());
            Log.i("Login Activity", "Log in.");
            return;
        }
        if (view.getId() == R.id.btnGuest) {
            onLoginSuccess("Guest");
            return;
        }

        if (view.getId() == R.id.btnSingup1) {
            // Sign up new account.
        }

        int x = view.getLeft() + ((view.getRight() - view.getLeft()) / 2);
        int y = view.getTop() + ((view.getBottom() - view.getTop()) / 2);

        int hypotenuse = (int) Math.hypot(clLogin.getWidth(), clLogin.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(flSignup, x, y, 0, hypotenuse);
        anim.setDuration(700);

        if (!flag) {
            flSignup.setVisibility(View.VISIBLE);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                    alphaAnimation.setDuration(700);
                    llSignup.setVisibility(View.VISIBLE);
                    llSignup.startAnimation(alphaAnimation);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
//
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.start();
        } else {
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                    alphaAnimation.setDuration(700);
                    llSignup.setVisibility(View.GONE);
                    llSignup.startAnimation(alphaAnimation);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    flSignup.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.start();
        }
        flag = !flag;
    }
}