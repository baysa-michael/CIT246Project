package cs246.businesscalendar.view_presenter.landing;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import cs246.businesscalendar.R;
import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;
import cs246.businesscalendar.controller.database_controller.FirestoreController;
import cs246.businesscalendar.controller.database_controller.FirestoreAddUserListenerInterface;
import cs246.businesscalendar.controller.database_controller.FirestoreGetAppointmentsListenerInterface;
import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.UserData;

class LandingPresenter implements LandingContract.Presenter {
    private FirebaseAuthController authenticator;
    private FirestoreController databaseUser;
    private FirestoreController databaseAppointments;

    LandingPresenter(Context newContext) {
        authenticator = new FirebaseAuthController();
        databaseUser = new FirestoreController((FirestoreAddUserListenerInterface) newContext);
        databaseAppointments = new FirestoreController((FirestoreGetAppointmentsListenerInterface)
                newContext);
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }

    @Override
    public void signOutUser() {
        authenticator.logout();
    }

    @Override
    public void getUserData() {
        databaseUser.getUserData(authenticator.getCurrentUserID());
    }

    @Override
    public void getUserAppointments() {
        databaseAppointments.getUserAppointments(authenticator.getCurrentUserID());
    }

    @Override
    public void updateSharedPreferences(Context context, UserData loadedUser) {
        // Set Up Shared Preferences
        SharedPreferences userInfo = context.getSharedPreferences(
                context.getString(R.string.user_shared_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor infoEditor = userInfo.edit();

        // Clear the current contents of Shared Preferences
        infoEditor.clear();

        // Add User Data to Shared Preferences
        infoEditor.putString(context.getString(R.string.shared_preferences_email),
                loadedUser.getEmail());
        infoEditor.putString(context.getString(R.string.shared_preferences_displayName),
                loadedUser.getDisplayName());
        infoEditor.putString(context.getString(R.string.shared_preferences_phone),
                loadedUser.getPhone());
        infoEditor.putString(context.getString(R.string.shared_preferences_time_zone_offset),
                Integer.toString(loadedUser.getTimeZoneOffset()));
        infoEditor.putString(context.getString(R.string.shared_preferences_is24H),
                Boolean.toString(loadedUser.getIs24H()));
        infoEditor.putString(context.getString(R.string.shared_preferences_email),
                loadedUser.getEmail());

        // Apply the new shared preferences (Apply is Asynchronous, Commit is Synchronous)
        infoEditor.apply();
    }

    @Override
    public void saveAppointments(Context context, List<Appointment> userAppointments) {
        // Target Directory and File
        String filename = context.getString(R.string.internal_storage_appointments_filename);
        File directory = context.getFilesDir();
        File targetFile = new File(directory, filename);


        try {
            // Delete any currently existing file
            targetFile.delete();

            // Open a File and Object Output Stream
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Write the object to the file
            oos.writeObject(userAppointments);

            // Close the output streams
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
