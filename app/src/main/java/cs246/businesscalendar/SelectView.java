package cs246.businesscalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_view);
    }

    public void clickCancel(View view) {
        finish();
    }
}
