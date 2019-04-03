package cs246.businesscalendar.controller.database_controller;

public interface FirestoreListenerInterface {
    void onWriteReadSuccess();
    void onWriteReadFailure();
}
