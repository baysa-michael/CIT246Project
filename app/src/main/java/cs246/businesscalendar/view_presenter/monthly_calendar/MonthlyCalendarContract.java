package cs246.businesscalendar.view_presenter.monthly_calendar;

public interface MonthlyCalendarContract {
    interface View {
        void showReturn();
        void showAdd();
    }

    interface Presenter {
        void handleClickReturn();
        void handleClickAdd();
    }
}
