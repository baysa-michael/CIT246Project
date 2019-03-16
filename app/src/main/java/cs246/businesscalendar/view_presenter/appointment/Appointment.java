package cs246.businesscalendar.view_presenter.appointment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs246.businesscalendar.R;

public class Appointment extends AppCompatActivity implements AppointmentContract.View {
    private static final String TAG = "ModelAppointment";
    private AppointmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        // Set Buttons
        Button confirmButton = findViewById(R.id.appointmentConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirm();
            }
        });

        Button cancelButton = findViewById(R.id.appointmentCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancel();
            }
        });

        // Set Presenter
        presenter = new AppointmentPresenter();
    }

    public void showConfirm() {
        finish();
    }

    public void showCancel() {
        finish();
    }
}
