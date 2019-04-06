package cs246.businesscalendar.view_presenter.modify_appointment;

import cs246.businesscalendar.model.ParcelableAppointment;

interface ModifyAppointmentContract {
    interface View {
        void showConfirm();
        void showCancel();
        void informUser(String message);
    }

    interface Presenter {
        boolean isUserSignedIn();
        void addNewAppointment(ParcelableAppointment newAppointment);
    }
}
