package cs246.businesscalendar.view_presenter.modify_appointment;

public interface ModifyAppointmentContract {
    interface View {
        void showConfirm();
        void showCancel();
    }

    interface Presenter {
        void handleClickConfirm();
        void handleClickCancel();
    }
}
