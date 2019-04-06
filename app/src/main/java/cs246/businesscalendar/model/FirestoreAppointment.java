package cs246.businesscalendar.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class FirestoreAppointment {
    private int year;
    private int month;
    private int day;
    private boolean isAllDay;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private String title;
    private String description;
    private String location;
    private String attendees;
    private String hash;

    public FirestoreAppointment() {
    }

    public FirestoreAppointment(int year,
                                int month,
                                int day,
                                boolean isAllDay,
                                int startHour,
                                int startMinute,
                                int endHour,
                                int endMinute,
                                String title,
                                String description,
                                String location,
                                String attendees,
                                String hash) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.isAllDay = isAllDay;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.title = title;
        this.description = description;
        this.location = location;
        this.attendees = attendees;
        this.hash = hash;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttendees() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public static FirestoreAppointment fromAppointment(Appointment baseAppointment) {
        return new FirestoreAppointment(
                baseAppointment.getDate().getYear(),
                baseAppointment.getDate().getMonthOfYear(),
                baseAppointment.getDate().getDayOfMonth(),
                baseAppointment.isAllDay(),
                baseAppointment.getStart().getHourOfDay(),
                baseAppointment.getStart().getMinuteOfHour(),
                baseAppointment.getEnd().getHourOfDay(),
                baseAppointment.getEnd().getMinuteOfHour(),
                baseAppointment.getTitle(),
                baseAppointment.getDescription(),
                baseAppointment.getLocation(),
                baseAppointment.getAttendees(),
                baseAppointment.getHash()
        );
    }

    public static Appointment fromFirestoreAppointment(FirestoreAppointment baseAppointment) {
        return new Appointment(
                new LocalDate(
                        baseAppointment.getYear(),
                        baseAppointment.getMonth(),
                        baseAppointment.getDay()
                ),
                baseAppointment.isAllDay(),
                new LocalTime(
                        baseAppointment.getStartHour(),
                        baseAppointment.getStartMinute()
                ),
                new LocalTime(
                        baseAppointment.getEndHour(),
                        baseAppointment.getEndMinute()
                ),
                baseAppointment.getTitle(),
                baseAppointment.getDescription(),
                baseAppointment.getLocation(),
                baseAppointment.getAttendees(),
                baseAppointment.getHash()
        );
    }
}
