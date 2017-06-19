package sg.vinova.dom.example_mvp_login.module;

import sg.vinova.dom.example_mvp_login.model.Account;

/**
 * Created by HNS on 19/06/2017.
 */

public class LoginPresenterImpl implements LoginFeature.Presenter {

    private LoginFeature.View loginView;

    public LoginPresenterImpl(LoginFeature.View loginView) {
        this.loginView = loginView;
    }

    @Override
    public void checkValidate(String username, String password) {
        if (!isValid(username, password))
            loginView.checkDataConstraint(new Account(username, password));
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
    public void login(Account account) {
        if ((account.getUsername().equals("google")) && (account.getPassword().equals("apple")))
            loginView.onLoginSuccess();
        else
            loginView.onLoginFail("Wrong");

    }
}
