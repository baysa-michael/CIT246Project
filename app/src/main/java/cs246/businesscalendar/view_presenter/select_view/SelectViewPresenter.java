package cs246.businesscalendar.view_presenter.select_view;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;

public class SelectViewPresenter implements SelectViewContract.Presenter {
    private FirebaseAuthController authenticator;

    SelectViewPresenter() {
        authenticator = new FirebaseAuthController();
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }
}
