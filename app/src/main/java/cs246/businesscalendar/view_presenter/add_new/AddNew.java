package cs246.businesscalendar.view_presenter.add_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void showAddNewAppointment() {
        Intent thisIntent = new Intent(this, ModifyAppointment.class);

        startActivity(thisIntent);
    }

    public void showAddNewTask() {
        finish();
    }

    public void showAddNewGoal() {
        finish();
    }

    public void showJoinGroup() {
        finish();
    }

    public void showCreateGroup() {
        finish();
    }

    public void showCancel() {
        finish();
    }
}
