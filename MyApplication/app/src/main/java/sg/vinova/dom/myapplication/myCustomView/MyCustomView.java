package sg.vinova.dom.myapplication.myCustomView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import sg.vinova.dom.myapplication.R;

public class MyCustomView extends LinearLayout {

    private EditText edtUsername, edtPassword;
    private CheckBox cbSave;
    private TextView tvMessage;
    public Button btnSignup, btnLogin, btnGuest;

    public MyCustomView(Context context) {
        super(context);
        init(context);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public String getUsername() {
        return edtUsername.getText().toString();
    }

    public void setUsername(String username) {
        edtUsername.setText(username);
    }

    public String getPassword() {
        return edtPassword.getText().toString();
    }

    public void setPassword(String password) {
        edtPassword.setText(password);
    }

    public boolean isSave() {
        return cbSave.isChecked();
    }

    public void setSave(boolean save) {
        cbSave.setChecked(save);
    }

    public void setMessage(String message) {
        tvMessage.setText(message);
    }

    private void init(Context context) {
        View rootView = inflate(context, R.layout.my_custom_view, this);

        edtUsername = (EditText) rootView.findViewById(R.id.edtUsername);
        edtPassword = (EditText) rootView.findViewById(R.id.edtPassword);
        cbSave = (CheckBox) rootView.findViewById(R.id.cbSave);
        tvMessage = (TextView) rootView.findViewById(R.id.tvMessage);
        btnSignup = (Button) rootView.findViewById(R.id.btnSignup);
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        btnGuest = (Button) rootView.findViewById(R.id.btnGuest);

        RxTextView.textChanges(edtUsername)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        tvMessage.setText(charSequence.length() + "/50");
                    }
                });
    }
}