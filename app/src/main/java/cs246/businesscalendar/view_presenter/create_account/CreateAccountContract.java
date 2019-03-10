package cs246.businesscalendar.view_presenter.create_account;

public interface CreateAccountContract {
    interface View {
        void showConfirm();
        void showCancel();
    }

    interface Presenter {
        void handleClickConfirm();
        void handleClickCancel();
    }
}
