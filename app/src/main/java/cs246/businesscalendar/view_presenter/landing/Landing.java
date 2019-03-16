package cs246.businesscalendar.view_presenter.landing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.select_view.SelectView;
import cs246.businesscalendar.view_presenter.add_new.AddNew;

public class Landing extends AppCompatActivity {
    private static final String TAG = "Landing";
    private Button scheduleButton;
    private Button tasksButton;
    private Button goalsButton;
    private Button addNewButton;
    private Button selectViewButton;
    private Button searchButton;
    private Button settingsButton;
    private Button logoutButton;
    private LandingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // Set Buttons
        scheduleButton = findViewById(R.id.landingScheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSchedule();
            }
        });

        tasksButton = findViewById(R.id.landingTasksButton);
        tasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTasks();
            }
        });

        goalsButton = findViewById(R.id.landingGoalsButton);
        goalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGoals();
            }
        });

        addNewButton = findViewById(R.id.landingAddNewButton);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNew();
            }
        });

        selectViewButton = findViewById(R.id.landingSelectViewButton);
        selectViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectView();
            }
        });

        searchButton = findViewById(R.id.landingSearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearch();
            }
        });

        settingsButton = findViewById(R.id.landingSettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSettings();
            }
        });

        logoutButton = findViewById(R.id.landingLogoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogout();
            }
        });

        // Set Presenter
        presenter = new LandingPresenter();

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
        Intent thisIntent = new Intent(this, AddNew.class);

        startActivity(thisIntent);
    }

    public void showSelectView() {
        Intent thisIntent = new Intent(this, SelectView.class);

        startActivity(thisIntent);
    }

    public void showSearch() {
        finish();
    }

    public void showSettings() {
        finish();
    }

    public void showLogout() {
        finish();
    }
}
