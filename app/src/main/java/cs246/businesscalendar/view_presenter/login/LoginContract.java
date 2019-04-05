package cs246.businesscalendar.view_presenter.login;

public interface LoginContract {
    interface View {
        void showLogin();
        void showCancel();
        void informUser(String message);
    }

    interface Presenter {
        boolean isUserSignedIn();
        void login(String username, String password);
    }
}
