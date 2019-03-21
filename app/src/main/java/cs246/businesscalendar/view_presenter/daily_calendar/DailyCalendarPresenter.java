package cs246.businesscalendar.view_presenter.daily_calendar;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.utilities.TestItems;

public class DailyCalendarPresenter implements DailyCalendarContract.Presenter {
    public void handleClickReturn() {

    }

    public void handleClickAdd() {

    }

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
