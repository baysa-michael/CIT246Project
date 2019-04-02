package cs246.businesscalendar.controller.authentication_controller;

import com.google.firebase.auth.FirebaseAuth;

public interface AuthenticationInterface {
    boolean addUser(String email, String password, String displayName, String phone,
                    int timeZoneOffset, boolean is24H);

    boolean authenticateUser(String email, String password);

    void logout();
}
