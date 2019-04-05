package cs246.businesscalendar.view_presenter.login;

import android.content.Context;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;
import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthListenerInterface;

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";
    private FirebaseAuthController authenticator;

    LoginPresenter(Context thisContext) {
        authenticator = new FirebaseAuthController((FirebaseAuthListenerInterface) thisContext);
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }

    @Override
    public void login(String email, String password) {
        authenticator.authenticateUser(email, password);
    }
}