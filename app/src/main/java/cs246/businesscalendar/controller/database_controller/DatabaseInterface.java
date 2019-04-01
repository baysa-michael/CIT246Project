package cs246.businesscalendar.controller.database_controller;

import org.joda.time.LocalDate;

import java.util.List;

import cs246.businesscalendar.model.Appointment;

public interface DatabaseInterface {
    void initializeDatabase();

    boolean createOrUpdateAccount(String userID, String displayName, String email,
                             String phone, int timeZoneOffset, boolean is24H);

    List<Appointment> getUserAppointments(String username, LocalDate startDate, LocalDate endDate);

    // Stretch to implement database tasks for user Tasks and Goals

    boolean addUserAppointment(String username, Appointment newAppointment);

    boolean modifyUserAppointment(String username, String appointmentHash,
                                  Appointment updatedAppointment);

    boolean deleteUserAppointment(String username, String appointmentHash);
}
