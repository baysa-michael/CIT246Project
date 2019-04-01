package cs246.businesscalendar.controller.database_controller;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs246.businesscalendar.model.Appointment;

public class FirestoreController implements DatabaseInterface {
    private FirebaseFirestore database;
    private static final String TAG = "FirebaseController";

    @Override
    public void initializeDatabase() {
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public boolean createOrUpdateAccount(String userID, String displayName, String email,
                             String phone, int timeZoneOffset, boolean is24H) {
        boolean isSuccessful;
        Map<String, Object> accountData = new HashMap<>();
        accountData.put("userID", userID);
        accountData.put("displayName", displayName);
        accountData.put("email", email);
        accountData.put("phone", phone);
        accountData.put("timeZoneOffset", timeZoneOffset);
        accountData.put("is24H", is24H);

        database.collection("users").document(userID)
                .set(accountData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Successful Update");
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Failed Update");
                    }
                });

        return true;
    }

    @Override
    public boolean addUserAppointment(String userID, Appointment newAppointment){
        database.collection("users").document(userID)
                .collection("appointments").document("1")
                .set(newAppointment);

        return true;
    }

    @Override
    public boolean modifyUserAppointment(String username, String appointmentHash,
                                         Appointment updatedAppointment){

        return true;
    }

    @Override
    public List<Appointment> getUserAppointments(String username, LocalDate startDate,
                                                 LocalDate endDate){

        List<Appointment> appointmentList = new ArrayList<>();
        return appointmentList;
    }

    @Override
    public boolean deleteUserAppointment(String username, String appointmentHash){

        return true;
    }
}
