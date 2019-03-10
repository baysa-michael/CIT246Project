package cs246.businesscalendar.view_presenter.select_view;

public interface SelectViewContract {
    interface View {
        void showMonthlyView();
        void showWeeklyView();
        void showDailyView();
        void showCancel();
    }

    interface Presenter {
        void handleClickMonthlyView();
        void handleClickWeeklyView();
        void handleClickDailyView();
        void handleClickCancel();
    }
}
