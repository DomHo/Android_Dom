package sg.vinova.dom.example_mvp_login.module;

import io.realm.Realm;
import sg.vinova.dom.example_mvp_login.model.Account;
import sg.vinova.dom.example_mvp_login.utils.WelcomeActivity;

/**
 * Created by HNS on 19/06/2017.
 */

public class LoginPresenterImpl implements LoginFeature.Presenter {

    private LoginFeature.View loginView;
    private WelcomeActivity welView;

    public LoginPresenterImpl() {
    }

    public void setLoginView(LoginFeature.View loginView) {
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
    public LoginPresenterImpl getInstance() {
        return null;
    }

    @Override
    public void checkValidate(String username, String password, boolean save) {
        if (!isValid(username, password))
            login(username, password, save);
    }

    @Override
    public void login(String username, String password, boolean save) {

        Account account = Realm.getDefaultInstance().where(Account.class).equalTo("username", username).findFirst();

        if (account == null) {
            loginView.onLoginFail("NonExist");
        }
        else {
            if (account.getPassword().equals(password)) {
                loginView.onLoginSuccess(account);
            }
            else {
                loginView.onLoginFail("Wrong");
            }
        }
    }

    @Override
    public void logout(Account account) {
        welView.logout();
        loginView.logout(account);
    }

    @Override
    public void signup(String username, String password, boolean save) {
//        SharedPreferences pre = mainContext.getSharedPreferences("my_data", MODE_PRIVATE);
//        SharedPreferences.Editor edit = pre.edit();
//        int size = pre.getInt("size", 0);
//        edit.putString("username" + size, username);
//        edit.putString("password" + size, password);
//        edit.putBoolean("save" + size, false);
//        edit.putInt("size", size + 1);
//        edit.apply();
//        checkValidate(username, password, save);
    }
}