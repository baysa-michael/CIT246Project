package cs246.businesscalendar.controller.database_controller;

import org.joda.time.LocalDate;

import java.util.List;

import cs246.businesscalendar.model.Appointment;

public interface DatabaseInterface {
    void initializeDatabase();

    boolean createOrUpdateAccount(String userID, String displayName, String email,
                             String phone, int timeZoneOffset, boolean is24H);

    boolean addUserAppointment(String userID, Appointment newAppointment);

    boolean modifyUserAppointment(String userID, String appointmentHash,
                                  Appointment updatedAppointment);

    boolean deleteUserAppointment(String userID, String appointmentHash);

    List<Appointment> getUserAppointments(String userID, LocalDate startDate, LocalDate endDate);
}
