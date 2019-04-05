package cs246.businesscalendar.view_presenter.create_account;

interface CreateAccountContract {
    interface View {
        void showConfirm();
        void showCancel();
        void informUser(String message);
    }

    interface Presenter {
        boolean validatePasswordRequirements(String password);
        void createNewAccount(String email, String password);
        void addNewAccountData(String email, String displayName, String phone,
                                 int timeZoneOffset, boolean is24H);
        boolean isUserSignedIn();
        void signOutUser();
        int getTimeZoneOffset(String spinnerValue);
    }
}
