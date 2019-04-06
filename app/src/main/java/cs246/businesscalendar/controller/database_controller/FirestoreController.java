package cs246.businesscalendar.controller.database_controller;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.ParcelableAppointment;
import cs246.businesscalendar.model.UserData;

public class FirestoreController implements DatabaseInterface {
    private static final String TAG = "FirestoreController";
    private FirebaseFirestore database;
    private FirestoreBaseListenerInterface listener;

    public FirestoreController(FirestoreBaseListenerInterface newListener) {
        database = FirebaseFirestore.getInstance();
        listener = newListener;
    }

    @Override
    public void createOrUpdateAccount(String userID, String email, String displayName,
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
                            ((FirestoreAddUserListenerInterface) listener).onAddUserSuccess();
                        } else {
                            // Run the Failure Tasks in the UI
                            ((FirestoreAddUserListenerInterface) listener).onAddUserFailure();
                        }
                    }
                });
    }

    @Override
    public void getUserData(String userID) {
        // Set the target document
        DocumentReference targetDocument = database.collection("users")
                .document(userID);

        // Retrieve the Target Document
        targetDocument.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            // Retrieve User Information
                            DocumentSnapshot returnDoucment = task.getResult();
                            if (returnDoucment != null) {
                                // Unpack the data
                                UserData loadedUser = task.getResult().toObject(UserData.class);

                               // Run the Success Tasks in the UI
                                ((FirestoreGetUserListenerInterface) listener)
                                        .onGetUserSuccess(loadedUser);
                            } else {
                                ((FirestoreGetUserListenerInterface) listener).onGetUserFailure();
                            }
                       } else {
                            // Run the Failure Tasks in the UI
                            ((FirestoreGetUserListenerInterface) listener).onGetUserFailure();
                        }
                    }
                });
    }

    @Override
    public void addUserAppointment(String userID, Appointment newAppointment){
        database.collection("users").document(userID)
                .collection("appointments")
                .document(newAppointment.getHash())
                .set(newAppointment)
                // Note - Void is used when setting a document as nothing is returned
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Run the Success Tasks in the UI
                            ((FirestoreAddAppointmentListenerInterface) listener)
                                    .onAddAppointmentSuccess();
                        } else {
                            // Run the Failure Tasks in the UI
                            ((FirestoreAddAppointmentListenerInterface) listener)
                                    .onAddAppointmentFailure();
                        }
                    }
                });
    }

    @Override
    public void modifyUserAppointment(String userID, String appointmentHash,
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
                            ((FirestoreModifyUserListenerInterface) listener).onModUserSuccess();
                        } else {
                            // Run the Failure Tasks in the UI
                            ((FirestoreModifyUserListenerInterface) listener).onModUserFailure();
                        }
                    }
                });
    }

    @Override
    public void deleteUserAppointment(String userID, String appointmentHash){
        database.collection("users").document(userID)
                .collection("appointments").document(appointmentHash)
                .delete()
                // Note - Void is used when setting a document as nothing is returned
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Run the Success Tasks in the UI
                            ((FirestoreDeleteUserListenerInterface) listener).onDeleteUserSuccess();
                        } else {
                            // Run the Failure Tasks in the UI
                            ((FirestoreDeleteUserListenerInterface) listener).onDeleteUserFailure();
                        }
                    }
                });
    }

    @Override
    public void getUserAppointments(String userID){
        // Set the target collection
        CollectionReference appointmentsCollection = database.collection("users")
                .document(userID).collection("appointments");

        // Retrieve the Target Documents
        appointmentsCollection.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<ParcelableAppointment> downloadedAppointments = new ArrayList<>();
                            if (task.getResult() != null) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    // Translate to Appointment and Add to List
                                    downloadedAppointments
                                            .add(document.toObject(ParcelableAppointment.class));
                                }
                            }

                            // Run the Success Tasks in the UI
                            ((FirestoreGetAppointmentsListenerInterface) listener)
                                    .onGetAppointmentsSuccess(downloadedAppointments);
                        } else {
                            // Run the Failure Tasks in the UI
                            ((FirestoreGetAppointmentsListenerInterface) listener)
                                    .onGetAppointmentsFailure();
                        }
                    }
                });
    }
}
