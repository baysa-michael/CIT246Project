package cs246.businesscalendar.view_presenter.monthly_calendar;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.utilities.TestItems;

public class MonthlyCalendarPresenter implements MonthlyCalendarContract.Presenter {
    public void handleClickReturn() {

    }

    public void handleClickAdd() {

    }

    Map<String, List<AtomicInteger>> retrieveBasicMonthlyInfo(LocalDate testDate) {
        // Determine the Start and End of the Month
        LocalDate startOfMonth = new LocalDate(testDate.getYear(), testDate.getMonthOfYear(),
                1);
        LocalDate endOfMonth = startOfMonth.dayOfMonth().withMaximumValue();

        // Determine the number of days in the month
        int daysInMonth = Days.daysBetween(startOfMonth, endOfMonth).getDays() + 1;

        // Retrieve list of appointments from database
        // ******************* REPLACE WITH ACTUAL DATA WHEN SET UP ********************
        List<Appointment> appointments = TestItems.testAppointments();

        // Initialize an empty list for Appointment Counts
        List<AtomicInteger> appointmentCount = new ArrayList<>();
        for (int i = 0; i < daysInMonth; i++) {
            appointmentCount.add(new AtomicInteger(0));
        }

        // Loop through each appointment and increment the Appointment Count
        for (Appointment thisAppointment : appointments) {
            if (thisAppointment.getDate().getYear() == testDate.getYear() &&
            thisAppointment.getDate().getMonthOfYear() == testDate.getMonthOfYear()) {
                appointmentCount.get(thisAppointment.getDate().
                        getDayOfMonth() - 1).incrementAndGet();
            }
        }

        // Add Tasks with a Date
        // Non-Core Task



        // Add Goals with a Date
        // Non-Core Task


        // Add lists to return element
        Map<String, List<AtomicInteger>> returnMap = new HashMap<>();
        returnMap.put("Appointments", appointmentCount);

        return returnMap;
    }
}
