package cs246.businesscalendar.view_presenter.modify_appointment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.R;
import cs246.businesscalendar.model.ParcelableAppointment;
import cs246.businesscalendar.view_presenter.daily_calendar.DailyCalendar;

public class ModifyAppointment extends AppCompatActivity implements ModifyAppointmentContract.View {
    private static final String TAG = "Appointment";
    private ModifyAppointmentPresenter presenter;
    private List<ParcelableAppointment> parcelableAppointments;
    private ParcelableAppointment targetAppointment;

    // Request Codes
    private static final int GENERAL_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_appointment);

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
        presenter = new ModifyAppointmentPresenter();

        // Load Activity List
        parcelableAppointments = getIntent().getParcelableArrayListExtra("appointments");

        // Load Target Appointment
        String testHash = getIntent().getStringExtra("hash");
        if (testHash.equals("None")) {
            targetAppointment = new ParcelableAppointment();
        } else {
            // Load the target appointment
            for (ParcelableAppointment testAppointment : parcelableAppointments) {
                if (testAppointment.getHash().equals(testHash)) {
                    targetAppointment = testAppointment;
                }
            }

            // If no appointments with a matching hash are found, exit activity
            if (targetAppointment.getHash() == null) {
                finish();
                return;
            }
        }

        // Load Appointment Data if exists
        EditText dateEdit = findViewById(R.id.appointmentDateEdit);
        EditText startEdit = findViewById(R.id.appointmentStartTimeEdit);
        EditText endEdit = findViewById(R.id.appointmentEndTimeEdit);
        LocalDate appointmentDate = new LocalDate();
        LocalTime appointmentStart = new LocalTime();
        LocalTime appointmentEnd = new LocalTime();

        if (targetAppointment.getHash().equals("")) {
            // Set Default Appointment Date and Time
            appointmentDate = new LocalDate(LocalDate.now().getYear(),
                    LocalDate.now().getMonthOfYear(),
                    LocalDate.now().getDayOfMonth());
            appointmentStart = new LocalTime(LocalTime.now().getHourOfDay(),
                    LocalTime.now().getMinuteOfHour());
            appointmentEnd = new LocalTime(LocalTime.now().getHourOfDay(),
                    LocalTime.now().getMinuteOfHour());

            // Load remaining appointment information
            ((CheckBox) findViewById(R.id.appointmentAllDayCheckbox))
                    .setChecked(targetAppointment.isAllDay());
            ((EditText) findViewById(R.id.appointmentTitleEdit))
                    .setText(targetAppointment.getTitle());
            ((EditText) findViewById(R.id.appointmentDescriptionEdit))
                    .setText(targetAppointment.getDescription());
            ((EditText) findViewById(R.id.appointmentLocationEdit))
                    .setText(targetAppointment.getLocation());
            ((EditText) findViewById(R.id.appointmentAttendeesEdit))
                    .setText(targetAppointment.getAttendees());
        } else {
            appointmentDate = new LocalDate(targetAppointment.getYear(),
                    targetAppointment.getMonth(), targetAppointment.getDay());
        }

        // Set Default Date
        dateEdit.setText(appointmentDate.toString("yyyy-MM-dd, EEEE"));
        DateTimeFormatter formatTime = DateTimeFormat.forPattern("hh:mm a");
        startEdit.setText(appointmentStart.toString("hh:mm a"));
        endEdit.setText(appointmentEnd.toString("hh:mm a"));

        // Prepare Date Picker
        dateEdit.setInputType(InputType.TYPE_NULL);
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current date in the field
                EditText dateEdit = findViewById(R.id.appointmentDateEdit);
                String retrieveYear = dateEdit.getText().toString().substring(0, 4);
                String retrieveMonth = dateEdit.getText().toString().substring(5, 7);
                String retrieveDay = dateEdit.getText().toString().substring(8, 10);

                int year = Integer.parseInt(retrieveYear);
                int month = Integer.parseInt(retrieveMonth);
                int day = Integer.parseInt(retrieveDay);

                // Create new Date Picker Dialog
                DatePickerDialog picker = new DatePickerDialog(ModifyAppointment.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month,
                                                  int dayOfMonth) {
                                EditText dateEdit = findViewById(R.id.appointmentDateEdit);
                                // Set Selected Date
                                LocalDate selectedDate = new LocalDate(year, month + 1, dayOfMonth);

                                // Display Formatted Date
                                DateTimeFormatter formatTime =
                                        DateTimeFormat.forPattern("yyyy-MM-dd, EEEE");
                                dateEdit.setText(selectedDate.toString(formatTime));
                            }
                        }, year, month - 1, day);
                picker.show();
            }
        });
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

    public void showConfirm() {
        // Gather User Input


        finish();
    }

    public void showCancel() {
        finish();
    }
}
