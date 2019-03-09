package cs246.businesscalendar.view_presenter.add_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.appointment.Appointment;

public class AddNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
    }

    public void clickAddNewAppointment(View view) {
        Intent thisIntent = new Intent(this, Appointment.class);

        startActivity(thisIntent);
    }

    public void clickCancel(View view) {
        finish();
    }
}
