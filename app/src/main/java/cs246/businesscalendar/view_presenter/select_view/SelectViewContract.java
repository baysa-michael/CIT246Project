package cs246.businesscalendar.view_presenter.select_view;

interface SelectViewContract {
    interface View {
        void showMonthlyView();
        void showWeeklyView();
        void showDailyView();
        void showCancel();
        void moveToNextActivity(Class<?> activityClass);
    }

    interface Presenter {
        boolean isUserSignedIn();
    }
}
