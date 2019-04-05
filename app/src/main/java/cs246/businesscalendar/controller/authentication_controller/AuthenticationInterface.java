package cs246.businesscalendar.controller.authentication_controller;

interface AuthenticationInterface {
    boolean addUser(String email, String password);

    boolean authenticateUser(String email, String password);

    String getCurrentUserID();

    boolean isUserSignedIn();

    void logout();
}
