package cs246.businesscalendar.view_presenter.add_new;

public interface AddNewContract {
    interface View {
        void showAddNewAppointment();
        void showAddNewTask();
        void showAddNewGoal();
        void showJoinGroup();
        void showCreateGroup();
        void showCancel();
    }

    interface Presenter {
        void handleClickAddNewAppointment();
        void handleClickAddNewTask();
        void handleClickAddNewGoal();
        void handleClickJoinGroup();
        void handleClickCreateGroup();
        void handleClickCancel();
    }
}
