package cs246.businesscalendar.controller.database_controller;

import com.google.firebase.firestore.FirebaseFirestore;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.model.Appointment;

public class FirestoreController implements DatabaseInterface {
    private FirebaseFirestore database;

    @Override
    public void initializeDatabase() {
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public boolean createNewAccount(String username, String passwordHash, String displayName, String email,
                             String phone, int timeZoneOffset, boolean is24H) {

        return true;
    }

    @Override
    public boolean modifyAccount(String username, String passwordHash, String displayName, String email,
                          String phone, int timeZoneOffset, boolean is24H) {

        return true;
    }

    @Override
    public List<Appointment> getUserAppointments(String username, LocalDate startDate,
                                                 LocalDate endDate){

        List<Appointment> appointmentList = new ArrayList<>();
        return appointmentList;
    }

    @Override
    public boolean addUserAppointment(String username, Appointment newAppointment){

        return true;
    }

    @Override
    public boolean modifyUserAppointment(String username, String appointmentHash,
                                  Appointment updatedAppointment){

        return true;
    }

    @Override
    public boolean deleteUserAppointment(String username, String appointmentHash){

        return true;
    }
}
