package cs246.businesscalendar.view_presenter.weekly_calendar;

public interface WeeklyCalendarContract {
    interface View {
        void showReturn();
        void showAdd();
    }

    interface Presenter {
        void handleClickReturn();
        void handleClickAdd();
    }
}
