package cs246.businesscalendar.view_presenter.modify_appointment;

import android.content.Context;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;
import cs246.businesscalendar.controller.database_controller.FirestoreAddAppointmentListenerInterface;
import cs246.businesscalendar.controller.database_controller.FirestoreController;
import cs246.businesscalendar.model.ParcelableAppointment;

class ModifyAppointmentPresenter implements ModifyAppointmentContract.Presenter {
    private FirebaseAuthController authenticator;
    private FirestoreController database;

    ModifyAppointmentPresenter(Context newContext) {
        authenticator = new FirebaseAuthController();
        database = new FirestoreController((FirestoreAddAppointmentListenerInterface) newContext);
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }

    @Override
    public void addNewAppointment(ParcelableAppointment newAppointment) {
        database.addUserAppointment(authenticator.getCurrentUserID(), newAppointment);
    }
}
