package cs246.businesscalendar.view_presenter.modify_appointment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cs246.businesscalendar.R;
import cs246.businesscalendar.controller.database_controller.FirestoreAddAppointmentListenerInterface;
import cs246.businesscalendar.model.ParcelableAppointment;
import cs246.businesscalendar.utilities.Hashing;
import cs246.businesscalendar.view_presenter.daily_calendar.DailyCalendar;

public class ModifyAppointment extends AppCompatActivity implements ModifyAppointmentContract.View,
        FirestoreAddAppointmentListenerInterface {
    private static final String TAG = "Appointment";
    private ModifyAppointmentPresenter presenter;
    private List<ParcelableAppointment> parcelableAppointments;
    private ParcelableAppointment targetAppointment;
    private ProgressBar indeterminateProgressBar;

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
        presenter = new ModifyAppointmentPresenter(this);

        // Load Activity List
        parcelableAppointments = getIntent().getParcelableArrayListExtra("appointments");

        // Load Target Appointment
        String testHash = getIntent().getStringExtra("hash");
        if (testHash == null) {
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

        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar = findViewById(R.id.appointmentIndeterminateProgress);
        indeterminateProgressBar.setVisibility(View.GONE);

        // Load Appointment Data if exists
        EditText dateEdit = findViewById(R.id.appointmentDateEdit);
        EditText startEdit = findViewById(R.id.appointmentStartTimeEdit);
        EditText endEdit = findViewById(R.id.appointmentEndTimeEdit);
        LocalDate appointmentDate;
        LocalTime appointmentStart;
        LocalTime appointmentEnd;

        if (targetAppointment != null && testHash != null) {
            // Set Appointment Date and Time
            appointmentDate = new LocalDate(targetAppointment.getYear(),
                    targetAppointment.getMonth() + 1,
                    targetAppointment.getDay() + 1);
            appointmentStart = new LocalTime(targetAppointment.getStartHour(),
                    targetAppointment.getStartMinute());
            appointmentEnd = new LocalTime(targetAppointment.getEndHour(),
                    targetAppointment.getEndMinute());

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
            // Set Default Appointment Date and Time
            appointmentDate = new LocalDate(LocalDate.now().getYear(),
                    LocalDate.now().getMonthOfYear(),
                    LocalDate.now().getDayOfMonth());
            appointmentStart = new LocalTime(LocalTime.now().getHourOfDay(),
                    LocalTime.now().getMinuteOfHour());
            appointmentEnd = new LocalTime(LocalTime.now().getHourOfDay(),
                    LocalTime.now().getMinuteOfHour());
        }

        // Set Default Date
        dateEdit.setText(appointmentDate.toString("yyyy-MM-dd, EEEE"));
        DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");
        startEdit.setText(appointmentStart.toString(formatTime));
        endEdit.setText(appointmentEnd.toString(formatTime));

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
                                LocalDate selectedDate = new LocalDate(year, month + 1,
                                        dayOfMonth);

                                // Display Formatted Date
                                DateTimeFormatter formatTime =
                                        DateTimeFormat.forPattern("yyyy-MM-dd, EEEE");
                                dateEdit.setText(selectedDate.toString(formatTime));
                            }
                        }, year, month - 1, day);
                picker.show();
            }
        });

        // Prepare Start Time Picker
        startEdit.setInputType(InputType.TYPE_NULL);
        startEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText startEdit = findViewById(R.id.appointmentStartTimeEdit);
                String retrieveHour = startEdit.getText().toString().substring(0, 2);
                String retrieveMinute = startEdit.getText().toString().substring(3, 5);

                int hour = Integer.parseInt(retrieveHour);
                int minute =Integer.parseInt(retrieveMinute);

                // Create a New Time Picker Dialog
                TimePickerDialog picker = new TimePickerDialog(ModifyAppointment.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            EditText startEdit = findViewById(R.id.appointmentStartTimeEdit);

                            // Set Selected Time
                            LocalTime selectedTime = new LocalTime(hourOfDay, minute);

                            // Display Format Time
                            DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");
                            startEdit.setText(selectedTime.toString(formatTime));
                        }
                    }
                    , hour, minute, true);
                picker.show();
            }
        });

        // Prepare End Time Picker
        endEdit.setInputType(InputType.TYPE_NULL);
        endEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText endEdit = findViewById(R.id.appointmentEndTimeEdit);
                String retrieveHour = endEdit.getText().toString().substring(0, 2);
                String retrieveMinute = endEdit.getText().toString().substring(3, 5);

                int hour = Integer.parseInt(retrieveHour);
                int minute =Integer.parseInt(retrieveMinute);

                // Create a New Time Picker Dialog
                TimePickerDialog picker = new TimePickerDialog(ModifyAppointment.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                EditText endEdit = findViewById(R.id.appointmentEndTimeEdit);

                                // Set Selected Time
                                LocalTime selectedTime = new LocalTime(hourOfDay, minute);

                                // Display Format Time
                                DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");
                                endEdit.setText(selectedTime.toString(formatTime));
                            }
                        }
                        , hour, minute, true);
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
        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar.setVisibility(View.VISIBLE);

        // Gather User Input
        EditText dateEdit = findViewById(R.id.appointmentDateEdit);
        String retrieveYear = dateEdit.getText().toString().substring(0, 4);
        String retrieveMonth = dateEdit.getText().toString().substring(5, 7);
        String retrieveDay = dateEdit.getText().toString().substring(8, 10);
        int year = Integer.parseInt(retrieveYear);
        int month = Integer.parseInt(retrieveMonth);
        int day = Integer.parseInt(retrieveDay);

        boolean isAllDay = ((CheckBox) findViewById(R.id.appointmentAllDayCheckbox)).isChecked();

        EditText startEdit = findViewById(R.id.appointmentStartTimeEdit);
        String retrieveStartHour = startEdit.getText().toString().substring(0, 2);
        String retrieveStartMinute = startEdit.getText().toString().substring(3, 5);
        int startHour = Integer.parseInt(retrieveStartHour);
        int startMinute =Integer.parseInt(retrieveStartMinute);

        EditText endEdit = findViewById(R.id.appointmentEndTimeEdit);
        String retrieveEndHour = endEdit.getText().toString().substring(0, 2);
        String retrieveEndMinute = endEdit.getText().toString().substring(3, 5);
        int endHour = Integer.parseInt(retrieveEndHour);
        int endMinute =Integer.parseInt(retrieveEndMinute);

        String title = ((EditText) findViewById(R.id.appointmentTitleEdit)).getText().toString();
        String description = ((EditText) findViewById(R.id.appointmentDescriptionEdit))
                .getText().toString();
        String location = ((EditText) findViewById(R.id.appointmentLocationEdit))
                .getText().toString();
        String attendees = ((EditText) findViewById(R.id.appointmentAttendeesEdit))
                .getText().toString();

        // If hash does not already exist, create a new hash
        String hash;
        String testHash = getIntent().getStringExtra("hash");
        if (testHash == null) {
            Random r = new Random();
            int salt = r.nextInt();
            String appointmentData = Integer.toString(year) +
                    Integer.toString(month) +
                    Integer.toString(day) +
                    Boolean.toString(isAllDay) +
                    Integer.toString(startHour) +
                    Integer.toString(startMinute) +
                    Integer.toString(endHour) +
                    Integer.toString(endMinute) +
                    title +
                    description +
                    location +
                    attendees +
                    Integer.toString(salt);
            try {
                hash = Hashing.calculateHash(appointmentData);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else {
            hash = testHash;
        }

        // Create a new Parcelable Appointment from data
        ParcelableAppointment newAppointment = new ParcelableAppointment(
                year,
                month,
                day,
                isAllDay,
                startHour,
                startMinute,
                endHour,
                endMinute,
                title,
                description,
                location,
                attendees,
                hash
        );

        // Add the appointment to the database
        presenter.addNewAppointment(newAppointment);
    }

    public void showCancel() {
        finish();
    }

    @Override
    public void onAddAppointmentSuccess(ParcelableAppointment newAppointment) {
        // Add the parcelable appointment to the list
        parcelableAppointments.add(newAppointment);

        // Clear Non-Time Fields
        ((CheckBox) findViewById(R.id.appointmentAllDayCheckbox)).setChecked(false);
        ((EditText) findViewById(R.id.appointmentTitleEdit)).getText().clear();
        ((EditText) findViewById(R.id.appointmentDescriptionEdit)).getText().clear();
        ((EditText) findViewById(R.id.appointmentLocationEdit)).getText().clear();
        ((EditText) findViewById(R.id.appointmentAttendeesEdit)).getText().clear();

        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar.setVisibility(View.GONE);

        // Inform the user
        informUser("Successfully Added Appointment");

        finish();
    }

    @Override
    public void onAddAppointmentFailure() {
        // Set Indeterminate Progress Bar to Gone
        indeterminateProgressBar.setVisibility(View.GONE);

        // Inform the user
        informUser("ERROR:  Unable to Add Appointment");
    }

    @Override
    public void informUser(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, message, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();
    }
}
