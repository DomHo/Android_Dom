package sg.vinova.dom.example_mvp_login.ui;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import sg.vinova.dom.example_mvp_login.R;
import sg.vinova.dom.example_mvp_login.module.LoginPresenterImpl;

import static sg.vinova.dom.example_mvp_login.ui.MainActivity.presenter;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        btnClose = (Button) findViewById(R.id.btnClose);
        tvWelcome.setText(getIntent().getExtras().getString("user"));
        String image = getIntent().getExtras().getString("background");
        Glide.with(this).load(image).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                findViewById(R.id.llMain).setBackground(resource);
                return false;
            }
        }).submit();
        presenter.setWelView(this);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
            }
        });
    }

    public void logout() {
        Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
        finish();
    }
}