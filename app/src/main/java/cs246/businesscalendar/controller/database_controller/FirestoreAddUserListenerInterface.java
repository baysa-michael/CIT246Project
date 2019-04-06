package cs246.businesscalendar.controller.database_controller;

public interface FirestoreAddUserListenerInterface extends FirestoreBaseListenerInterface {
    void onAddUserSuccess();
    void onAddUserFailure();
}
