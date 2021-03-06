package cs246.businesscalendar.view_presenter.daily_calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.R;
import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.ParcelableAppointment;

/**
 *  View for DailyCalendar Package
 *  <p>
 *  This class serves as the view in the MVP model for the DailyCalendar Package, with appropriate
 *  methods included as a part of this class.
 *  </p>
 *
 * @author Michael Baysa
 * @version 2019.03.20
 * @since 1.0
 */
public class DailyCalendar extends AppCompatActivity implements DailyCalendarContract.View {
    private static final String TAG = "DailyCalendar";
    /**
     * A handle to the presenter for the DailyCalendar Package
     */
    private DailyCalendarPresenter presenter;
    private List<ParcelableAppointment> parcelableAppointments;

    // Request Codes
    private static final int GENERAL_REQUEST_CODE = 0;

    /**
     * Creation Cycle of the DailyCalendar Activity
     *
     * <p>
     *     This method controls the On Create portion of the DailyCalendar Activity Lifecycle
     * </p>
     * @param savedInstanceState Instance Identified in On Create state of activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_calendar);

        // Set Buttons
        Button returnButton = findViewById(R.id.dailycalendarReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReturn();
            }
        });

        Button addButton = findViewById(R.id.dailycalendarAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdd();
            }
        });

        // Set Presenter
        presenter = new DailyCalendarPresenter();

        // Set Default Date to Today
        EditText dateEdit = findViewById(R.id.dailycalendarDateEdit);
        LocalDate today = new LocalDate(LocalDate.now().getYear(),
                LocalDate.now().getMonthOfYear(),
                LocalDate.now().getDayOfMonth());
        dateEdit.setText(today.toString("yyyy-MM-dd, EEEE"));

        // Load Activity List
        parcelableAppointments = getIntent().getParcelableArrayListExtra("appointments");

        // Retrieve Default Appointments
        updateCalendar(today, false);

        // Prepare Date Picker
        dateEdit.setInputType(InputType.TYPE_NULL);
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current date in the field
                EditText dateEdit = findViewById(R.id.dailycalendarDateEdit);
                String retrieveYear = dateEdit.getText().toString().substring(0, 4);
                String retrieveMonth = dateEdit.getText().toString().substring(5, 7);
                String retrieveDay = dateEdit.getText().toString().substring(8, 10);

                int year = Integer.parseInt(retrieveYear);
                int month = Integer.parseInt(retrieveMonth);
                int day = Integer.parseInt(retrieveDay);

                // Create new Date Picker Dialog
                DatePickerDialog picker = new DatePickerDialog(DailyCalendar.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month,
                                                  int dayOfMonth) {
                                EditText dateEdit = findViewById(R.id.dailycalendarDateEdit);
                                // Set Selected Date
                                LocalDate selectedDate = new LocalDate(year, month + 1,
                                        dayOfMonth);

                                // Display Formatted Date
                                DateTimeFormatter formatTime =
                                        DateTimeFormat.forPattern("yyyy-MM-dd, EEEE");
                                dateEdit.setText(selectedDate.toString(formatTime));

                                // Update Appointments
                                updateCalendar(selectedDate, false);
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

    public void showReturn() {
        finish();
    }

    public void showAdd() {
        informUser("NOTE:  Function not yet implemented - Please add appointments through the Add New Appointment view");
    }

    /**
     * Change the calendar display between 12H and 24H
     *
     * <p>
     *     Based on the boolean parameter, this transitions the calendar display between 12 and 24 H
     * </p>
     * @param is24HTime True if time should be displayed in 24H format, false if it should be in 12H
     */
    public void display1224Time(boolean is24HTime) {
        ConstraintLayout targetConstraint = findViewById(R.id.dailycalendarHourlyLayout);

        if (is24HTime) {
            // Formatting Strings
            DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");

            for (int i = 0; i < 24 ; i++) {
                // Construct Time Reference
                LocalTime insertTime = new LocalTime(i, 0);

                // Get Reference of Child in Calendar Layout
                ((TextView) targetConstraint.getChildAt(i + (i > 0 ? 1 : 0)))
                        .setText(insertTime.toString(formatTime));
            }
        } else {
            // Formatting Strings
            DateTimeFormatter formatTime = DateTimeFormat.forPattern("hh:mm a");

            for (int i = 0; i < 24; i++) {
                // Construct Time Reference - Need to skip extra child
                LocalTime insertTime = new LocalTime(i, 0);

                // Get Reference of Child in Calendar Layout
                ((TextView) targetConstraint.getChildAt(i + (i > 0 ? 1 : 0)))
                        .setText(insertTime.toString(formatTime));
            }
        }
    }

