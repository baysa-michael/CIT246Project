package cs246.businesscalendar.view_presenter.landing;

interface LandingContract {
    interface View {
        void showSchedule();
        void showTasks();
        void showGoals();
        void showAddNew();
        void showSelectView();
        void showSearch();
        void showSettings();
        void showLogout();
        void informUser(String message);
    }

    interface Presenter {
        boolean isUserSignedIn();
        void signOutUser();
        void handleClickSchedule();
        void handleClickTasks();
        void handleClickGoals();
        void handleClickAddNew();
        void handleClickSelectView();
        void handleClickSearch();
        void handleClickSettings();
        void handleClickLogout();
    }}
