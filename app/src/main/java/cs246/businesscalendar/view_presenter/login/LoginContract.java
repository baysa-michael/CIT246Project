package cs246.businesscalendar.view_presenter.login;

public interface LoginContract {
    interface View {
        void showLogin();
        void showCancel();
    }

    interface Presenter {
        boolean handleClickLogin(String username, String password);
        void handleClickCancel();
    }
}
