package cs246.businesscalendar.view_presenter.daily_calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs246.businesscalendar.R;

public class DailyCalendar extends AppCompatActivity implements DailyCalendarContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_calendar);
    }

    public void showReturn() {

    }

    public void showAdd() {

    }

    public void clickReturn(View view) {
        finish();
    }

}