    /**
     * Position and Display Appointments
     *
     * <p>
     *     This method takes a list of appointments and positions them on the calendar according to
     *     the appointment time.
     * </p>
     * @param dailyAppointments List of appointments for a given day
     */
    public void displayAppointments(List<Appointment> dailyAppointments) {
        // Get the Appointment Container
        FrameLayout appointmentContainer = findViewById(R.id.dailycalendarAppointmentLayout);

        // Clear all current views
        appointmentContainer.removeAllViews();

        // Add appointments
        for(int i = 0; i < dailyAppointments.size(); i++) {
            Appointment thisAppointment = dailyAppointments.get(i);

            // Create a New View Group
            LayoutInflater myInflater = LayoutInflater.from(this);
            View newView = myInflater.inflate(R.layout.appointment_layout, appointmentContainer,
                    false);
            ViewGroup appointment = (ViewGroup) newView;

            // Add the ID for the view group
            appointment.setId(i);

            // Add the text for the text view
            ((TextView) appointment.getChildAt(0))
                    .setText(thisAppointment.getTitle());
            ((TextView) appointment.getChildAt(1))
                    .setText(thisAppointment.getDescription());


            // Identify the height to be used for the appointment
            Resources resource = this.getResources();
            int minutes = Minutes.minutesBetween(thisAppointment.getStart(),
                    thisAppointment.getEnd()).getMinutes();
            int pxHeight = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    minutes,
                    resource.getDisplayMetrics()
            );


            // Identify the Top and Left margin to be used, and convert to pixels
            int startMinute = Minutes.minutesBetween(new LocalTime(0, 0),
                    thisAppointment.getStart()).getMinutes();
            int pxTopMargin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    startMinute,
                    resource.getDisplayMetrics()
            );
            int startMargin = 5;
            int pxStartMargin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    startMargin,
                    resource.getDisplayMetrics()
            );

            // Set the Margins and Width
            FrameLayout.MarginLayoutParams layoutParameters = new FrameLayout.MarginLayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT
                    , pxHeight);
            layoutParameters.setMargins(pxStartMargin, pxTopMargin, 0, 0);
            appointment.setLayoutParams(layoutParameters);

            // Apply the margins
            appointment.requestLayout();

            // Add the completed view
            appointmentContainer.addView(appointment);
        }

        // Update the view
        appointmentContainer.requestLayout();
    }

    /**
     * Update calendar display appointments for a given day
     *
     * <p>
     *     This method will take a target date and a 24H time period indicator and will update the
     *     view with the appropriate calendar display and appointment listing.
     * </p>
     * @param updateDate Selected date used to retrieve the appropriate appointment list
     * @param is24HTime Selection of either 24H time (true) or 12H time (false)
     */
    public void updateCalendar(LocalDate updateDate, boolean is24HTime) {
        // Set Times in Constraint View
        display1224Time(is24HTime);

        // Retrieve and Add Daily Appointments
        List<Appointment> convertedAppointments =
                presenter.convertParcelableAppointments(parcelableAppointments);
        List<Appointment> dailyAppointments =
                presenter.retrieveAppointmentsByDay(convertedAppointments, updateDate);
        displayAppointments(dailyAppointments);
    }

    @Override
    public void informUser(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, message, duration);
        toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
        toastSuccessful.show();
    }
}