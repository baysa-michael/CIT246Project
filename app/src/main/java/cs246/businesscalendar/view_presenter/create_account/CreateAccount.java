package cs246.businesscalendar.view_presenter.create_account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cs246.businesscalendar.R;
import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthListenerInterface;
import cs246.businesscalendar.controller.database_controller.FirestoreListenerInterface;
import cs246.businesscalendar.view_presenter.landing.Landing;

public class CreateAccount extends AppCompatActivity implements CreateAccountContract.View,
FirebaseAuthListenerInterface, FirestoreListenerInterface {
    private static final String TAG = "CreateAccount";
    private CreateAccountPresenter presenter;
    private Spinner timeZoneSpinner;
    private Spinner time1224HSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Set Buttons
        Button confirmButton = findViewById(R.id.createaccountConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirm();
            }
        });

        Button cancelButton = findViewById(R.id.createaccountCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancel();
            }
        });

        // Set Presenter
        presenter = new CreateAccountPresenter(this);

        // Set Drop-down Lists
        timeZoneSpinner = findViewById(R.id.createaccountTimeZoneSpinner);
        time1224HSpinner = findViewById(R.id.createaccount1224HSpinner);

        // Create Array Adapters
        ArrayAdapter<CharSequence> timeZoneAdapter = ArrayAdapter.createFromResource(this,
                R.array.time_zone, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> time1224HAdapter = ArrayAdapter.createFromResource(this,
                R.array.time_1224, android.R.layout.simple_spinner_item);

        // Specify layout for spinner dropdown items when list appears
        timeZoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time1224HAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the appropriate spinner
        timeZoneSpinner.setAdapter(timeZoneAdapter);
        time1224HSpinner.setAdapter(time1224HAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check to see if user is already signed in, and log user out if signed in as user should
        // not be signed in on the Create Account page
        if (presenter.isUserSignedIn()) {
            presenter.signOutUser();
        }

    }

    public void showConfirm() {
        // Gather form information into Edit Texts and place into Strings
        EditText emailInput = findViewById(R.id.createaccountEmailEdit);
        String email = emailInput.getText().toString();
        EditText passwordInput = findViewById(R.id.createaccountPasswordEdit);
        String password = passwordInput.getText().toString();

        // Check Password Parameters
        if (!presenter.validatePasswordRequirements(password)) {
            CharSequence myText = "ERROR:  User Password Does Not Meet Minimum Requirements";
            int duration = Toast.LENGTH_SHORT;
            Toast toastSuccessful = Toast.makeText(this, myText, duration);
            toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
            toastSuccessful.show();

            // Exit Function
            return;
        }

        // Add user to the authenticator and database
        presenter.createNewAccount(email, password);

        // Finish Create New Account Activity
        finish();
    }

    public void showCancel() {
        finish();
    }

    @Override
    public void onAuthSuccess() {
        // If successful, add the user information to the database

        Log.i(TAG, "Started Auth Success");

        // Retrieve Information
        EditText emailInput = findViewById(R.id.createaccountEmailEdit);
        String email = emailInput.getText().toString();
        EditText displayNameInput = findViewById(R.id.createaccountDisplayNameEdit);
        String displayName = displayNameInput.getText().toString();
        EditText phoneInput = findViewById(R.id.createaccountPhoneEdit);
        String phone = phoneInput.getText().toString();

        // Get Spinner Values
        int offset = presenter.getTimeZoneOffset(timeZoneSpinner.getSelectedItem().toString());
        boolean is24H = false;
        if (time1224HSpinner.getSelectedItem().toString().equals("24H")) {
            is24H = true;
        }

        Log.i(TAG, "Values for Auth Success Set");

        presenter.addNewAccountData(email, displayName, phone, offset, is24H);

        Log.i(TAG, "After Auth Success");

    }

    @Override
    public void onAuthFailure() {
        Log.i(TAG, "Inform User of Auth Failure");

        informUser("ERROR:  Unable to Add User");
    }

    @Override
    public void onReadWriteSuccess() {
        Log.i(TAG, "Inform User of Read Write Success");

        informUser("Successfully Added User and Logged In");

        // Users to this point are successfully logged in and will move to the landing page
        Intent thisIntent = new Intent(this, Landing.class);

        startActivity(thisIntent);
    }

    @Override
    public void onReadWriteFailure() {
        Log.i(TAG, "Inform User of Write Read Failure");

        informUser("ERROR:  User Data Not Saved");
    }


    public void informUser(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, message, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();
    }

}
