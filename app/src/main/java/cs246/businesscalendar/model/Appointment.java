package cs246.businesscalendar.model;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class Appointment {
    private LocalDate date;
    private boolean isAllDay;
    private LocalTime start;
    private LocalTime end;
    private String title;
    private String description;
    private String location;
    private String attendees;
    private String hash;

    public Appointment() {
        this.date = new LocalDate(DateTimeZone.UTC);  // Current Date
        this.isAllDay = true;
        this.start = null;
        this.end = null;
        this.title = "N/A";
        this.description = "";
        this.location = "";
        this.attendees = "";
        this.hash = "0000000000000000000000000000000000000000000000000000000000000000";
    }

    public Appointment(LocalDate date,
                       boolean isAllDay,
                       LocalTime start,
                       LocalTime end,
                       String title,
                       String description,
                       String location,
                       String attendees,
                       String hash) {
        this.date = date;
        this.isAllDay = isAllDay;
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;
        this.location = location;
        this.attendees = attendees;
        this.hash = hash;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
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


}
