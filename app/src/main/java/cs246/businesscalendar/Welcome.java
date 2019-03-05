package cs246.businesscalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //added comment for team activity for Jordan
        // MB - Comment Added for Team Activity - My Edit
        // MB - Comment Added for Team Activity- edited by JJ
        // TS = comment for Team Activity
    }

    /**
     * Called when user taps on Create New Account Button
     */
    public void clickCreateNewAccount(View view) {
        // Create and Start Intent to Create New Account Activity
        Intent thisIntent = new Intent(this, CreateAccount.class);

        startActivity(thisIntent);
    }
}
