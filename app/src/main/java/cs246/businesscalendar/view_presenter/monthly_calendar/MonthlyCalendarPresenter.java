package cs246.businesscalendar.view_presenter.monthly_calendar;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;
import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.ParcelableAppointment;
import cs246.businesscalendar.utilities.TestItems;

public class MonthlyCalendarPresenter implements MonthlyCalendarContract.Presenter {
    private FirebaseAuthController authenticator;

    MonthlyCalendarPresenter() {
        authenticator = new FirebaseAuthController();
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }

    @Override
    public Map<String, List<AtomicInteger>> retrieveBasicMonthlyInfo(
            List<Appointment> testAppointments, LocalDate testDate) {
        // Determine the Start and End of the Month
        LocalDate startOfMonth = new LocalDate(testDate.getYear(), testDate.getMonthOfYear(),
                1);
        LocalDate endOfMonth = startOfMonth.dayOfMonth().withMaximumValue();

        // Determine the number of days in the month
        int daysInMonth = Days.daysBetween(startOfMonth, endOfMonth).getDays() + 1;

        // Initialize an empty list for Appointment Counts
        List<AtomicInteger> appointmentCount = new ArrayList<>();
        for (int i = 0; i < daysInMonth; i++) {
            appointmentCount.add(new AtomicInteger(0));
        }

        // Loop through each appointment and increment the Appointment Count
        for (Appointment thisAppointment : testAppointments) {
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

    @Override
    public List<Appointment> convertParcelableAppointments(List<ParcelableAppointment> initialList) {
        List<Appointment> convertedAppointments = new ArrayList<>();

        // Convert each appointment
        for (ParcelableAppointment input : initialList) {
            convertedAppointments.add(ParcelableAppointment.fromParcelableAppointment(input));
        }

        return convertedAppointments;
    }
}
