package cs246.businesscalendar.view_presenter.weekly_calendar;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;
import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.ParcelableAppointment;
import cs246.businesscalendar.utilities.TestItems;

public class WeeklyCalendarPresenter implements WeeklyCalendarContract.Presenter {
    private FirebaseAuthController authenticator;

    WeeklyCalendarPresenter() {
        authenticator = new FirebaseAuthController();
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }

    /**
     * Retrieve List of Appointments for a given week
     *
     * <p>
     *     This method returns a list of appointments in a week included with a specific test date.
     * </p>
     *
     * @param testDate LocalDate formatted date used to retrieve appointments on this date
     * @return Returns a List<List<Appointment>> of appointments in a given week
     */
    @Override
    public List<List<Appointment>> retrieveAppointmentsByWeek(List<Appointment> testAppointments,
                                                              LocalDate testDate) {
        // Determine start of the week
        LocalDate startDate = determineStartOfWeek(testDate);

        // Retrieve list of appointments for the day
        List<List<Appointment>> weeklyAppointments = new ArrayList<>();

        // Create a bucket for the appointments each day of week
        for (int i = 0; i < 7; i++) {
            weeklyAppointments.add(new ArrayList<Appointment>());
        }

        for(Appointment thisAppointment : testAppointments) {
            int daysBetween = Days.daysBetween(startDate, thisAppointment
                    .getDate()).getDays();
            if (daysBetween < 7 && daysBetween >= 0) {
                weeklyAppointments.get(daysBetween).add(thisAppointment);
            }
        }

        return weeklyAppointments;
    }

    /**
     * Determine Starting Day of Week
     *
     * <p>
     *     This method takes a test date and identifies the start of the week including that
     *     test date.
     * </p>
     *
     * @param testDate Date to use when identifying start of week
     * @return LocalDate representing the start of the week
     */
    @Override
    public LocalDate determineStartOfWeek(LocalDate testDate) {
        // Get day of week start based on test date
        // Should only return a 1 through 7
        LocalDate returnDate = new LocalDate();
        switch (testDate.getDayOfWeek()) {
            case 1:
                returnDate = testDate;
                break;
            case 2:
                returnDate = testDate.minusDays(1);
                break;
            case 3:
                returnDate = testDate.minusDays(2);
                break;
            case 4:
                returnDate = testDate.minusDays(3);
                break;
            case 5:
                returnDate = testDate.minusDays(4);
                break;
            case 6:
                returnDate = testDate.minusDays(5);
                break;
            case 7:
                returnDate = testDate.minusDays(6);
                break;
        }

        return returnDate;
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
