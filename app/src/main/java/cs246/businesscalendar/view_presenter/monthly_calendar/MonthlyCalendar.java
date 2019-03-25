package cs246.businesscalendar.view_presenter.monthly_calendar;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import cs246.businesscalendar.R;

public class MonthlyCalendar extends AppCompatActivity implements MonthlyCalendarContract.View {
    private static final String TAG = "MonthlyCalendar";
    private MonthlyCalendarPresenter presenter;

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

    public void showReturn() {
        finish();
    }

    public void showAdd() {
        finish();
    }

    public void updateCalendar(LocalDate testDate) {
        // Determine the day of week of the first day of the month
        LocalDate startOfMonth = new LocalDate(testDate.getYear(), testDate.getMonthOfYear(), 1);
        int startOfMonthDayOfWeek = startOfMonth.getDayOfWeek();

        // Determine the number of days in the month


        // For each day of the month, inflate a Monthly View Cell
        // Populate each view cell with the Day of Week, Day of Month,
        // Appointment Count, Task Count, and Goal Count


    }
}
