package cs246.businesscalendar.view_presenter.landing;

import android.content.Context;

import cs246.businesscalendar.controller.authentication_controller.FirebaseAuthController;
import cs246.businesscalendar.controller.database_controller.FirestoreController;
import cs246.businesscalendar.controller.database_controller.FirestoreAddUserListenerInterface;

class LandingPresenter implements LandingContract.Presenter {
    private FirebaseAuthController authenticator;
    private FirestoreController database;

    LandingPresenter(Context newContext) {
        authenticator = new FirebaseAuthController();
        database = new FirestoreController((FirestoreAddUserListenerInterface) newContext);
    }

    @Override
    public boolean isUserSignedIn() {
        return authenticator.isUserSignedIn();
    }

    @Override
    public void signOutUser() {
        authenticator.logout();
    }

    public void handleClickSchedule() {

    }

    public void handleClickTasks() {

    }

    public void handleClickGoals() {

    }

    public void handleClickAddNew() {

    }

    public void handleClickSelectView() {

    }

    public void handleClickSearch() {

    }

    public void handleClickSettings() {

    }

    public void handleClickLogout() {

    }
}
