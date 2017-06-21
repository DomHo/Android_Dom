package sg.vinova.dom.example_mvp_login.module;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

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
        if (username.length() < 1) {
            loginView.onLoginFail("username empty");
            return true;
        }
        if (username.contains(" ")) {
            loginView.onLoginFail(" ");
            return true;
        }
        if (username.contains(";")) {
            loginView.onLoginFail(" ");
            return true;
        }
        if (password.length() < 1) {
            loginView.onLoginFail("password empty");
            return true;
        }
        return false;
    }

    @Override
    public void checkValidate(String username, String password, boolean save) {
        if (!isValid(username, password))
            login(username, password, save);
    }

    @Override
    public void login(String username, String password, boolean save) {

        Account account = null;
        boolean right = false;
        boolean exist = false;

        File file = new File(mainContext.getFilesDir(), "my_file");
        int length = (int) file.length();
        byte[] bytes = new byte[length];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] contents = new String(bytes).split("\n");
        int size = Integer.parseInt(contents[0]);
        for (int i = 0; i <= size; i++) {
            String[] temp = contents[i].split(";");
            if (username.equals(temp[0])){
                if (password.equals(temp[1])){
                    account = new Account(temp[0], temp[1], temp[2]);
                    right = true;
                }
                else
                    exist = true;
                break;
            }
        }
        if (right) {
            loginView.onLoginSuccess(account);
            file.delete();
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
    public void signup(String username, String password, boolean save) {
        SharedPreferences pre = mainContext.getSharedPreferences("my_data", MODE_PRIVATE);
        SharedPreferences.Editor edit = pre.edit();
        int size = pre.getInt("size", 0);
        edit.putString("username" + size, username);
        edit.putString("password" + size, password);
        edit.putBoolean("save" + size, false);
        edit.putInt("size", size + 1);
        edit.apply();
        checkValidate(username, password, save);
    }

    private void loadPreferences() {
        SharedPreferences pre = mainContext.getSharedPreferences("my_data",MODE_PRIVATE);
        boolean save = pre.getBoolean("save", false);
        Account account = new Account("", "", "");
        if(save) {
            account.setUsername(pre.getString("username", ""));
            account.setPassword(pre.getString("password", ""));
        }
        loginView.logout(account);
    }
}