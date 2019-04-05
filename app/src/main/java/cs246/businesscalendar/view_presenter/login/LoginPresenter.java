package cs246.businesscalendar.view_presenter.login;

import android.content.Context;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;
import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthListenerInterface;

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";
    private FirebaseAuthController authenticator;

    public LoginPresenter(Context thisContext) {
        authenticator = new FirebaseAuthController((FirebaseAuthListenerInterface) thisContext);
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }

    public boolean login(String email, String password) {
        // Confirm Username and Password are not empty strings
        if (email == null || email.isEmpty() ||
        password == null || password.isEmpty()) {
            // Send false back to the view
            return false;
        }

        // ADD LOGIC FOR LOGIN HERE ***************
        return true;
    }

    public void handleClickCancel() {

    }
}
