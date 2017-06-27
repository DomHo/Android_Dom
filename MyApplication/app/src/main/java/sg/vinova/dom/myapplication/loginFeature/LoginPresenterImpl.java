package sg.vinova.dom.myapplication.loginFeature;

import io.realm.Realm;
import sg.vinova.dom.myapplication.model.Account;

/**
 * Created by HNS on 26/06/2017.
 */

public class LoginPresenterImpl implements Login.Presenter {

    private Login.View loginView;

    public LoginPresenterImpl(Login.View loginView) {
        this.loginView = loginView;
    }

    @Override
    public void logIn(String username, String password) {
        if (isValid(username, password)) {
            Realm realm = Realm.getDefaultInstance();
            Account account = realm.where(Account.class).equalTo("username", username).findFirst();
            if (account == null)
                loginView.onLoginFail("Account doesn't exist.");
            else
                if (password.equals(account.getPassword()))
                    loginView.onLoginSuccess(username);
                else
                    loginView.onLoginFail("Wrong password.");
            realm.close();
        }
    }

    private boolean isValid(String username, String password) {
        if (username.length() < 1) {
            loginView.onLoginFail("Username is empty.");
            return false;
        }
        if (password.length() < 1) {
            loginView.onLoginFail("Password is empty.");
            return  false;
        }
        if (username.contains(" ")){
            loginView.onLoginFail("Username invalid: space");
            return false;
        }
        if (username.contains(";")){
            loginView.onLoginFail("Username invalid: ;");
            return false;
        }
        return true;
    }
}