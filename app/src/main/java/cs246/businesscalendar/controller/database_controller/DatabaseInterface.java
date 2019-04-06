package cs246.businesscalendar.controller.database_controller;

import org.joda.time.LocalDate;

import java.util.List;

import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.UserData;

interface DatabaseInterface {
    void createOrUpdateAccount(String userID, String email, String displayName,
                             String phone, int timeZoneOffset, boolean is24H);

    void getUserData(String userID);

    void addUserAppointment(String userID, Appointment newAppointment);

    void modifyUserAppointment(String userID, String appointmentHash,
                                  Appointment updatedAppointment);

    void deleteUserAppointment(String userID, String appointmentHash);

    void getUserAppointments(String userID);
}
