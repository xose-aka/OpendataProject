package uzb.progressive_young_team.opendataproject;

import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.authentication.AuthenticationActivity;
import uzb.progressive_young_team.opendataproject.illness_library.IllnessListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mIllnessesListButton, logout;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        mIllnessesListButton = (Button)findViewById(R.id.illness_list_button);
        logout = (Button)findViewById(R.id.logout);

        mIllnessesListButton.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.illness_list_button:
                Intent intent = new Intent(MainActivity.this, IllnessListActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intentLogout = new Intent(MainActivity.this, AuthenticationActivity.class);
                intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogout);
                break;
        }
    }
}
