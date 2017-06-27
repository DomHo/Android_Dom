package sg.vinova.dom.myapplication.loginFeature;

/**
 * Created by HNS on 26/06/2017.
 */

public interface Login {

    interface View {
        void onLoginSuccess(String username);

        void onLoginFail(String message);
    }

    interface Presenter {
        void logIn(String username, String Password);
    }
}
