package cs246.businesscalendar.view_presenter.appointment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs246.businesscalendar.R;

public class Appointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
    }

    public void clickCancel(View view) {
        finish();
    }
}
