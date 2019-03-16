package cs246.businesscalendar.view_presenter.login;

public class LoginPresenter implements LoginContract.Presenter {
    public static final String TAG = "LoginPresenter";

    public boolean handleClickLogin(String username, String password) {
        // Confirm Username and Password are not empty strings
        if (username == null || username.isEmpty() ||
        password == null || password.isEmpty()) {
            // Send false back to the view
            return false;
        }

        // ADD LOGIC FOR LOGIN HERE ***************
        return true;
    }

    public void handleClickCancel() {

    }
}
