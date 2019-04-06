package cs246.businesscalendar.view_presenter.monthly_calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cs246.businesscalendar.R;
import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.ParcelableAppointment;

public class MonthlyCalendar extends AppCompatActivity implements MonthlyCalendarContract.View {
    private static final String TAG = "MonthlyCalendar";
    private MonthlyCalendarPresenter presenter;
    private List<ParcelableAppointment> parcelableAppointments;

    // Request Codes
    private static final int GENERAL_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_calendar);

        // Set Buttons
        Button returnButton = findViewById(R.id.monthlycalendarReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReturn();
            }
        });

        Button addButton = findViewById(R.id.monthlycalendarAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdd();
            }
        });

        // Set Presenter
        presenter = new MonthlyCalendarPresenter();

        // Set Default Date to Today
        EditText dateEdit = findViewById(R.id.monthlycalendarDateEdit);
        LocalDate today = new LocalDate(LocalDate.now().getYear(),
                LocalDate.now().getMonthOfYear(),
                LocalDate.now().getDayOfMonth());
        dateEdit.setText(today.toString("yyyy-MM-dd, EEEE"));

        // Load Activity List
        parcelableAppointments = getIntent().getParcelableArrayListExtra("appointments");

        // Retrieve Default Appointments
        updateCalendar(today);

        // Prepare Date Picker
        dateEdit.setInputType(InputType.TYPE_NULL);
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current date in the field
                EditText dateEdit = findViewById(R.id.monthlycalendarDateEdit);
                String retrieveYear = dateEdit.getText().toString().substring(0, 4);
                String retrieveMonth = dateEdit.getText().toString().substring(5, 7);
                String retrieveDay = dateEdit.getText().toString().substring(8, 10);

                int year = Integer.parseInt(retrieveYear);
                int month = Integer.parseInt(retrieveMonth);
                int day = Integer.parseInt(retrieveDay);

                // Create new Date Picker Dialog
                DatePickerDialog picker = new DatePickerDialog(MonthlyCalendar.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month,
                                                  int dayOfMonth) {
                                EditText dateEdit = findViewById(R.id.monthlycalendarDateEdit);
                                // Set Selected Date
                                LocalDate selectedDate = new LocalDate(year, month + 1,
                                        dayOfMonth);

                                // Display Formatted Date
                                DateTimeFormatter formatTime =
                                        DateTimeFormat.forPattern("yyyy-MM-dd, EEEE");
                                dateEdit.setText(selectedDate.toString(formatTime));

                                // Update Appointments
                                updateCalendar(selectedDate);
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
        finish();
    }

    public void updateCalendar(LocalDate testDate) {
        // Retrieve lists of counts of appointments, tasks, and goals
        List<Appointment> convertedAppointments =
                presenter.convertParcelableAppointments(parcelableAppointments);
        Map<String, List<AtomicInteger>> monthlyData =
                presenter.retrieveBasicMonthlyInfo(convertedAppointments, testDate);

        // Clear Calendar
        clearCalendar();

        // Display the daily information
        displayDays(testDate, monthlyData);
    }

    public void displayDays(LocalDate testDate, Map<String, List<AtomicInteger>> monthlyData) {
        // Determine the day of week of the first day of the month
        LocalDate startOfMonth = new LocalDate(testDate.getYear(), testDate.getMonthOfYear(),
                1);
        int dayOfWeekCursor = startOfMonth.getDayOfWeek() - 1;

        // Determine the number of days in the month
        LocalDate endOfMonth = startOfMonth.dayOfMonth().withMaximumValue();
        int daysInMonth = Days.daysBetween(startOfMonth, endOfMonth).getDays() + 1;

        // Get handle on Monthly Calendar
        ConstraintLayout monthlyCalendar = findViewById(R.id.monthlycalendarCalendar);

        // Set Weekly Cursor and initial handles on appropriate constraint layouts
        int weekCursor = 0;
        ConstraintLayout weekOfMonth = (ConstraintLayout) monthlyCalendar.getChildAt(weekCursor);
        ConstraintLayout dayOfWeek = (ConstraintLayout) weekOfMonth.getChildAt(dayOfWeekCursor);

        // Loop through calendar and add daily information as appropriate
        for (int i = 0; i < daysInMonth; i++) {
            // Identify the Date of the Cell
            LocalDate insertDate = startOfMonth.plusDays(i);

            // Inflate Monthly View Cell
            // Create a New View Group
            LayoutInflater myInflater = LayoutInflater.from(this);
            View newView = myInflater.inflate(R.layout.monthly_view_cell, dayOfWeek,
                    false);
            ViewGroup dayDetails = (ViewGroup) newView;

            // Add the ID for the view group
            dayDetails.setId(i);

            // Prepare Date Formats
            DateTimeFormatter formatDayOfWeek = DateTimeFormat.forPattern("EE");
            DateTimeFormatter formatDayOfMonth = DateTimeFormat.forPattern("dd");

            // Prepare Data for Insert
            String appointmentCount;
            if (!monthlyData.containsKey("Appointments") ||
                    monthlyData.get("Appointments").get(i).get() < 1) {
                appointmentCount = "";
            } else {
                appointmentCount = "A: " + monthlyData.get("Appointments").get(i).get();
            }
            String taskCount;
            if (!monthlyData.containsKey("Tasks") || monthlyData.get("Tasks").get(i).get() < 1) {
                taskCount = "";
            } else {
                taskCount = "T: " + monthlyData.get("Tasks").get(i).get();
            }
            String goalCount;
            if (!monthlyData.containsKey("Goals") || monthlyData.get("Goals").get(i).get() < 1) {
                goalCount = "";
            } else {
                goalCount = "G: " + monthlyData.get("Goals").get(i).get();
            }

            // Add the text for the text view
            // Day of Week
            ((TextView) dayDetails.getChildAt(0))
                    .setText(insertDate.toString(formatDayOfWeek));
            // Day of Month
            ((TextView) dayDetails.getChildAt(1))
                    .setText(insertDate.toString(formatDayOfMonth));
            // Appointments
            ((TextView) dayDetails.getChildAt(2)).setText(appointmentCount);
            // Tasks
            ((TextView) dayDetails.getChildAt(3)).setText(taskCount);
            // Goals
            ((TextView) dayDetails.getChildAt(4)).setText(goalCount);

            // Set the Margins and Width
            ConstraintLayout.MarginLayoutParams layoutParameters =
                    new ConstraintLayout.MarginLayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT
                    , ConstraintLayout.LayoutParams.MATCH_PARENT);
            layoutParameters.setMargins(0, 0, 0, 0);
            dayDetails.setLayoutParams(layoutParameters);

            // Apply the margins
            dayDetails.requestLayout();

            // Add the completed view
            dayOfWeek.addView(dayDetails);

            // Increment dayOfWeek Cursor, wrap to 1 when it increments to 7
            if (dayOfWeekCursor == 6) {
                // Update Cursors
                dayOfWeekCursor = 0;
                weekCursor++;

                // Update Handles
                weekOfMonth = (ConstraintLayout) monthlyCalendar.getChildAt(weekCursor);
                dayOfWeek = (ConstraintLayout) weekOfMonth.getChildAt(dayOfWeekCursor);
            } else {
                dayOfWeekCursor++;
                dayOfWeek = (ConstraintLayout) weekOfMonth.getChildAt(dayOfWeekCursor);
            }
        }
    }

    public void clearCalendar() {
        // Get handle on Monthly Calendar
        ConstraintLayout monthlyCalendar = findViewById(R.id.monthlycalendarCalendar);

        // Clear inner content of each day on the calendar
        for (int i = 0; i < 6; i++) {
            // Get handle on week of month
            ConstraintLayout weekOfMonth = (ConstraintLayout) monthlyCalendar.getChildAt(i);

            for (int j = 0; j < 7; j++) {
                // Clear Daily Contents
                ((ConstraintLayout) weekOfMonth.getChildAt(j)).removeAllViews();
            }
        }
    }
}
