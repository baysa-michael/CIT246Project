package cs246.businesscalendar.view_presenter.monthly_calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs246.businesscalendar.R;

public class MonthlyCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_calendar);
    }

    public void clickReturn(View view) {
        finish();
    }
}
