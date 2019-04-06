package cs246.businesscalendar.view_presenter.monthly_calendar;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cs246.businesscalendar.model.Appointment;
import cs246.businesscalendar.model.ParcelableAppointment;

interface MonthlyCalendarContract {
    interface View {
        void showReturn();
        void showAdd();
    }

    interface Presenter {
        boolean isUserSignedIn();
        Map<String, List<AtomicInteger>> retrieveBasicMonthlyInfo(
                List<Appointment> testAppointments, LocalDate testDate);
        List<Appointment> convertParcelableAppointments(List<ParcelableAppointment> initialList);
    }
}
