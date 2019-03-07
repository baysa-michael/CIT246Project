package cs246.businesscalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void clickLogin(View view) {
        // Create and Start Intent to Landing Page
        // ******THIS WILL NEED TO BE DELETED AND CHANGED WITH AN ACTUAL LOGIN PROCESS
        Intent thisIntent = new Intent(this, Landing.class);

        startActivity(thisIntent);
    }
}
