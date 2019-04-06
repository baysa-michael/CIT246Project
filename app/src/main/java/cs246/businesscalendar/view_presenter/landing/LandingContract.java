package cs246.businesscalendar.view_presenter.landing;

import android.content.Context;

import java.util.List;

import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.ParcelableAppointment;
import cs246.businesscalendar.model.UserData;

interface LandingContract {
    interface View {
        void showSchedule();
        void showTasks();
        void showGoals();
        void showAddNew();
        void showSelectView();
        void showSearch();
        void showSettings();
        void showLogout();
        void informUser(String message);
        void moveToNextActivity(Class<?> activityClass);
    }

    interface Presenter {
        boolean isUserSignedIn();
        void signOutUser();
        void getUserData();
        void getUserAppointments();
        void updateSharedPreferences(Context context, UserData loadedUser);
        List<Appointment> convertToAppointments(List<ParcelableAppointment> downloadList);
    }
}
