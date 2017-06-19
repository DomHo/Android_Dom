package sg.vinova.dom.example_mvp_login.module;

import sg.vinova.dom.example_mvp_login.model.Account;

public interface LoginFeature {

    interface View {
        void onLoginSuccess();
        void onLoginFail(String message);
        void checkDataConstraint(Account account);
    }

    interface Presenter {
        void checkValidate(String username, String password);
        void login(Account account);
    }
}
