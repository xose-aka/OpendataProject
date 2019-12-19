package uzb.progressive_young_team.opendataproject.illness_library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import uzb.progressive_young_team.opendataproject.R;

import android.os.Bundle;
import android.widget.TextView;

public class IllnessInfoActivity extends AppCompatActivity {

    private TextView mTitle, mDescription;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illness_info);

        String title = getIntent().getStringExtra("title");

        toolbar = findViewById(R.id.illness_info_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTitle = (TextView) findViewById(R.id.info_title);
        mTitle.setText(title);

        mDescription = (TextView) findViewById(R.id.description);
        mDescription.setText(getIntent().getStringExtra("description"));
    }
}
