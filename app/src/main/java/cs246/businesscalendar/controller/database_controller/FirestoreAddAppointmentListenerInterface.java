package cs246.businesscalendar.controller.database_controller;

import cs246.businesscalendar.model.ParcelableAppointment;

public interface FirestoreAddAppointmentListenerInterface extends FirestoreBaseListenerInterface {
    void onAddAppointmentSuccess(ParcelableAppointment newAppointment);
    void onAddAppointmentFailure();
}
