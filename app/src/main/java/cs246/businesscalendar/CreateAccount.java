package cs246.businesscalendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {
    Spinner timeZoneSpinner;
    Spinner time1224HSpinner;

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

    public void saveUsername(View view) {
        SharedPreferences myPreferences = this.getSharedPreferences(,Context.MODE_PRIVATE);

        SharedPreferences.Editor myEditor = myPreferences.edit();

        myEditor.putString("savedUsername", username);

        myEditor.apply();

        Context myContext = DisplayUsername.this;
        CharSequence myText = "Username Saved";
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(myContext, myText, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();

        Intent intent = new Intent(this, Login.class);

        startActivity(intent);
    }

}
