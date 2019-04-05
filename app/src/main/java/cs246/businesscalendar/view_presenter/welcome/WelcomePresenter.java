package cs246.businesscalendar.view_presenter.welcome;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;

public class WelcomePresenter implements WelcomeContract.Presenter {
    private FirebaseAuthController authenticator;

    WelcomePresenter(){
        authenticator = new FirebaseAuthController();
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }
}
