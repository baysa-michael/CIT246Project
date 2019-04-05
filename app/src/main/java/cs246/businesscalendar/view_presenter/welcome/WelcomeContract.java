package cs246.businesscalendar.view_presenter.welcome;

interface WelcomeContract {
    interface View {
        void showCreateAccount();
        void showLogin();
    }

    interface Presenter {
        boolean isUserSignedIn();
    }
}
