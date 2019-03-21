package cs246.businesscalendar.view_presenter.weekly_calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs246.businesscalendar.R;
import cs246.businesscalendar.view_presenter.daily_calendar.DailyCalendarContract;

public class WeeklyCalendar extends AppCompatActivity implements WeeklyCalendarContract.View {
    private static final String TAG = "MonthlyCalendar";
    private WeeklyCalendarPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_calendar);

        // Set Buttons
        Button returnButton = findViewById(R.id.weeklyviewReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReturn();
            }
        });

        Button addButton = findViewById(R.id.weeklyviewAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdd();
            }
        });

        // Set Presenter
        presenter = new WeeklyCalendarPresenter();
    }

    public void showReturn() {
        finish();
    }

    public void showAdd() {
        finish();
    }
}
