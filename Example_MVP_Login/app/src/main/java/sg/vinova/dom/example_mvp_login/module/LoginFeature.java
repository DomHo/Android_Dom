package sg.vinova.dom.example_mvp_login.module;

import sg.vinova.dom.example_mvp_login.model.Account;

public interface LoginFeature {

    interface View {
        void onLoginSuccess(Account account);

        void onLoginFail(String message);

        void logout(Account account);
    }

    interface Presenter {
        LoginPresenterImpl getInstance();

        void checkValidate(String username, String password, boolean save);

        void login(String username, String password, boolean save);

        void logout(Account account);

        void signup(String username, String password, boolean save);
    }
}