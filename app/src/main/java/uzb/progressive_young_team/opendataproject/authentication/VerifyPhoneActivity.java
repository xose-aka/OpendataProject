package uzb.progressive_young_team.opendataproject.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.MainActivity;
import uzb.progressive_young_team.opendataproject.R;
import uzb.progressive_young_team.opendataproject.User;
import uzb.progressive_young_team.opendataproject.illness_library.IllnessListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {

    private String mVerificationCode;
    private String mUserName, mUserSurname, mUserPassword, mPhoneNumber;
    //The edittext to input the code
    private EditText mUserEnteredCode;

    //firebase auth object
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        mUserEnteredCode = findViewById(R.id.verification_code);

        mAuth = FirebaseAuth.getInstance();
        //getting mobile number from the previous activity
        //and sending the verification code to the number
        Intent intent = getIntent();
        mPhoneNumber = intent.getStringExtra("phone_number");
        mUserName = intent.getStringExtra("user_name");
        mUserSurname = intent.getStringExtra("user_surname");
        mUserPassword = intent.getStringExtra("password");
        sendVerificationCode();

        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button
        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEnteredCode = mUserEnteredCode.getText().toString().trim();
                if (userEnteredCode.isEmpty() || userEnteredCode.length() < 6) {
                    mUserEnteredCode.setError("Noto'g'ri kod kiritdingiz");
                    mUserEnteredCode.requestFocus();
                    return;
                }

                //verifying the code entered manually
                verifyVerificationCode(userEnteredCode);
            }
        });

    }

    private void sendVerificationCode() {

        Log.d("phone", mPhoneNumber);
        Log.d("name", mUserName);
        Log.d("surname", mUserSurname);
        Log.d("password", mUserPassword);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                ("+998" + mPhoneNumber),        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                VerifyPhoneActivity.this,               // Activity (for callback binding)
                mCallbacks);
    }


    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                mUserEnteredCode.setText(code);

                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(@NonNull String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationCode = s;
        }
    };


    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationCode, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //verification successful we will start the profile activity
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userID = firebaseUser.getUid();

                            User user = new User(mUserName, userID, mUserSurname,
                                    mUserPassword, (new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss" ).toString()), mPhoneNumber);

                            db.collection("users").document(userID).set(user);

                            Intent intent = new Intent(VerifyPhoneActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            snackbar.show();
                        }
                    }
                });
    }

}
