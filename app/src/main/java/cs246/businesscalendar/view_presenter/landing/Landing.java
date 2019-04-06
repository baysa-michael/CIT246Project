package cs246.businesscalendar.view_presenter.landing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.R;

import cs246.businesscalendar.controller.database_controller.FirestoreAddUserListenerInterface;
import cs246.businesscalendar.controller.database_controller.FirestoreGetAppointmentsListenerInterface;
import cs246.businesscalendar.controller.database_controller.FirestoreGetUserListenerInterface;
import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.ParcelableAppointment;
import cs246.businesscalendar.model.UserData;
import cs246.businesscalendar.utilities.TestItems;
import cs246.businesscalendar.view_presenter.select_view.SelectView;
import cs246.businesscalendar.view_presenter.add_new.AddNew;

public class Landing extends AppCompatActivity implements LandingContract.View,
        FirestoreGetUserListenerInterface, FirestoreGetAppointmentsListenerInterface {
    private static final String TAG = "Landing";
    private LandingPresenter presenter;
    private RecyclerView.Adapter myAdapter;
    private ProgressBar indeterminateProgressBar;
    private List<Appointment> appointments;
    private List<ParcelableAppointment> parcelableAppointments;

    // Request Codes
    private static final int GENERAL_REQUEST_CODE = 0;

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

        // Initialize the Appointment Lists
        appointments = new ArrayList<>();
        parcelableAppointments = new ArrayList<>();

        // Set Recycler View with Layout Manager
        RecyclerView myRecycler = findViewById(R.id.landingRecyclerView);
        myRecycler.setHasFixedSize(true);
        myRecycler.addItemDecoration(new DividerItemDecoration(myRecycler.getContext(),
                DividerItemDecoration.VERTICAL));
        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(myLayoutManager);
        myAdapter = new LandingRecyclerViewAdapter(this, appointments);
        myRecycler.setAdapter(myAdapter);

        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar = findViewById(R.id.landingIndeterminateProgress);
        indeterminateProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check to see if user already signed in - If not, exit the activity
        if (!presenter.isUserSignedIn()) {
            finish();
        } else {
            // Start Indeterminate Progress Bar
            indeterminateProgressBar.setVisibility(View.VISIBLE);

            // Load User Data Into Shared Preferences
            presenter.getUserData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Start Indeterminate Progress Bar
        indeterminateProgressBar.setVisibility(View.VISIBLE);

        // Load User Data Into Shared Preferences
        presenter.getUserData();
    }

    @Override
    public void showSchedule() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    @Override
    public void showTasks() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    @Override
    public void showGoals() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    @Override
    public void showAddNew() {
        startActivity(AddNew.class);
    }

    @Override
    public void showSelectView() {
        startActivity(SelectView.class);
    }

    @Override
    public void showSearch() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    @Override
    public void showSettings() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    @Override
    public void showLogout() {
        // Inform the User
        informUser("Logging Out User");

        // Sign out the user and exit the activity
        presenter.signOutUser();
        finish();
    }

    @Override
    public void onGetUserSuccess(UserData loadedUser) {
        presenter.updateSharedPreferences(this, loadedUser);

        // Update Welcome for User
        String welcome = "Welcome " + loadedUser.getDisplayName();
        TextView welcomeText = findViewById(R.id.landingTitle);
        welcomeText.setText(welcome);

        // Load User Appointments
        presenter.getUserAppointments();
    }

    @Override
    public void onGetUserFailure() {
        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar.setVisibility(View.GONE);

        // Inform the User
        informUser("WARNING:  Error Retrieving User Information");
    }

    @Override
    public void onGetAppointmentsSuccess(List<ParcelableAppointment> downloadedAppointments) {
        // Clear any old data in the appointments
        parcelableAppointments.clear();
        appointments.clear();

        // Load the new appointments
        parcelableAppointments.addAll(downloadedAppointments);
        appointments.addAll(presenter.convertToAppointments(parcelableAppointments));

        // Notify the adapter
        myAdapter.notifyDataSetChanged();

        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetAppointmentsFailure() {
        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar.setVisibility(View.GONE);

        // Inform the User
        informUser("WARNING:  Error Retrieving User Appintments");
    }

    @Override
    public void informUser(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, message, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();
    }

    @Override
    public void startActivity(Class<?> activityClass) {
        Intent thisIntent = new Intent(this, activityClass);
        thisIntent.putParcelableArrayListExtra("appointments",
                (ArrayList<ParcelableAppointment>) parcelableAppointments);
        startActivityForResult(thisIntent, GENERAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test to make sure the proper request code is received
        if (requestCode == GENERAL_REQUEST_CODE) {
            // Make sure that the request return results are OK
            if (resultCode == RESULT_OK) {
                // Retrieve the updated appointments
                if (data != null) {
                    // Clear any old data in the appointments
                    parcelableAppointments.clear();
                    appointments.clear();


                    // Load the new appointments
                    parcelableAppointments.addAll(new ArrayList<>(data.<ParcelableAppointment>
                            getParcelableArrayListExtra("appointment")));
                    appointments.addAll(presenter.convertToAppointments(parcelableAppointments));

                    // Notify the adapter
                    myAdapter.notifyDataSetChanged();

                    // Set Indeterminate Progress Bar to Gone
                    indeterminateProgressBar.setVisibility(View.GONE);
                }

            }
        }
    }
}
