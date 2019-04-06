package cs246.businesscalendar.controller.database_controller;

public interface FirestoreDeleteUserListenerInterface extends FirestoreBaseListenerInterface {
    void onDeleteUserSuccess();
    void onDeleteUserFailure();
}
