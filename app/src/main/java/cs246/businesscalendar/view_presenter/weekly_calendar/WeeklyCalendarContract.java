package cs246.businesscalendar.view_presenter.weekly_calendar;

import org.joda.time.LocalDate;

import java.util.List;

import cs246.businesscalendar.model.Appointment;

public interface WeeklyCalendarContract {
    interface View {
        void showReturn();
        void showAdd();
        void display1224Time(boolean is24HTime);
        void displayAppointments(List<List<Appointment>> weeklyAppointments, LocalDate startDate);
        void updateCalendar(LocalDate updateDate, boolean is24HTime);
    }

    interface Presenter {
        void handleClickReturn();
        void handleClickAdd();
        List<List<Appointment>> retrieveAppointmentsByWeek(LocalDate testDate);
        LocalDate determineStartOfWeek(LocalDate testDate);
    }
}
