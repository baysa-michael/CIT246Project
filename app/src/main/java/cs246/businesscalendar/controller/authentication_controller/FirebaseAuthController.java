package cs246.businesscalendar.controller.authentication_controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthController implements AuthenticationInterface {
    private FirebaseAuth authenticator;
    private FirebaseUser currentUser;

    public FirebaseAuthController() {
        // Initialize Firebase Auth
        authenticator = FirebaseAuth.getInstance();
    }

    @Override
    public boolean addUser(String email, String password, String displayName, String phone,
                    int timeZoneOffset, boolean is24H) {

        return true;
    }

    @Override
    public boolean authenticateUser(String email, String password) {

        return true;
    }

    @Override
    public void logout() {

    }

}
