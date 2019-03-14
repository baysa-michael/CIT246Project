package cs246.businesscalendar.view_presenter.appointment;

public interface AppointmentContract {
    interface View {
        void showConfirm();
        void showCancel();
    }

    interface Presenter {
        void handleClickConfirm();
        void handleClickCancel();
    }
}