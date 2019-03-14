package cs246.businesscalendar.view_presenter.appointment;

import android.util.Log;

public class AppointmentPresenter implements AppointmentContract.Presenter {
    private static final String TAG = "Appointment";

    public void handleClickConfirm() {
        Log.i(TAG, "Confirming appointment");
    }

    public void handleClickCancel() {

    }

}
