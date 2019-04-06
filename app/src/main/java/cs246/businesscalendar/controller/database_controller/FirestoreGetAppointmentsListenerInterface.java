package cs246.businesscalendar.controller.database_controller;

import java.util.List;

import cs246.businesscalendar.model.Appointment;

public interface FirestoreGetAppointmentsListenerInterface extends FirestoreBaseListenerInterface {
    void onGetAppointmentsSuccess(List<Appointment> downloadedAppointments);
    void onGetAppointmentsFailure();
}
