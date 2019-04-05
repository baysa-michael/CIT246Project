package cs246.businesscalendar.view_presenter.welcome;

public interface WelcomeContract {
    interface View {
        void showCreateAccount();
        void showLogin();
    }

    interface Presenter {
        boolean isUserSignedIn();
    }
}
