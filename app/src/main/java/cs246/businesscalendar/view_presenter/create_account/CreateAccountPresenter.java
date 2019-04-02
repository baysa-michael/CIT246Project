package cs246.businesscalendar.view_presenter.create_account;

import android.util.Log;

public class CreateAccountPresenter implements CreateAccountContract.Presenter {
    @Override
    public void handleClickConfirm() {

    }

    @Override
    public void handleClickCancel() {

    }

    @Override
    public boolean validatePasswordRequirements(String password) {
        // A minimum of 14 characters with at least one capital and one digit
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(\\S){14,}$");
    }
}
