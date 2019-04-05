package cs246.businesscalendar.view_presenter.login;

public interface LoginContract {
    interface View {
        void showLogin();
        void showCancel();
    }

    interface Presenter {
        boolean isUserSignedIn();
        boolean login(String username, String password);
        void handleClickCancel();
    }
}
