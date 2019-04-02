package cs246.businesscalendar.view_presenter.create_account;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormatSymbols;

import cs246.businesscalendar.R;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.quickstart.auth.R;

public class CreateAccount extends AppCompatActivity implements CreateAccountContract.View {
    private static final String TAG = "CreateAccount";
    private CreateAccountPresenter presenter;
    private Spinner timeZoneSpinner;
    private Spinner time1224HSpinner;
    private String displayName;
    private String email;
    private String phone;

    //firebase
    private FirebaseAuth mAuth;

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
        presenter = new CreateAccountPresenter();

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


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //checks if user is signed in already
        @Override
        public void onStart() {
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);
        }

        //create a new email and password
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    public void showConfirm() {
        // NEED TO INPUT ACTUAL LOGIC HERE **********************************************


        // Gather form information into Edit Texts and place into Strings
        EditText usernameInput = findViewById(R.id.createaccountUsernameEdit);
        username = usernameInput.getText().toString();
        EditText displayNameInput = findViewById(R.id.createaccountDisplayNameEdit);
        displayName = displayNameInput.getText().toString();
        EditText emailInput = findViewById(R.id.createaccountEmailEdit);
        email = emailInput.getText().toString();
        EditText phoneInput = findViewById(R.id.createaccountPhoneEdit);
        phone = phoneInput.getText().toString();


        // Create General Shared Preferences File
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
        finish();    }

    public void showCancel() {
        finish();
    }
}
