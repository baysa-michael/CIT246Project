package cs246.businesscalendar.view_presenter.add_new;

interface AddNewContract {
    interface View {
        void showAddNewAppointment();
        void showAddNewTask();
        void showAddNewGoal();
        void showJoinGroup();
        void showCreateGroup();
        void showCancel();
        void informUser(String message);
    }

    interface Presenter {
        boolean isUserSignedIn();
    }
}
