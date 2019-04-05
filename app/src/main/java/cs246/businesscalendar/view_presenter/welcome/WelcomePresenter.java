package cs246.businesscalendar.view_presenter.welcome;

import android.content.Context;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;
import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthListenerInterface;

public class WelcomePresenter implements WelcomeContract.Presenter {
    private FirebaseAuthController authenticator;

    WelcomePresenter(Context thisContext){
        authenticator = new FirebaseAuthController((FirebaseAuthListenerInterface) thisContext);
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }
}
