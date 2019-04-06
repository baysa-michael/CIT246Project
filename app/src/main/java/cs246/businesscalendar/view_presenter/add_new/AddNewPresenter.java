package cs246.businesscalendar.view_presenter.add_new;

import android.content.Context;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;

class AddNewPresenter implements AddNewContract.Presenter {
    private FirebaseAuthController authenticator;

    AddNewPresenter() {
        authenticator = new FirebaseAuthController();
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }
}
