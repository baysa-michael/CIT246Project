package cs246.businesscalendar.view_presenter.modify_appointment;

interface ModifyAppointmentContract {
    interface View {
        void showConfirm();
        void showCancel();
    }

    interface Presenter {
        boolean isUserSignedIn();
    }
}
