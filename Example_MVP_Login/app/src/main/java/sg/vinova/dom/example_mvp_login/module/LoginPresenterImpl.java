package sg.vinova.dom.example_mvp_login.module;

import android.content.SharedPreferences;

import sg.vinova.dom.example_mvp_login.model.Account;
import sg.vinova.dom.example_mvp_login.ui.WelcomeActivity;

import static android.content.Context.MODE_PRIVATE;
import static sg.vinova.dom.example_mvp_login.ui.MainActivity.mainContext;

/**
 * Created by HNS on 19/06/2017.
 */

public class LoginPresenterImpl implements LoginFeature.Presenter {

    private LoginFeature.View loginView;
    private WelcomeActivity welView;

    public LoginPresenterImpl(LoginFeature.View loginView) {
        this.loginView = loginView;
    }

    public void setWelView(WelcomeActivity welView) {
        this.welView = welView;
    }

    private boolean isValid(String username, String password) {
        boolean empty = false;
        if (username.length() < 1) {
            loginView.onLoginFail("Username");
            empty = true;
        }
        if (password.length() < 1) {
            loginView.onLoginFail("Password");
            empty = true;
        }
        return empty;
    }

    @Override
    public void checkValidate(String username, String password) {
        if (!isValid(username, password))
            loginView.checkDataConstraint(new Account(username, password));
    }

    @Override
    public void login(Account account, boolean save) {

        String username = account.getUsername();
        String password = account.getPassword();
        boolean right = false;
        boolean exist = false;
        SharedPreferences pre = mainContext.getSharedPreferences("my_data",MODE_PRIVATE);

        for (int i = 0; i < pre.getInt("size", 0); i++) {
            if (pre.getString("username" + i, "").equals(username)) {
                if (pre.getString("password" + i, "").equals(password))
                    right = true;
                else
                    exist = true;
                break;
            }
        }
        if (right) {
            loginView.onLoginSuccess();
        } else
            if (exist)
                loginView.onLoginFail("Wrong");
            else
                loginView.onLoginFail("NonExist");
    }

    @Override
    public void logout() {
        welView.logout();
        loadPreferences();
    }

    @Override
    public void signup(String username, String password) {
        SharedPreferences pre = mainContext.getSharedPreferences("my_data", MODE_PRIVATE);
        SharedPreferences.Editor edit = pre.edit();
        int size = pre.getInt("size", 0);
        edit.putString("username" + size, username);
        edit.putString("password" + size, password);
        edit.putBoolean("save" + size, false);
        edit.putInt("size", size + 1);
        edit.apply();
        checkValidate(username, password);
    }

    private void loadPreferences() {
        SharedPreferences pre = mainContext.getSharedPreferences("my_data",MODE_PRIVATE);
        boolean save = pre.getBoolean("save", false);
        Account account = new Account("", "");
        if(save) {
            account.setUsername(pre.getString("username", ""));
            account.setPassword(pre.getString("password", ""));
        }
        loginView.logout(account);
    }
}