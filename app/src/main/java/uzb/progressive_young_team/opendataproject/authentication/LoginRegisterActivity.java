package uzb.progressive_young_team.opendataproject.authentication;

import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.MainActivity;
import uzb.progressive_young_team.opendataproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

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
                startActivity(new Intent(LoginRegisterActivity.this, LoginActivity.class));
                break;
        }
    }
}
