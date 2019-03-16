package cs246.businesscalendar.model;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.List;

public class ModelAppointment {
    private LocalDate appointmentDate;
    private boolean isAllDay;
    private LocalTime appointmentStart;
    private LocalTime appointmentEnd;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentAttendees;
    private List<LocalDate> repeatingDates;
    private List<LocalDateTime> reminderTimes;
    private int appointmentSalt;
    private String appointmentHash;

    public ModelAppointment() {
        this.appointmentDate = new LocalDate(DateTimeZone.UTC);  // Current Date
        this.isAllDay = true;
        this.appointmentStart = null;
        this.appointmentEnd = null;
        this.appointmentTitle = "N/A";
        this.appointmentDescription = "";
        this.appointmentLocation = "";
        this.appointmentAttendees = "";
        this.repeatingDates = null;
        this.reminderTimes = null;
        this.appointmentSalt = 0;
        this.appointmentHash = "0000000000000000000000000000000000000000000000000000000000000000";
    }

    public ModelAppointment(LocalDate appointmentDate,
                            boolean isAllDay,
                            LocalTime appointmentStart,
                            LocalTime appointmentEnd,
                            String appointmentTitle,
                            String appointmentDescription,
                            String appointmentLocation,
                            String appointmentAttendees,
                            List<LocalDate> repeatingDates,
                            List<LocalDateTime> reminderTimes,
                            int appointmentSalt,
                            String appointmentHash) {
        this.appointmentDate = appointmentDate;
        this.isAllDay = isAllDay;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentAttendees = appointmentAttendees;
        this.repeatingDates = repeatingDates;
        this.reminderTimes = reminderTimes;
        this.appointmentSalt = appointmentSalt;
        this.appointmentHash = appointmentHash;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    public LocalTime getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(LocalTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public LocalTime getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(LocalTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public String getAppointmentAttendees() {
        return appointmentAttendees;
    }

    public void setAppointmentAttendees(String appointmentAttendees) {
        this.appointmentAttendees = appointmentAttendees;
    }

    public List<LocalDate> getRepeatingDates() {
        return repeatingDates;
    }

    public void setRepeatingDates(List<LocalDate> repeatingDates) {
        this.repeatingDates = repeatingDates;
    }

    public List<LocalDateTime> getReminderTimes() {
        return reminderTimes;
    }

    public void setReminderTimes(List<LocalDateTime> reminderTimes) {
        this.reminderTimes = reminderTimes;
    }

    public int getAppointmentSalt() {
        return appointmentSalt;
    }

    public void setAppointmentSalt(int appointmentSalt) {
        this.appointmentSalt = appointmentSalt;
    }

    public String getAppointmentHash() {
        return appointmentHash;
    }

    public void setAppointmentHash(String appointmentHash) {
        this.appointmentHash = appointmentHash;
    }
}
