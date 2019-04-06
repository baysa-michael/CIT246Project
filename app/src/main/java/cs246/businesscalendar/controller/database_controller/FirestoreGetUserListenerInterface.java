package cs246.businesscalendar.controller.database_controller;

import cs246.businesscalendar.model.UserData;

public interface FirestoreGetUserListenerInterface extends FirestoreBaseListenerInterface {
    void onGetUserSuccess(UserData loadedUser);
    void onGetUserFailure();
}
