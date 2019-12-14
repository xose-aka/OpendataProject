package uzb.progressive_young_team.opendataproject.authentication;

import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AuthenticationActivity extends AppCompatActivity {

    private EditText mPhoneNumber, mUserName, mUserSurname, mUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        mPhoneNumber = findViewById(R.id.phone_number);
        mUserName = findViewById(R.id.user_name);
        mUserSurname = findViewById(R.id.user_surname);
        mUserPassword = findViewById(R.id.user_password);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone_number, user_name, user_surname, password;

                phone_number = mPhoneNumber.getText().toString().trim();
                user_name = mUserName.getText().toString().trim();
                user_surname = mUserSurname.getText().toString().trim();
                password = mUserPassword.getText().toString().trim();

                if( !verificationUserItputs(phone_number, user_name, user_surname, password) ) { return; }

                Intent intent = new Intent(AuthenticationActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("phone_number", phone_number);
                intent.putExtra("user_name", user_name);
                intent.putExtra("user_surname", user_surname);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
    }

    private boolean verificationUserItputs(String phone_number, String user_name, String user_surname, String password) {
        if(phone_number.isEmpty()){

            mPhoneNumber.setError("Enter a valid mobile");
            mPhoneNumber.requestFocus();
            return false;

        } else if(user_name.isEmpty()){

            mPhoneNumber.setError("Enter a valid mobile");
            mPhoneNumber.requestFocus();
            return false;

        } else if(user_surname.isEmpty()){

            mPhoneNumber.setError("Enter a valid mobile");
            mPhoneNumber.requestFocus();
            return false;

        } else if(password.isEmpty()){

            mPhoneNumber.setError("Enter a valid mobile");
            mPhoneNumber.requestFocus();
            return false;

        } else {
            return true;
        }
    }
}
