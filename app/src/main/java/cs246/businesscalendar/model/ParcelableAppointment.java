package cs246.businesscalendar.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class ParcelableAppointment implements Parcelable {
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

    public ParcelableAppointment() {
    }

    public ParcelableAppointment(int year,
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

    public static ParcelableAppointment fromAppointment(Appointment baseAppointment) {
        return new ParcelableAppointment(
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

    public static Appointment fromParcelableAppointment(ParcelableAppointment baseAppointment) {
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


    // Implement Parcelable Code
    // Constructor for Parcelable
    private ParcelableAppointment(Parcel in) {
        // ***NOTE:  This is highly dependent on the order to which a parcel is written
        year = in.readInt();
        month = in.readInt();
        day = in.readInt();
        isAllDay = in.readByte() != 0;
        startHour = in.readInt();
        startMinute = in.readInt();
        endHour = in.readInt();
        endMinute = in.readInt();
        title = in.readString();
        description = in.readString();
        location = in.readString();
        attendees = in.readString();
        hash = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        // Write Key Variables
        out.writeInt(year);
        out.writeInt(month);
        out.writeInt(day);
        out.writeByte((byte) (isAllDay ? 1 : 0));
        out.writeInt(startHour);
        out.writeInt(startMinute);
        out.writeInt(endHour);
        out.writeInt(endMinute);
        out.writeString(title);
        out.writeString(description);
        out.writeString(location);
        out.writeString(attendees);
        out.writeString(hash);
    }

    public static final Parcelable.Creator<ParcelableAppointment> CREATOR =
            new Parcelable.Creator<ParcelableAppointment>() {
                // Create from Parcelable Constructor
                @Override
                public ParcelableAppointment createFromParcel(Parcel in) {
                    return new ParcelableAppointment(in);
                }

                @Override
                public ParcelableAppointment[] newArray(int size) {
                    return new ParcelableAppointment[size];
                }
            };
}
