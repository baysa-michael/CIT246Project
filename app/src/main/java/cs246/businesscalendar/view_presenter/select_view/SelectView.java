package cs246.businesscalendar.view_presenter.select_view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.R;

import cs246.businesscalendar.model.ParcelableAppointment;
import cs246.businesscalendar.view_presenter.daily_calendar.DailyCalendar;
import cs246.businesscalendar.view_presenter.monthly_calendar.MonthlyCalendar;
import cs246.businesscalendar.view_presenter.weekly_calendar.WeeklyCalendar;

public class SelectView extends AppCompatActivity implements SelectViewContract.View {
    private static final String TAG = "SelectView";
    private SelectViewPresenter presenter;
    private List<ParcelableAppointment> parcelableAppointments;

    // Request Codes
    private static final int GENERAL_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_view);

        // Set Buttons
        Button monthlyViewButton = findViewById(R.id.selectviewMonthlyViewButton);
        monthlyViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMonthlyView();
            }
        });

        Button weeklyViewButton = findViewById(R.id.selectviewWeeklyViewButton);
        weeklyViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWeeklyView();
            }
        });

        Button dailyViewButton = findViewById(R.id.selectviewDailyViewButton);
        dailyViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDailyView();
            }
        });

        Button cancelButton = findViewById(R.id.selectviewCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancel();
            }
        });

        // Set Presenter
        presenter = new SelectViewPresenter();

        // Load Activity List
        parcelableAppointments = getIntent().getParcelableArrayListExtra("appointments");
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

    public void showMonthlyView() {
        moveToNextActivity(MonthlyCalendar.class);
    }

    public void showWeeklyView() {
        moveToNextActivity(WeeklyCalendar.class);
    }

    public void showDailyView() {
        moveToNextActivity(DailyCalendar.class);
    }

    public void showCancel() {
        finish();
    }

    @Override
    public void moveToNextActivity(Class<?> activityClass) {
        // Start Activity, Passing Appointments, and Requesting Result
        Intent thisIntent = new Intent(this, activityClass);
        thisIntent.putParcelableArrayListExtra("appointments",
                (ArrayList<ParcelableAppointment>) parcelableAppointments);
        startActivityForResult(thisIntent, GENERAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test to make sure the proper request code is received
        if (requestCode == GENERAL_REQUEST_CODE) {
            // Make sure that the request return results are OK
            if (resultCode == RESULT_OK) {
                // Retrieve the updated appointments
                if (data != null) {
                    // Clear any old data in the appointments
                    parcelableAppointments.clear();

                    // Load the new appointments
                    parcelableAppointments.addAll(new ArrayList<>(data.<ParcelableAppointment>
                            getParcelableArrayListExtra("appointment")));
                }
            }
        }
    }

}
