package cs246.businesscalendar.view_presenter.daily_calendar;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
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
    private RecyclerView myRecycler;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    EditText dateEdit;

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
                        }, year, month, day);
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



        // Set Recycler View with Layout Manager and Adapter
        myRecycler = findViewById(R.id.dailyviewRecyclerView);
        myRecycler.setHasFixedSize(true);
        myRecycler.addItemDecoration(new DividerItemDecoration(myRecycler.getContext(),
                DividerItemDecoration.VERTICAL));
        myLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(myLayoutManager);
        myAdapter = new DailyCalendarRecyclerViewAdapter(this, timeBlocks,
                dailyAppointments);
        myRecycler.setAdapter(myAdapter);
    }

    public void showReturn() {
        finish();
    }

    public void showAdd() {
        finish();
    }
}
