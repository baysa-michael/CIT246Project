package cs246.businesscalendar.controller.database_controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs246.businesscalendar.model.Appointment;

public class FirestoreController implements DatabaseInterface {
    private FirebaseFirestore database;
    private Context thisContext;
    private static final String TAG = "FirebaseController";

    public void initializeDatabase(Context newContext) {
        database = FirebaseFirestore.getInstance();
        thisContext = newContext;
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
                        sendUserMessage("Account Successfully Updated");
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        sendUserMessage("ERROR:  Account Update Failed");
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
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        sendUserMessage("Appointment Successfully Added");
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        sendUserMessage("ERROR:  Unable to Add Appointment");
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
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        sendUserMessage("Appointment Successfully Modified");
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        sendUserMessage("ERROR:  Unable to Modify Appointment");
                    }
                });

        return true;
    }

    @Override
    public boolean deleteUserAppointment(String userID, String appointmentHash){
        database.collection("users").document(userID)
                .collection("appointments").document(appointmentHash)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        sendUserMessage("Appointment Successfully Deleted");
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        sendUserMessage("ERROR:  Unable to Delete Appointment");
                    }
                });

        return true;
    }

    @Override
    public List<Appointment> getUserAppointments(String userID, LocalDate startDate,
                                                 LocalDate endDate){
        CollectionReference appointmentsCollection = database.collection("users")
                .document(userID).collection("appointments");




        List<Appointment> appointmentList = new ArrayList<>();
        return appointmentList;
    }

    private void sendUserMessage(String toastMessage) {
        int toastDuration = Toast.LENGTH_SHORT;

        Toast messageToast = Toast.makeText(thisContext, toastMessage, toastDuration);
        messageToast.setGravity(Gravity.CENTER, 0, 0);
        messageToast.show();
    }
}
