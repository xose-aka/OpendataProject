package uzb.progressive_young_team.opendataproject.authentication;

import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        findViewById(R.id.register_button).setOnClickListener(this);
        findViewById(R.id.login_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                startActivity(new Intent(LoginRegisterActivity.this, AuthenticationActivity.class));
                break;
            case R.id.login_button:

                break;
        }
    }
}
