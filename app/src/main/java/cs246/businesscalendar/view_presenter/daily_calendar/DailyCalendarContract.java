package cs246.businesscalendar.view_presenter.daily_calendar;

import org.joda.time.LocalDate;

import java.util.List;

import cs246.businesscalendar.model.Appointment;

/**
 *  Contract for DailyCalendar Package
 *  <p>
 *  This interface serves as the MVP contract between the View and the Presenter in the
 *  DailyCalendar Package, with appropriate methods included as a part of this class.
 *  </p>
 *
 * @author Michael Baysa
 * @version 2019.03.20
 * @since 1.0
 */
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
