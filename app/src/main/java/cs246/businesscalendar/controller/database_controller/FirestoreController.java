package cs246.businesscalendar.controller.database_controller;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs246.businesscalendar.model.Appointment;

public class FirestoreController implements DatabaseInterface {
    private static final String TAG = "FirestoreController";
    private FirebaseFirestore database;
    private FirestoreListenerInterface listener;

    public FirestoreController(FirestoreListenerInterface newListener) {
        database = FirebaseFirestore.getInstance();
        listener = newListener;
    }

    @Override
    public boolean createOrUpdateAccount(String userID, String email, String displayName,
                             String phone, int timeZoneOffset, boolean is24H) {
        Map<String, Object> accountData = new HashMap<>();
        accountData.put("userID", userID);
        accountData.put("displayName", displayName);
        accountData.put("email", email);
        accountData.put("phone", phone);
        accountData.put("timeZoneOffset", timeZoneOffset);
        accountData.put("is24H", is24H);

        database.collection("users").document(userID)
                .set(accountData)
                // Note - Void is used when setting a document as nothing is returned
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Run the Success Tasks in the UI
                            listener.onReadWriteSuccess();
                        } else {
                            // Run the Failure Tasks in the UI
                            listener.onReadWriteFailure();
                        }
                    }
                });

        return true;
    }

    @Override
    public boolean addUserAppointment(String userID, Appointment newAppointment){
        database.collection("users").document(userID)
                .collection("appointments")
                .document(newAppointment.getAppointmentHash())
                .set(newAppointment)
                // Note - Void is used when setting a document as nothing is returned
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Run the Success Tasks in the UI
                            listener.onReadWriteSuccess();
                        } else {
                            // Run the Failure Tasks in the UI
                            listener.onReadWriteFailure();
                        }
                    }
                });
        return true;
    }

    @Override
    public boolean modifyUserAppointment(String userID, String appointmentHash,
                                         Appointment updatedAppointment){
        database.collection("users").document(userID)
                .collection("appointments").document(appointmentHash)
                .set(updatedAppointment)
                // Note - Void is used when setting a document as nothing is returned
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Run the Success Tasks in the UI
                            listener.onReadWriteSuccess();
                        } else {
                            // Run the Failure Tasks in the UI
                            listener.onReadWriteFailure();
                        }
                    }
                });

        return true;
    }

    @Override
    public boolean deleteUserAppointment(String userID, String appointmentHash){
        database.collection("users").document(userID)
                .collection("appointments").document(appointmentHash)
                .delete()
                // Note - Void is used when setting a document as nothing is returned
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Run the Success Tasks in the UI
                            listener.onReadWriteSuccess();
                        } else {
                            // Run the Failure Tasks in the UI
                            listener.onReadWriteFailure();
                        }
                    }
                });

        return true;
    }

    @Override
    public List<Appointment> getUserAppointments(String userID, LocalDate startDate,
                                                 LocalDate endDate){
        CollectionReference appointmentsCollection = database.collection("users")
                .document(userID).collection("appointments");

/*
        appointmentsCollection.whereGreaterThanOrEqualTo()
                // Note - Void is used when setting a document as nothing is returned
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            // Run the Success Tasks in the UI
                            listener.onReadWriteSuccess();
                        } else {
                            // Run the Failure Tasks in the UI
                            listener.onReadWriteFailure();
                        }
                    }
                });
*/


        List<Appointment> appointmentList = new ArrayList<>();
        return appointmentList;
    }
}
