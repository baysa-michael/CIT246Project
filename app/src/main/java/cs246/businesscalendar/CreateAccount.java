package cs246.businesscalendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {
    private Spinner timeZoneSpinner;
    private Spinner time1224HSpinner;
    private String username;
    private String displayName;
    private String email;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Set Drop-down Lists
        timeZoneSpinner = (Spinner) findViewById(R.id.createaccountTimeZoneSpinner);
        time1224HSpinner = (Spinner) findViewById(R.id.createaccount1224HSpinner);

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

    public void clickCreateAccount(View view) {
        // Gather form information into Edit Texts and place into Strings
        EditText usernameInput = findViewById(R.id.createaccountUsernameEdit);
        username = usernameInput.getText().toString();
        EditText displayNameInput = findViewById(R.id.createaccountDisplayNameEdit);
        displayName = displayNameInput.getText().toString();
        EditText emailInput = findViewById(R.id.createaccountEmailEdit);
        email = emailInput.getText().toString();
        EditText phoneInput = findViewById(R.id.createaccountPhoneEdit);
        phone = phoneInput.getText().toString();


        // Create Genearal Shared Preferences File
        SharedPreferences myPreferences = this
                .getSharedPreferences(getString(R.string.general_shared_preferences)
                        ,Context.MODE_PRIVATE);

        // Create an Editor
        SharedPreferences.Editor myEditor = myPreferences.edit();

        // Add Key-Value Pairs
        myEditor.putString("username", username);
        myEditor.putString("display_name", displayName);
        myEditor.putString("e-mail", email);
        myEditor.putString("phone", phone);

        // Confirm
        myEditor.apply();


        // Display a toast indicating the save was successful
        Context myContext = this;
        CharSequence myText = "User Preferences Saved";
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(myContext, myText, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();

        // Finish Create New Account Activity
        finish();
    }

    public void clickCreateAccountCancel(View view) {
        finish();
    }

}
