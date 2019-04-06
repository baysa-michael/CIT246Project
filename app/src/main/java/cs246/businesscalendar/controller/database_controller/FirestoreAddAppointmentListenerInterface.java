package cs246.businesscalendar.controller.database_controller;

public interface FirestoreAddAppointmentListenerInterface extends FirestoreBaseListenerInterface {
    void onAddAppointmentSuccess();
    void onAddAppointmentFailure();
}
