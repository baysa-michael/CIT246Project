package cs246.businesscalendar.view_presenter.daily_calendar;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.utilities.TestItems;

/**
 *  Presenter for DailyCalendar Package
 *  <p>
 *  This class serves as the MVP Presenter in the DailyCalendar Package, with appropriate methods
 *  included as a part of this class.
 *  </p>
 *
 * @author Michael Baysa
 * @version 2019.03.20
 * @since 1.0
 */
public class DailyCalendarPresenter implements DailyCalendarContract.Presenter {
    public void handleClickReturn() {

    }

    public void handleClickAdd() {

    }

    /**
     * Retrieve List of Appointments on a Given Day
     *
     * <p>
     * This method returns a list of appointments given a specific test date.
     * </p>
     *
     * @param testDate LocalDate formatted date used to retrieve appointments on this date
     * @return Returns a List<Appointment> of appointments on a given day
     */
    public List<Appointment> retrieveAppointmentsByDay(LocalDate testDate) {
        // Retrieve list of appointments for the day
        List<Appointment> dailyAppointments = new ArrayList<>();

        //  *********** LINK TO REAL DATA WHEN READY ************
        List<Appointment> testAppointments = TestItems.testAppointments();
        for(Appointment thisAppointment : testAppointments) {
            if (thisAppointment.getAppointmentDate().equals(
                    testDate)) {
                dailyAppointments.add(thisAppointment);
            }
        }

        return dailyAppointments;
    }
}
