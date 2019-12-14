package uzb.progressive_young_team.opendataproject.authentication;

import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.MainActivity;
import uzb.progressive_young_team.opendataproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class AuthenticationActivity extends AppCompatActivity {

    private EditText mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        mPhoneNumber = findViewById(R.id.phone_number);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone_number = mPhoneNumber.getText().toString().trim();

                if(phone_number.isEmpty()){
                    mPhoneNumber.setError("Enter a valid mobile");
                    mPhoneNumber.requestFocus();
                    return;
                }

                Intent intent = new Intent(AuthenticationActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("phone_number", phone_number);
                startActivity(intent);
            }
        });
    }
}
