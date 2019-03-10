package cs246.businesscalendar.view_presenter.login;

public interface LoginContract {
    interface View {
        void showLogin();
        void showCancel();
    }

    interface Presenter {
        void handleClickLogin();
        void handleClickCancel();
    }
}
