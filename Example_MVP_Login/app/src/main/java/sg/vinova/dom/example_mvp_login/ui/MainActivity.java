package sg.vinova.dom.example_mvp_login.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import sg.vinova.dom.example_mvp_login.R;
import sg.vinova.dom.example_mvp_login.model.Account;
import sg.vinova.dom.example_mvp_login.module.LoginFeature;
import sg.vinova.dom.example_mvp_login.module.LoginPresenterImpl;

import static android.R.attr.bitmap;

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
                presenter.checkValidate(edtUsername.getText().toString(), edtPassword.getText().toString(), cbSave.isChecked());
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signup(edtUsername.getText().toString(), edtPassword.getText().toString(), cbSave.isChecked());
            }
        });

        /////////////////////////////////////////////////////////////////

        File file = new File(getFilesDir(), "my_file");
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);

            String size = "10" + "\n";
            fileOutputStream.write(size.getBytes());
            for (int i = 1; i <= 10; i++) {
                String username = "g" + i + ";";
                String password = "g" + i + ";";
                String background = "https://cdn.pixabay.com/photo/2015/06/10/19/13/background-805060_960_720.jpg" + "\n";
                fileOutputStream.write(username.getBytes());
                fileOutputStream.write(password.getBytes());
                fileOutputStream.write(background.getBytes());
            }
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /////////

        ////////////Download file google drive

    }

    @Override
    public void onLoginSuccess(Account account) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("user", account.getUsername());
        intent.putExtra("background", account.getImage());
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        edtPassword.getText().clear();
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
        if (cbSave.isChecked())
            edtUsername.setText(account.getUsername());
        edtPassword.setText(account.getPassword());
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