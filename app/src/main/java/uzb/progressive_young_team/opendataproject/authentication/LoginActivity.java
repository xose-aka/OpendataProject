package uzb.progressive_young_team.opendataproject.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import uzb.progressive_young_team.opendataproject.MainActivity;
import uzb.progressive_young_team.opendataproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    private EditText mPhoneNumber, mPassword;
    private FirebaseFirestore db;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.login_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.action_bar_login));
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final SessionManager sessionManager = new SessionManager(this) ;

        mPhoneNumber = (EditText)findViewById(R.id.login_phone_number);
        mPassword = (EditText)findViewById(R.id.login_user_password);

        db = FirebaseFirestore.getInstance();

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {

            String phoneNumber, password;

            @Override
            public void onClick(View v) {

                phoneNumber = mPhoneNumber.getText().toString();
                password = mPassword.getText().toString();

                if(phoneNumber.isEmpty()) {
                    mPhoneNumber.setError("Telefon raqamni kiriting!");
                    mPhoneNumber.requestFocus();
                    return;
                } else if(password.isEmpty()) {
                    mPassword.setError("Parolni kiriting!");
                    mPassword.requestFocus();
                    return;
                } else {
                    db.collection("users")
                            .whereEqualTo("phoneNumber", phoneNumber)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {

                                            if( phoneNumber.equals(document.getString("phoneNumber")) ) {
                                                if( password.equals(document.getString("password")) ) {
                                                    sessionManager.createSession(phoneNumber, password);
                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                    finish();
                                                } else {
                                                    mPassword.setError("Noto'g'ri parol");
                                                    mPassword.requestFocus();
                                                }
                                            } else {
                                                mPhoneNumber.setError("Noto'g'ri telefon raqami");
                                                mPhoneNumber.requestFocus();
                                            }
                                        }
                                    } else {
                                        Log.d("errorFetch", "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                }
            }
        });
    }
}
