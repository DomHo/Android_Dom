package sg.vinova.dom.myapplication.utils;

import android.animation.Animator;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import sg.vinova.dom.myapplication.MyCustomView.MyCustomView;
import sg.vinova.dom.myapplication.loadImageFeature.LoadImage;
import sg.vinova.dom.myapplication.loginFeature.Login;
import sg.vinova.dom.myapplication.loginFeature.LoginPresenterImpl;
import sg.vinova.dom.myapplication.R;

public class LoginActivity extends AppCompatActivity implements Login.View, View.OnClickListener {

    private LoginPresenterImpl loginPresenter;
    MyCustomView myCustomView;

    private FrameLayout flSignup;
    private LinearLayout layoutButtons;
    private ConstraintLayout clLogin;
    private Button btnSingup1;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenterImpl(this);
        findViewById(R.id.ivLogin).startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink));

        myCustomView = (MyCustomView) findViewById(R.id.mcvUsername);


        clLogin = (ConstraintLayout) findViewById(R.id.clLogin);
        flSignup = (FrameLayout) findViewById(R.id.flSignup);
        layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons);

        myCustomView.btnSignup.setOnClickListener(this);
        btnSingup1 = (Button) findViewById(R.id.btnSingup1);
        btnSingup1.setOnClickListener(this);
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
    public void onBackPressed() {
        if (flag) {
            onClick(findViewById(R.id.btnSingup1));
        } else
            super.onBackPressed();
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
    public void onClick(View view) {

        if (view.getId() == R.id.btnSingup1) {
            // Write new account to database
        }

        int x = view.getLeft() + ((view.getRight() - view.getLeft()) / 2);
        int y = view.getTop() + ((view.getBottom() - view.getTop()) / 2);

        int hypotenuse = (int) Math.hypot(clLogin.getWidth(), clLogin.getHeight());

        if (!flag) {
            Animator anim = ViewAnimationUtils.createCircularReveal(flSignup, x, y, 0, hypotenuse);
            anim.setDuration(700);

            flSignup.setVisibility(View.VISIBLE);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                    alphaAnimation.setDuration(700);
                    layoutButtons.setVisibility(View.VISIBLE);
                    layoutButtons.startAnimation(alphaAnimation);
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
            Animator anim = ViewAnimationUtils.createCircularReveal(flSignup, x, y, hypotenuse, 0);
            anim.setDuration(700);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                    alphaAnimation.setDuration(700);
                    layoutButtons.setVisibility(View.GONE);
                    layoutButtons.startAnimation(alphaAnimation);
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