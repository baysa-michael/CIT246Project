package cs246.businesscalendar.controller.authentication_controller;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthController implements AuthenticationInterface {
    private final String TAG = "FirebaseAuthController";
    private FirebaseAuth authenticator;
    private FirebaseUser currentUser;
    private FirebaseAuthListenerInterface listener;

    public FirebaseAuthController(FirebaseAuthListenerInterface newListener) {
        // Initialize Firebase Auth
        authenticator = FirebaseAuth.getInstance();
        currentUser = authenticator.getCurrentUser();
        listener = newListener;
    }

    @Override
    public boolean addUser(String email, String password) {
        authenticator.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Update the Current User
                            currentUser = authenticator.getCurrentUser();

                            Log.i(TAG, "New User Added by FirebaseAuth");

                            // Run the Success Tasks in the UI
                            listener.onAuthSuccess();
                        } else {
                            Log.i(TAG, "ERROR:  Did not add user to FirebaseAuth");

                            // Run the Failure Tasks in the UI
                            listener.onAuthFailure();
                        }
                    }
                });

        // Default return for this interface - Work is done by listener calls
        return true;
    }

    @Override
    public boolean authenticateUser(String email, String password) {

        return true;
    }

    @Override
    public boolean isUserSignedIn() {
        return currentUser != null;
    }

    @Override
    public String getCurrentUserID() {
        return currentUser.getUid();
    }

    @Override
    public void logout() {
        authenticator.signOut();
    }

}
