package cs246.businesscalendar.view_presenter.create_account;

import android.content.Context;
import android.util.Log;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;
import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthListenerInterface;
import cs246.businesscalendar.controller.database_controller.FirestoreController;
import cs246.businesscalendar.controller.database_controller.FirestoreListenerInterface;

public class CreateAccountPresenter implements CreateAccountContract.Presenter {
    private FirebaseAuthController authenticator;
    private FirestoreController database;

    public CreateAccountPresenter(Context thisContext){
        authenticator = new FirebaseAuthController((FirebaseAuthListenerInterface) thisContext);
        database = new FirestoreController((FirestoreListenerInterface) thisContext);
    }


    @Override
    public boolean validatePasswordRequirements(String password) {
        // A minimum of 14 characters with at least one capital and one digit
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(\\S){14,}$");
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }

    @Override
    public void createNewAccount(String email, String password) {
        // Add new user to Authentication Database
        authenticator.addUser(email, password);
    }

    @Override
    public void addNewAccountData(String email, String displayName, String phone,
                           int timeZoneOffset, boolean is24H) {
        // Add new user to Data Database
        database.createOrUpdateAccount(authenticator.getCurrentUserID(), email, displayName, phone,
                timeZoneOffset, is24H);
    }

    @Override
    public void signOutUser() {
        authenticator.logout();
    }

    @Override
    public int getTimeZoneOffset(String spinnerValue) {

        return 1;
    }
}
