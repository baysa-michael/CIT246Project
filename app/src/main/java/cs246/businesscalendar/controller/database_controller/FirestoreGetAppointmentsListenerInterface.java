package cs246.businesscalendar.controller.database_controller;

import java.util.List;

import cs246.businesscalendar.model.ParcelableAppointment;

public interface FirestoreGetAppointmentsListenerInterface extends FirestoreBaseListenerInterface {
    void onGetAppointmentsSuccess(List<ParcelableAppointment> downloadedAppointments);
    void onGetAppointmentsFailure();
}
