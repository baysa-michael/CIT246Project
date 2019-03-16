package cs246.businesscalendar.view_presenter.landing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.R;

import cs246.businesscalendar.model.ModelAppointment;
import cs246.businesscalendar.view_presenter.select_view.SelectView;
import cs246.businesscalendar.view_presenter.add_new.AddNew;

public class Landing extends AppCompatActivity {
    private static final String TAG = "Landing";
    private LandingPresenter presenter;
    private RecyclerView myRecycler;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // Set Buttons
        Button scheduleButton = findViewById(R.id.landingScheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSchedule();
            }
        });

        Button tasksButton = findViewById(R.id.landingTasksButton);
        tasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTasks();
            }
        });

        Button goalsButton = findViewById(R.id.landingGoalsButton);
        goalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGoals();
            }
        });

        Button addNewButton = findViewById(R.id.landingAddNewButton);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNew();
            }
        });

        Button selectViewButton = findViewById(R.id.landingSelectViewButton);
        selectViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectView();
            }
        });

        Button searchButton = findViewById(R.id.landingSearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearch();
            }
        });

        Button settingsButton = findViewById(R.id.landingSettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSettings();
            }
        });

        Button logoutButton = findViewById(R.id.landingLogoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogout();
            }
        });

        // Set Presenter
        presenter = new LandingPresenter();

        // Obtain Display Name in Shared Preferences
        SharedPreferences myPreferences = this.getSharedPreferences(
                getString(R.string.general_shared_preferences), Context.MODE_PRIVATE);
        String welcome = "Welcome " + myPreferences.getString("display_name", "");

        // Include Display Name with Welcome
        TextView welcomeText = findViewById(R.id.landingTitle);
        welcomeText.setText(welcome);

        // SET UP TEST LIST OF APPOINTMENTS ************* TO REMOVE *************************
        List<ModelAppointment> testAppointments = new ArrayList<>();
        testAppointments.add(new ModelAppointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(8,0)
                , new LocalTime(9,0)
                , "Test 1"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new ModelAppointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(10,0)
                , new LocalTime(11,0)
                , "Test 2"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new ModelAppointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(12,0)
                , new LocalTime(13,0)
                , "Test 3"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new ModelAppointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(14,0)
                , new LocalTime(15,0)
                , "Test 4"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new ModelAppointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(16,0)
                , new LocalTime(17,0)
                , "Test 5"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new ModelAppointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(18,0)
                , new LocalTime(19,0)
                , "Test 6"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new ModelAppointment(
                new LocalDate(2019, 3, 16)
                , false
                , new LocalTime(20,0)
                , new LocalTime(21,0)
                , "Test 7"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new ModelAppointment(
                new LocalDate(2019, 3, 17)
                , false
                , new LocalTime(8,0)
                , new LocalTime(9,0)
                , "Test 8"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new ModelAppointment(
                new LocalDate(2019, 3, 17)
                , false
                , new LocalTime(10,0)
                , new LocalTime(11,0)
                , "Test 9"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));
        testAppointments.add(new ModelAppointment(
                new LocalDate(2019, 3, 17)
                , false
                , new LocalTime(12,0)
                , new LocalTime(13,0)
                , "Test 10"
                , ""
                , ""
                , ""
                , null
                , null
                , 0
                , "0000000000000000000000000000000000000000000000000000000000000000"));


        // Set Recycler View with Layout Manager and Adapter
        myRecycler = findViewById(R.id.landingRecyclerView);
        myRecycler.setHasFixedSize(true);
        myRecycler.addItemDecoration(new DividerItemDecoration(myRecycler.getContext(), DividerItemDecoration.VERTICAL));
        myLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(myLayoutManager);
        myAdapter = new LandingRecyclerViewAdapter(this, testAppointments);
        myRecycler.setAdapter(myAdapter);
    }

    public void showSchedule() {

    }

    public void showTasks() {

    }

    public void showGoals() {

    }

    public void showAddNew() {
        Intent thisIntent = new Intent(this, AddNew.class);

        startActivity(thisIntent);
    }

    public void showSelectView() {
        Intent thisIntent = new Intent(this, SelectView.class);

        startActivity(thisIntent);
    }

    public void showSearch() {
        finish();
    }

    public void showSettings() {
        finish();
    }

    public void showLogout() {
        finish();
    }
}
