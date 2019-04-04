package cs246.businesscalendar.view_presenter.create_account;

import android.content.Context;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;
import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthListenerInterface;
import cs246.businesscalendar.controller.database_controller.FirestoreController;
import cs246.businesscalendar.controller.database_controller.FirestoreListenerInterface;

public class CreateAccountPresenter implements CreateAccountContract.Presenter {
    private static final String TAG = "CreateAccountPresenter";
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
        // Find the time offset in the string
        Pattern offsetPattern = Pattern.compile("([+-]\\s\\d?\\d)");
        Matcher thisMatch = offsetPattern.matcher(spinnerValue);

        // Extract the offset from the pattern
        if (thisMatch.find()) {
            String offsetBlock = thisMatch.group(0);
            // Determine if offset is positive or negative
            boolean isPositive = offsetBlock.substring(0, 1).equals("+");

            // Extract Offset Number
            int returnNumber = Integer.parseInt(offsetBlock.substring(2));

            if (!isPositive) {
                returnNumber *= -1;
            }

            return returnNumber;
        } else {
            // Default Return Value
            return 0;
        }

    }
}
