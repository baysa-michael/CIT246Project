package cs246.businesscalendar.view_presenter.appointment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs246.businesscalendar.R;

public class Appointment extends AppCompatActivity implements AppointmentContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
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

    public void clickCancel(View view) {
        finish();
    }
}
