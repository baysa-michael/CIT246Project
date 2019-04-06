package cs246.businesscalendar.controller.database_controller;

public interface FirestoreGetAppointmentsListenerInterface extends FirestoreBaseListenerInterface {
    void onGetAppointmentsSuccess();
    void onGetAppointmentsFailure();
}
