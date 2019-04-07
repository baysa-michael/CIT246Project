package cs246.businesscalendar.view_presenter.weekly_calendar;

import org.joda.time.LocalDate;

import java.util.List;

import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.ParcelableAppointment;

interface WeeklyCalendarContract {
    interface View {
        void showReturn();
        void showAdd();
        void display1224Time(boolean is24HTime);
        void displayAppointments(List<List<Appointment>> weeklyAppointments, LocalDate startDate);
        void updateCalendar(LocalDate updateDate, boolean is24HTime);
        void informUser(String message);
    }

    interface Presenter {
        boolean isUserSignedIn();
        List<List<Appointment>> retrieveAppointmentsByWeek(List<Appointment> testAppointments,
                                                           LocalDate testDate);
        LocalDate determineStartOfWeek(LocalDate testDate);
        List<Appointment> convertParcelableAppointments(List<ParcelableAppointment> initialList);
    }
}
