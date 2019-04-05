package cs246.businesscalendar.view_presenter.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import cs246.businesscalendar.R;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthListenerInterface;
import cs246.businesscalendar.view_presenter.create_account.CreateAccount;
import cs246.businesscalendar.view_presenter.landing.Landing;

public class Login extends AppCompatActivity implements LoginContract.View,
        FirebaseAuthListenerInterface {
    private static final String TAG = "Login";
    private LoginPresenter presenter;
    private ProgressBar indeterminateProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set Buttons
        Button loginButton = findViewById(R.id.loginLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogin();
            }
        });

        Button cancelButton = findViewById(R.id.loginCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancel();
            }
        });

        // Set Presenter
        presenter = new LoginPresenter(this);

        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar = findViewById(R.id.loginIndeterminateProgress);
        indeterminateProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check to see if user is already logged on - If so, pass intent to the Landing activity
        if (presenter.isUserSignedIn()) {
            Intent thisIntent = new Intent(this, Landing.class);

            startActivity(thisIntent);
        }
    }

    @Override
    public void showLogin() {
        // Gather Login Information
        String email = ((EditText)findViewById(R.id.loginEmailEdit)).getText().toString();
        String password = ((EditText)findViewById(R.id.loginPasswordEdit)).getText().toString();

        // Confirm all information has been entered in
        if (email.isEmpty() || password.isEmpty()) {
            // If missing e-mail or password, inform the user and exit the function
            informUser("ERROR:  Missing E-Mail and/or Password");
            return;
        }

        // Start Indeterminate Progress Bar
        indeterminateProgressBar.setVisibility(View.VISIBLE);

        // Authenticate User
        presenter.login(email, password);
    }

    @Override
    public void showCancel() {
        finish();
    }

    @Override
    public void onAuthSuccess() {
        // End Indeterminate Progress Bar
        indeterminateProgressBar.setVisibility(View.GONE);

        // Inform the User and then move to the Landing Page
        informUser("Successfully Logged In");

        Intent thisIntent = new Intent(this, CreateAccount.class);

        startActivity(thisIntent);
    }

    @Override
    public void onAuthFailure() {
        // End Indeterminate Progress Bar
        indeterminateProgressBar.setVisibility(View.GONE);

        informUser("ERROR:  Unable to Authenticate User");
    }

    @Override
    public void informUser(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, message, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();
    }
}
