package cs246.businesscalendar.view_presenter.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.landing.Landing;

public class Login extends AppCompatActivity implements LoginContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void showLogin() {

    }

    public void showCancel() {

    }

    public void clickLogin(View view) {
        // Create and Start Intent to Landing Page
        // ******THIS WILL NEED TO BE DELETED AND CHANGED WITH AN ACTUAL LOGIN PROCESS
        Intent thisIntent = new Intent(this, Landing.class);

        startActivity(thisIntent);
    }

    public void clickLoginCancel(View view) {
        finish();
    }
}
