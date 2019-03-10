package cs246.businesscalendar.view_presenter.daily_calendar;

public interface DailyCalendarContract {
    interface View {
        void showReturn();
        void showAdd();
    }

    interface Presenter {
        void handleClickReturn();
        void handleClickAdd();
    }
}
