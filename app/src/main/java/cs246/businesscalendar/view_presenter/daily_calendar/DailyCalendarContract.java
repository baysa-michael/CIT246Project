package cs246.businesscalendar.view_presenter.daily_calendar;

import org.joda.time.LocalDate;

import java.util.List;

import cs246.businesscalendar.model.Appointment;

public interface DailyCalendarContract {
    interface View {
        void showReturn();
        void showAdd();
        void display1224Time(boolean is24HTime);
        void displayAppointments(List<Appointment> dailyAppointments);
    }

    interface Presenter {
        void handleClickReturn();
        void handleClickAdd();
        List<Appointment> retrieveAppointmentsByDay(LocalDate testDate);
    }
}
