package uzb.progressive_young_team.opendataproject.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.MainActivity;
import uzb.progressive_young_team.opendataproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    private EditText mPhoneNumber, mPassword;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPhoneNumber = (EditText)findViewById(R.id.phone_number);
        mPassword = (EditText)findViewById(R.id.user_password);

        db = FirebaseFirestore.getInstance();

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {

            String phoneNumber = mPhoneNumber.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            @Override
            public void onClick(View v) {
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
                            .whereEqualTo("userPhoneNumber", phoneNumber)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            if( phoneNumber.equals(document.getString("userPhoneNumber")) ) {
                                                if( mPassword.equals(document.getString("userPassword")) ) {
                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
