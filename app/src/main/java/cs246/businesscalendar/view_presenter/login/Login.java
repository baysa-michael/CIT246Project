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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.quickstart.auth.R;

import cs246.businesscalendar.R;

import cs246.businesscalendar.view_presenter.landing.Landing;

public class Login extends AppCompatActivity implements LoginContract.View {
    private static final String TAG = "Login";
    private LoginPresenter presenter;

    private FirebaseAuth mAuth;

    // Initialize Firebase Auth
    mAuth = FirebaseAuth.getInstance();

    /**
     * Firebase checks to see if user is already logged in
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

     mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                updateUI(null);
            }

            // [START_EXCLUDE]
            if (!task.isSuccessful()) {
                mStatusTextView.setText(R.string.auth_failed);
            }
            hideProgressDialog();
            // [END_EXCLUDE]
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set Buttons
        Button loginButton = findViewById(R.id.loginLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogin();
            }
        });

        Button cancelButton = findViewById(R.id.loginCancelButton);
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

    //firebase sign out
    FirebaseAuth.getInstance().signOut();
}
