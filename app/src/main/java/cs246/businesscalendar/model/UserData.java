package cs246.businesscalendar.model;

public class UserData {
    private String email;
    private String displayName;
    private String phone;
    private int timeZoneOffset;
    private boolean is24H;

    public UserData() {
        this.email = "";
        this.displayName = "";
        this.phone = "";
        this.timeZoneOffset = 0;
        this.is24H = false;
    }

    public UserData(String email,
                    String displayName,
                    String phone,
                    int timeZoneOffset,
                    boolean is24H) {
        this.email = email;
        this.displayName = displayName;
        this.phone = phone;
        this.timeZoneOffset = timeZoneOffset;
        this.is24H = is24H;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public void setDefaultTimeZone(int defaultTimeZone) {
        this.timeZoneOffset = defaultTimeZone;
    }

    public boolean getIs24H() {
        return is24H;
    }

    public void setIs24H(boolean is24H) {
        this.is24H = is24H;
    }

}
