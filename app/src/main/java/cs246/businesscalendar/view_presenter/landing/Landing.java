package cs246.businesscalendar.view_presenter.landing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cs246.businesscalendar.R;

import cs246.businesscalendar.controller.database_controller.FirestoreListenerInterface;
import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.utilities.TestItems;
import cs246.businesscalendar.view_presenter.select_view.SelectView;
import cs246.businesscalendar.view_presenter.add_new.AddNew;

public class Landing extends AppCompatActivity implements LandingContract.View,
        FirestoreListenerInterface {
    private static final String TAG = "Landing";
    private LandingPresenter presenter;
    private RecyclerView myRecycler;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // Set Buttons
        Button scheduleButton = findViewById(R.id.landingScheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSchedule();
            }
        });

        Button tasksButton = findViewById(R.id.landingTasksButton);
        tasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTasks();
            }
        });

        Button goalsButton = findViewById(R.id.landingGoalsButton);
        goalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGoals();
            }
        });

        Button addNewButton = findViewById(R.id.landingAddNewButton);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNew();
            }
        });

        Button selectViewButton = findViewById(R.id.landingSelectViewButton);
        selectViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectView();
            }
        });

        Button searchButton = findViewById(R.id.landingSearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearch();
            }
        });

        Button settingsButton = findViewById(R.id.landingSettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSettings();
            }
        });

        Button logoutButton = findViewById(R.id.landingLogoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogout();
            }
        });

        // Set Presenter
        presenter = new LandingPresenter(this);

        // Obtain Display Name in Shared Preferences
        SharedPreferences myPreferences = this.getSharedPreferences(
                getString(R.string.general_shared_preferences), Context.MODE_PRIVATE);
        String welcome = "Welcome " + myPreferences.getString("display_name", "");

        // Include Display Name with Welcome
        TextView welcomeText = findViewById(R.id.landingTitle);
        welcomeText.setText(welcome);

        // SET UP TEST LIST OF APPOINTMENTS ************* TO REMOVE *************************
        List<Appointment> testAppointments = TestItems.testAppointments();

        // Set Recycler View with Layout Manager and Adapter
        myRecycler = findViewById(R.id.landingRecyclerView);
        myRecycler.setHasFixedSize(true);
        myRecycler.addItemDecoration(new DividerItemDecoration(myRecycler.getContext(),
                DividerItemDecoration.VERTICAL));
        myLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(myLayoutManager);
        myAdapter = new LandingRecyclerViewAdapter(this, testAppointments);
        myRecycler.setAdapter(myAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check to see if user already signed in - If not, exit the activity
        if (!presenter.isUserSignedIn()) {
            finish();
        }
    }
    public void showSchedule() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    public void showTasks() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    public void showGoals() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
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
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    public void showSettings() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    public void showLogout() {
        // Inform the User
        informUser("Logging Out User");

        // Sign out the user and exit the activity
        presenter.signOutUser();
        finish();
    }

    @Override
    public void onReadWriteSuccess() {

    }

    @Override
    public void onReadWriteFailure() {

    }

    @Override
    public void informUser(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, message, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();
    }
}
