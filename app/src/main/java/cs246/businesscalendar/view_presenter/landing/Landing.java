package cs246.businesscalendar.view_presenter.landing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.select_view.SelectView;
import cs246.businesscalendar.view_presenter.add_new.AddNew;

public class Landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // Obtain Display Name in Shared Preferences
        SharedPreferences myPreferences = this.getSharedPreferences(
                getString(R.string.general_shared_preferences), Context.MODE_PRIVATE);
        String welcome = "Welcome " + myPreferences.getString("display_name", "");

        // Include Display Name with Welcome
        TextView welcomeText = findViewById(R.id.landingTitle);
        welcomeText.setText(welcome);
    }

    public void showSchedule() {

    }

    public void showTasks() {

    }

    public void showGoals() {

    }

    public void showAddNew() {

    }

    public void showSelectView() {

    }

    public void showSearch() {

    }

    public void showSettings() {

    }

    public void showLogout() {

    }

    public void clickAddNew(View view) {
        Intent thisIntent = new Intent(this, AddNew.class);

        startActivity(thisIntent);
    }

    public void clickSelectView(View view) {
        Intent thisIntent = new Intent(this, SelectView.class);

        startActivity(thisIntent);
    }

    public void clickLogout(View view) {
        finish();
    }
}
