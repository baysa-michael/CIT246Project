package cs246.businesscalendar.controller.authentication_controller;

public interface FirebaseAuthListenerInterface {
    void onAuthSuccess();
    void onAuthFailure();
}
