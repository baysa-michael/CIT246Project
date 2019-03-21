package cs246.businesscalendar.view_presenter.daily_calendar;

import android.app.DatePickerDialog;
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

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.R;
import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.utilities.DailyTimeBlocks;
import cs246.businesscalendar.utilities.TestItems;

public class DailyCalendar extends AppCompatActivity implements DailyCalendarContract.View {
    private static final String TAG = "DailyCalendar";
    private DailyCalendarPresenter presenter;
    private EditText dateEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_calendar);

        // Set Buttons
        Button returnButton = findViewById(R.id.dailyviewReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReturn();
            }
        });

        Button addButton = findViewById(R.id.dailyviewAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdd();
            }
        });

        // Set Presenter
        presenter = new DailyCalendarPresenter();

        // Prepare Date Picker
        dateEdit = findViewById(R.id.dailyviewDateEdit);
        dateEdit.setInputType(InputType.TYPE_NULL);
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the default date as the current date
                LocalDate currentDate = LocalDate.now();
                int year = currentDate.getYear();
                int month = currentDate.getMonthOfYear();
                int day = currentDate.getDayOfMonth();

                // Create new Date Picker Dialog
                DatePickerDialog picker = new DatePickerDialog(DailyCalendar.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month,
                                                  int dayOfMonth) {
                                LocalDate selectedDate = new LocalDate(year, month, dayOfMonth);
                                DateTimeFormatter formatTime =
                                        DateTimeFormat.forPattern("yyyy-MM-dd, EEEE");
                                dateEdit.setText(selectedDate.toString(formatTime));
                            }
                        }, year, month - 1, day);
                picker.show();
            }
        });


        // ******************* MOVE THIS SECTION TO PRESENTER WHEN READY **********************
        // Retrieve Daily Time Slices
        List<LocalTime> timeBlocks = DailyTimeBlocks.dailyCalendarSetup();

        // Retrieve list of appointments for the day *********** LINK TO REAL DATA ************
        List<Appointment> dailyAppointments = new ArrayList<>();
        List<Appointment> testAppointments = TestItems.testAppointments();
        for(Appointment thisAppointment : testAppointments) {
            if (thisAppointment.getAppointmentDate().equals(
                    new LocalDate(2019, 3, 16))) {
                dailyAppointments.add(thisAppointment);
            }
        }

        // ************************************************************************************


        // Set Times in Constraint View
        set1224Time(false);

        // Add Appointments
        addAppointments(dailyAppointments);
    }

    public void showReturn() {
        finish();
    }

    public void showAdd() {
        finish();
    }

    public void set1224Time(boolean is24HTime) {
        ConstraintLayout targetConstraint = findViewById(R.id.dailycalendarHourlyLayout);

        if (is24HTime) {
            // Formatting Strings
            DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");

            for (int i = 0; i < targetConstraint.getChildCount() ; i++) {
                // Construct Time Reference
                LocalTime insertTime = new LocalTime(i, 0);

                // Get Reference of Child in Calendar Layout
                ((TextView) targetConstraint.getChildAt(i)).setText(insertTime.toString(formatTime));
            }
        } else {
            // Formatting Strings
            DateTimeFormatter formatTime = DateTimeFormat.forPattern("KK:mm a");

            for (int i = 0; i < targetConstraint.getChildCount() ; i++) {
                // Construct Time Reference
                LocalTime insertTime = new LocalTime(i, 0);

                // Get Reference of Child in Calendar Layout
                ((TextView) targetConstraint.getChildAt(i)).setText(insertTime.toString(formatTime));
            }
        }
    }

    public void addAppointments(List<Appointment> dailyAppointments) {
        // Get the Appointment Container
        FrameLayout appointmentContainer = findViewById(R.id.dailycalendarAppointmentLayout);

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
                    .setText(thisAppointment.getAppointmentTitle());
            ((TextView) appointment.getChildAt(1))
                    .setText(thisAppointment.getAppointmentDescription());


            // Identify the height to be used for the appointment
            Resources resource = this.getResources();
            int minutes = Minutes.minutesBetween(thisAppointment.getAppointmentStart(),
                    thisAppointment.getAppointmentEnd()).getMinutes();
            int pxHeight = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    minutes,
                    resource.getDisplayMetrics()
            );


            // Identify the Top and Left margin to be used, and convert to pixels
            int startMinute = Minutes.minutesBetween(new LocalTime(0, 0),
                    thisAppointment.getAppointmentStart()).getMinutes();
            int pxTopMargin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    startMinute,
                    resource.getDisplayMetrics()
            );
            int startMargin = 60;
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

            Log.i(TAG, "New View Created - Minutes:  " + minutes
            + " - Title:  " + thisAppointment.getAppointmentTitle()
            + " - Height:  " + pxHeight
            + " - Top Margin:  " + pxTopMargin
            + " - Start Margin:  " + pxStartMargin);

            // Add the completed view
            appointmentContainer.addView(appointment);
        }

        // Update the view
        appointmentContainer.requestLayout();

        Log.i(TAG, "Total Children for AppointmentLayout Constraint View:  " +
                appointmentContainer.getChildCount());
        FrameLayout.LayoutParams thisLayout = (FrameLayout.LayoutParams) appointmentContainer.getChildAt(0).getLayoutParams();
        FrameLayout.LayoutParams thatLayout = (FrameLayout.LayoutParams) appointmentContainer.getChildAt(appointmentContainer.getChildCount() - 1).getLayoutParams();
        Log.i(TAG, "Top Margin for Child 1:  " + thisLayout.topMargin);
        Log.i(TAG, "Top Margin for Last Child:  " + thatLayout.topMargin);
    }
}
