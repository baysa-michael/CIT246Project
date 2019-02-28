package cs246.businesscalendar.model;

public class UserData {
    private String username;
    private String hashedPassword;
    private String displayId;
    private String email;
    private String businessPhone;
    private String ampm;

    public UserData() {
        this.username = "";
        this.hashedPassword = "";
        this.displayId = "";
        this.email = "";
        this.businessPhone = "";
        this.ampm = "";
    }

    public UserData(String username, String hashedPassword, String displayId, String email, String businessPhone, String ampm) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.displayId = displayId;
        this.email = email;
        this.businessPhone = businessPhone;
        this.ampm = ampm;
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

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }
}
