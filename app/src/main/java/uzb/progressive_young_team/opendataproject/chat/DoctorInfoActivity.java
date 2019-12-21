package uzb.progressive_young_team.opendataproject.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import uzb.progressive_young_team.opendataproject.R;

import android.os.Bundle;
import android.widget.TextView;

public class DoctorInfoActivity extends AppCompatActivity {

    private TextView mName;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);

        String name = getIntent().getStringExtra("name");

        toolbar = findViewById(R.id.doctor_info_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mName = (TextView) findViewById(R.id.doctor_name);
        mName.setText(name);
    }
}
