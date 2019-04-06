package cs246.businesscalendar.controller.database_controller;

public interface FirestoreModifyUserListenerInterface extends FirestoreBaseListenerInterface {
    void onModUserSuccess();
    void onModUserFailure();
}
