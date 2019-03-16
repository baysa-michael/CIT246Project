package cs246.businesscalendar.view_presenter.daily_calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs246.businesscalendar.R;

public class DailyCalendar extends AppCompatActivity implements DailyCalendarContract.View {
    private static final String TAG = "DailyCalendar";
    private DailyCalendarPresenter presenter;

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
    }

    public void showReturn() {
        finish();
    }

    public void showAdd() {
        finish();
    }
}
