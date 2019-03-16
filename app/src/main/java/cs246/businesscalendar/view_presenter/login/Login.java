package cs246.businesscalendar.view_presenter.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.landing.Landing;

public class Login extends AppCompatActivity implements LoginContract.View {
    private static final String TAG = "Login";
    private Button loginButton;
    private Button cancelButton;
    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set Buttons
        loginButton = findViewById(R.id.loginLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogin();
            }
        });

        cancelButton = findViewById(R.id.loginCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancel();
            }
        });

        // Set Presenter
        presenter = new LoginPresenter();
    }

    public void showLogin() {
        // Gather Login Information
        String username = ((EditText)findViewById(R.id.loginUsernameEdit)).getText().toString();
        String password = ((EditText)findViewById(R.id.loginPasswordEdit)).getText().toString();

        // Test login information before moving to Landing page
        if (presenter.handleClickLogin(username, password)) {
            Intent thisIntent = new Intent(this, Landing.class);

            startActivity(thisIntent);
        }
        else
        {
            Context myContext = this;
            CharSequence myText = "ERROR:  Invalid Username-Password Combination";
            int duration = Toast.LENGTH_SHORT;
            Toast toastSuccessful = Toast.makeText(myContext, myText, duration);
            toastSuccessful.setGravity(Gravity.CENTER, 0, 0);
            toastSuccessful.show();
        }
    }

    public void showCancel() {
        finish();
    }
}
