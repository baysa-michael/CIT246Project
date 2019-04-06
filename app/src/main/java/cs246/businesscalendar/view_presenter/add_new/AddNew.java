package cs246.businesscalendar.view_presenter.add_new;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.R;

import cs246.businesscalendar.model.ParcelableAppointment;
import cs246.businesscalendar.view_presenter.modify_appointment.ModifyAppointment;

public class AddNew extends AppCompatActivity implements AddNewContract.View {
    private static final String TAG = "AddNew";
    private AddNewPresenter presenter;
    private List<ParcelableAppointment> parcelableAppointments;

    // Request Codes
    private static final int GENERAL_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        // Set Buttons
        Button addNewAppointmentButton = findViewById(R.id.addnewAppointmentButton);
        addNewAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNewAppointment();
            }
        });

        Button addNewTaskButton = findViewById(R.id.addnewTaskButton);
        addNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNewTask();
            }
        });

        Button addNewGoalButton = findViewById(R.id.addnewGoalButton);
        addNewGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNewGoal();
            }
        });

        Button joinGroupButton = findViewById(R.id.addnewJoinGroupButton);
        joinGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showJoinGroup();
            }
        });

        Button createGroupButton = findViewById(R.id.addnewCreateGroupButton);
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateGroup();
            }
        });

        Button cancelButton = findViewById(R.id.addnewCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancel();
            }
        });

        // Set Presenter
        presenter = new AddNewPresenter();

        // Load Activity List
        parcelableAppointments = getIntent().getParcelableArrayListExtra("appointments");
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check to see if user already signed in - If not, exit the activity
        if (!presenter.isUserSignedIn()) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Prepare Return Intent
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra("appointments",
                (ArrayList<ParcelableAppointment>) parcelableAppointments);
        returnIntent.putExtra("code", GENERAL_REQUEST_CODE);
        setResult(RESULT_OK, returnIntent);
    }

    @Override
    public void showAddNewAppointment() {
        moveToNextActivity(ModifyAppointment.class);
    }

    @Override
    public void showAddNewTask() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    @Override
    public void showAddNewGoal() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    @Override
    public void showJoinGroup() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    @Override
    public void showCreateGroup() {
        // NOT YET IMPLEMENTED
        informUser("NOTICE:  Feature has not yet been implemented");
    }

    @Override
    public void showCancel() {
        // Finish the Activity
        finish();
    }

    @Override
    public void informUser(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, message, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();
    }

    @Override
    public void moveToNextActivity(Class<?> activityClass) {
        // Start Activity, Passing Appointments, and Requesting Result
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

                    // Load the new appointments
                    parcelableAppointments.addAll(new ArrayList<>(data.<ParcelableAppointment>
                            getParcelableArrayListExtra("appointment")));
                }
            }
        }
    }
}
