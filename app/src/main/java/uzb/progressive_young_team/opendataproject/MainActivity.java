package uzb.progressive_young_team.opendataproject;

import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.authentication.AuthenticationActivity;
import uzb.progressive_young_team.opendataproject.authentication.LoginRegisterActivity;
import uzb.progressive_young_team.opendataproject.authentication.SessionManager;
import uzb.progressive_young_team.opendataproject.chat.DoctorsListActivity;
import uzb.progressive_young_team.opendataproject.illness_library.IllnessListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        sessionManager = new SessionManager(this);

        findViewById(R.id.illness_list_button).setOnClickListener(this);
        findViewById(R.id.logout).setOnClickListener(this);
        findViewById(R.id.doctor_list_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.illness_list_button:
                startActivity(new Intent(MainActivity.this, IllnessListActivity.class));
                break;
            case R.id.doctor_list_button:
                startActivity(new Intent(MainActivity.this, DoctorsListActivity.class));
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                sessionManager.logout();
                Intent intentLogout = new Intent(MainActivity.this, LoginRegisterActivity.class);
                intentLogout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLogout);
                finish();
                break;
        }
    }
}
