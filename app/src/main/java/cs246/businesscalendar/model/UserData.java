package cs246.businesscalendar.model;

import org.joda.time.DateTimeZone;

import java.util.List;

public class UserData {
    private String username;
    private String hashedPassword;
    private String displayName;
    private String email;
    private String phone;
    private DateTimeZone defaultTimeZone;
    private boolean is24H;
    private List<Appointment> userAppointments;

    public UserData() {
        this.username = "";
        this.hashedPassword = "";
        this.displayName = "";
        this.email = "";
        this.phone = "";
        this.defaultTimeZone = DateTimeZone.UTC;
        this.is24H = false;
        this.userAppointments = null;
    }

    public UserData(String username,
                    String hashedPassword,
                    String displayName,
                    String email,
                    String phone,
                    DateTimeZone defaultTimeZone,
                    boolean is24H,
                    List<Appointment> userAppointments) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.displayName = displayName;
        this.email = email;
        this.phone = phone;
        this.defaultTimeZone = defaultTimeZone;
        this.is24H = is24H;
        this.userAppointments = userAppointments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DateTimeZone getDefaultTimeZone() {
        return defaultTimeZone;
    }

    public void setDefaultTimeZone(DateTimeZone defaultTimeZone) {
        this.defaultTimeZone = defaultTimeZone;
    }

    public boolean isIs24H() {
        return is24H;
    }

    public void setIs24H(boolean is24H) {
        this.is24H = is24H;
    }

    public List<Appointment> getUserAppointments() {
        return userAppointments;
    }

    public void setUserAppointments(List<Appointment> userAppointments) {
        this.userAppointments = userAppointments;
    }
}
