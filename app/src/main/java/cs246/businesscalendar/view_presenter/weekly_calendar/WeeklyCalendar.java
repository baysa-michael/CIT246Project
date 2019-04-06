package cs246.businesscalendar.view_presenter.weekly_calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
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
import cs246.businesscalendar.model.ParcelableAppointment;

public class WeeklyCalendar extends AppCompatActivity implements WeeklyCalendarContract.View {
    private static final String TAG = "MonthlyCalendar";
    private WeeklyCalendarPresenter presenter;
    private ScrollView sideVerticalScroll;
    private ScrollView mainVerticalScroll;
    private HorizontalScrollView topHorizontalScroll;
    private HorizontalScrollView mainHorizontalScroll;
    private int sideVerticalScrollPoint;
    private int topHorizontalScrollPoint;
    private int mainVerticalScrollPoint;
    private int mainHorizontalScrollPoint;
    private List<ParcelableAppointment> parcelableAppointments;

    // Request Codes
    private static final int GENERAL_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_calendar);

        // Set Buttons
        Button returnButton = findViewById(R.id.weeklycalendarReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReturn();
            }
        });

        Button addButton = findViewById(R.id.weeklycalendarAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdd();
            }
        });

        // Set Presenter
        presenter = new WeeklyCalendarPresenter();

        // Set Default Date to Today
        EditText dateEdit = findViewById(R.id.weeklycalendarDateEdit);
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
                EditText dateEdit = findViewById(R.id.weeklycalendarDateEdit);
                String retrieveYear = dateEdit.getText().toString().substring(0, 4);
                String retrieveMonth = dateEdit.getText().toString().substring(5, 7);
                String retrieveDay = dateEdit.getText().toString().substring(8, 10);

                int year = Integer.parseInt(retrieveYear);
                int month = Integer.parseInt(retrieveMonth);
                int day = Integer.parseInt(retrieveDay);

                // Create new Date Picker Dialog
                DatePickerDialog picker = new DatePickerDialog(WeeklyCalendar.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month,
                                                  int dayOfMonth) {
                                EditText dateEdit = findViewById(R.id.weeklycalendarDateEdit);
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

        // Link Scroll Views
        sideVerticalScroll = findViewById(R.id.weeklycalendarSideVerticalScroll);
        mainVerticalScroll = findViewById(R.id.weeklycalendarMainVerticalScroll);
        topHorizontalScroll =
                findViewById(R.id.weeklycalendarTopHorizontalScroll);
        mainHorizontalScroll =
                findViewById(R.id.weeklycalendarMainHorizontalScroll);

        // Identify Initial Scroll Points
        sideVerticalScrollPoint = sideVerticalScroll.getScrollY();
        mainVerticalScrollPoint = mainVerticalScroll.getScrollY();
        topHorizontalScrollPoint = topHorizontalScroll.getScrollX();
        mainHorizontalScrollPoint = mainHorizontalScroll.getScrollX();

        // Add Scroll Listeners
        sideVerticalScroll.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        verticalScroll(sideVerticalScroll);
                    }
                }
        );
        mainVerticalScroll.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        verticalScroll(mainVerticalScroll);
                    }
                }
        );
        topHorizontalScroll.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        horizontalScroll(topHorizontalScroll);
                    }
                }
        );
        mainHorizontalScroll.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        horizontalScroll(mainHorizontalScroll);
                    }
                }
        );
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

    /**
     * Change the calendar display between 12H and 24H
     *
     * <p>
     *     Based on the boolean parameter, this transitions the calendar display between 12 and 24 H
     * </p>
     * @param is24HTime True if time should be displayed in 24H format, false if it should be in 12H
     */
    public void display1224Time(boolean is24HTime) {
        ConstraintLayout targetConstraint = findViewById(R.id.weeklycalendarHourlyLayoutVertical);

        if (is24HTime) {
            // Formatting Strings
            DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");

            for (int i = 0; i < 24 ; i++) {
                // Construct Time Reference
                LocalTime insertTime = new LocalTime(i, 0);

                // Get Reference of Child in Calendar Layout
                ((TextView) targetConstraint.getChildAt(i))
                        .setText(insertTime.toString(formatTime));
            }
        } else {
            // Formatting Strings
            DateTimeFormatter formatTime = DateTimeFormat.forPattern("hh:mm a");

            for (int i = 0; i < 24; i++) {
                // Construct Time Reference - Need to skip extra child
                LocalTime insertTime = new LocalTime(i, 0);

                // Get Reference of Child in Calendar Layout
                ((TextView) targetConstraint.getChildAt(i))
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
     * @param weeklyAppointments List of List of Appointments for a given week
     */
    public void displayAppointments(List<List<Appointment>> weeklyAppointments,
                                    LocalDate startDate) {
        // Get the Containers
        ConstraintLayout weeklyContainer = findViewById(R.id.weeklycalendarHourlyLayoutHorizontal);
        ConstraintLayout dateHeaderContainer = findViewById(R.id.weeklycalendarDateRowHeader);

        // Loop for each day of week
        for (int i = 0; i < 7; i++) {
            // Get the container for the day
            ConstraintLayout dailyHeaderContainer =
                    (ConstraintLayout) dateHeaderContainer.getChildAt(i);

            // Add the Date field at the top of each day
            // Add the text for the text view
            LocalDate insertDate = startDate.plusDays(i);
            DateTimeFormatter formatDate = DateTimeFormat.forPattern("dd MMMM, yyyy");
            DateTimeFormatter formatDayofWeek = DateTimeFormat.forPattern("EEEE");

            TextView dateDisplay = (TextView) dailyHeaderContainer.getChildAt(0);
            TextView dayDisplay = (TextView) dailyHeaderContainer.getChildAt(1);
            dateDisplay.setText(insertDate.toString(formatDate));
            dayDisplay.setText(insertDate.toString(formatDayofWeek));

            // Clear the Appointment Container
            FrameLayout appointmentContainer = (FrameLayout) weeklyContainer.getChildAt(i);
            appointmentContainer.removeAllViews();

            // Add appointments
            for (int j = 0; j < weeklyAppointments.get(i).size(); j++) {
                Appointment thisAppointment = weeklyAppointments.get(i).get(j);

                // Create a New View Group
                LayoutInflater myInflater = LayoutInflater.from(this);
                View newView = myInflater.inflate(R.layout.appointment_layout, appointmentContainer,
                        false);
                ViewGroup appointment = (ViewGroup) newView;

                // Add the ID for the view group
                appointment.setId(i*j);

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

                // Set the Margins and Width
                FrameLayout.MarginLayoutParams layoutParameters = new FrameLayout.MarginLayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT
                        , pxHeight);
                layoutParameters.setMargins(0, pxTopMargin, 0, 0);
                appointment.setLayoutParams(layoutParameters);

                // Apply the margins
                appointment.requestLayout();

                // Add the completed view
                appointmentContainer.addView(appointment);
            }
        }

        // Update the view
        weeklyContainer.requestLayout();
    }

    /**
     * Update calendar display appointments for a given week
     *
     * <p>
     *     This method will take a target date and a 24H time period indicator and will update the
     *     view with the appropriate calendar display and appointment listings.
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
        List<List<Appointment>> weeklyAppointments = presenter
                .retrieveAppointmentsByWeek(convertedAppointments, updateDate);
        displayAppointments(weeklyAppointments, presenter.determineStartOfWeek(updateDate));
    }

    public void verticalScroll(ScrollView itemScrolled) {
        int scrollPoint = itemScrolled.getScrollY();

        if (sideVerticalScrollPoint != scrollPoint) {
            sideVerticalScroll.setScrollY(scrollPoint);
            sideVerticalScrollPoint = scrollPoint;
        }

        if (mainVerticalScrollPoint != scrollPoint) {
            mainVerticalScroll.setScrollY(scrollPoint);
            mainVerticalScrollPoint = scrollPoint;
        }
    }

    public void horizontalScroll(HorizontalScrollView itemScrolled) {
        int scrollPoint = itemScrolled.getScrollX();

        if (topHorizontalScrollPoint != scrollPoint) {
            topHorizontalScroll.setScrollX(scrollPoint);
            topHorizontalScrollPoint = scrollPoint;
        }

        if (mainHorizontalScrollPoint != scrollPoint) {
            mainHorizontalScroll.setScrollX(scrollPoint);
            mainHorizontalScrollPoint = scrollPoint;
        }
    }
}

