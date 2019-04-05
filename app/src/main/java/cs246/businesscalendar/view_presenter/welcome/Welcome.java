package cs246.businesscalendar.view_presenter.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.create_account.CreateAccount;
import cs246.businesscalendar.view_presenter.landing.Landing;
import cs246.businesscalendar.view_presenter.login.Login;

/**
 * First page that runs when opening the app.
 * Offers user the ability to create a new account and/or login.
 */

public class Welcome extends AppCompatActivity implements WelcomeContract.View {
    private static final String TAG = "Welcome";
    private WelcomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Set Buttons
        Button loginButton = findViewById(R.id.welcomeLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogin();
            }
        });

        Button createNewAccountButton = findViewById(R.id.welcomeCreateAccountButton);
        createNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateAccount();
            }
        });

        // Set Presenter
        presenter = new WelcomePresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check to see if user is already logged on - If so, pass intent to the Landing activity
        if (presenter.isUserSignedIn()) {
            Intent thisIntent = new Intent(this, Landing.class);

            startActivity(thisIntent);
        }
    }

    public void showCreateAccount() {
        Intent thisIntent = new Intent(this, CreateAccount.class);

        startActivity(thisIntent);
    }

    public void showLogin() {
        Intent thisIntent = new Intent(this, Login.class);

        startActivity(thisIntent);
    }
}
