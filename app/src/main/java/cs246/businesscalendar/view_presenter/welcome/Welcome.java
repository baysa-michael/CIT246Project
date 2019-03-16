package cs246.businesscalendar.view_presenter.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.create_account.CreateAccount;
import cs246.businesscalendar.view_presenter.login.Login;


public class Welcome extends AppCompatActivity implements WelcomeContract.View {
    private static final String TAG = "Welcome";
    private Button loginButton;
    private Button createNewAccountButton;
    private WelcomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Set Buttons
        loginButton = findViewById(R.id.welcomeLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogin();
            }
        });

        createNewAccountButton = findViewById(R.id.welcomeCreateAccountButton);
        createNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateAccount();
            }
        });

        // Set Presenter
        presenter = new WelcomePresenter();
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
