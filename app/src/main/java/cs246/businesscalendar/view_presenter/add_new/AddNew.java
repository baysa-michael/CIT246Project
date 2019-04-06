package cs246.businesscalendar.view_presenter.add_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.modify_appointment.ModifyAppointment;

public class AddNew extends AppCompatActivity implements AddNewContract.View {
    private static final String TAG = "AddNew";
    private AddNewPresenter presenter;

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
    public void showAddNewAppointment() {
        Intent thisIntent = new Intent(this, ModifyAppointment.class);

        startActivity(thisIntent);
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
        finish();
    }


    @Override
    public void informUser(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, message, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();
    }
}
