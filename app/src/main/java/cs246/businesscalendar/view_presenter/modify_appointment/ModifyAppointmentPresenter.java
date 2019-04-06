package cs246.businesscalendar.view_presenter.modify_appointment;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;

class ModifyAppointmentPresenter implements ModifyAppointmentContract.Presenter {
    private FirebaseAuthController authenticator;

    ModifyAppointmentPresenter() {
        authenticator = new FirebaseAuthController();
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }
}
