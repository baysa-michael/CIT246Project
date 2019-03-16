package cs246.businesscalendar.view_presenter.select_view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.daily_calendar.DailyCalendar;
import cs246.businesscalendar.view_presenter.monthly_calendar.MonthlyCalendar;
import cs246.businesscalendar.view_presenter.weekly_calendar.WeeklyCalendar;

public class SelectView extends AppCompatActivity implements SelectViewContract.View {
    private static final String TAG = "SelectView";
    private SelectViewPresenter presenter;

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
    }

    public void showMonthlyView() {
        Intent thisIntent = new Intent(this, MonthlyCalendar.class);
        startActivity(thisIntent);
    }

    public void showWeeklyView() {
        Intent thisIntent = new Intent(this, WeeklyCalendar.class);
        startActivity(thisIntent);
    }

    public void showDailyView() {
        Intent thisIntent = new Intent(this, DailyCalendar.class);
        startActivity(thisIntent);
    }

    public void showCancel() {
        finish();
    }
}
