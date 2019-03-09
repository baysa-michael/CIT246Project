package cs246.businesscalendar.view_presenter.select_view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.daily_calendar.DailyCalendar;
import cs246.businesscalendar.view_presenter.monthly_calendar.MonthlyCalendar;
import cs246.businesscalendar.view_presenter.weekly_calendar.WeeklyCalendar;

public class SelectView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_view);
    }

    public void clickMonthly(View view) {
        Intent thisIntent = new Intent(this, MonthlyCalendar.class);
        startActivity(thisIntent);
    }

    public void clickWeekly(View view) {
        Intent thisIntent = new Intent(this, WeeklyCalendar.class);
        startActivity(thisIntent);
    }

    public void clickDaily(View view) {
        Intent thisIntent = new Intent(this, DailyCalendar.class);
        startActivity(thisIntent);
    }

    public void clickCancel(View view) {
        finish();
    }
}
