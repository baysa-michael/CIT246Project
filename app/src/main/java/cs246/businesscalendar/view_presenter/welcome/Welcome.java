package cs246.businesscalendar.view_presenter.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.create_account.CreateAccount;
import cs246.businesscalendar.view_presenter.login.Login;


public class Welcome extends AppCompatActivity implements WelcomeContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void showCreateAccount() {

    }

    public void showLogin() {

    }

    /**
     * Called when user taps on Create New Account Button
     */
    public void clickCreateNewAccount(View view) {
        // Create and Start Intent to Create New Account Activity
        Intent thisIntent = new Intent(this, CreateAccount.class);

        startActivity(thisIntent);
    }

    /**
     * Called when user taps on Login Button
     */
    public void clickLogin(View view) {
        // Create and Start Intent to Create New Account Activity
        Intent thisIntent = new Intent(this, Login.class);

        startActivity(thisIntent);
    }
}
