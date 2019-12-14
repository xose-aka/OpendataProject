package uzb.progressive_young_team.opendataproject.illness_library;

import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.R;

import android.os.Bundle;
import android.widget.TextView;

public class IllnessInfoActivity extends AppCompatActivity {

    private TextView mTitle, mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illness_info);

        mTitle = (TextView) findViewById(R.id.info_title);
        mTitle.setText(getIntent().getStringExtra("title"));

        mDescription = (TextView) findViewById(R.id.description);
        mDescription.setText(getIntent().getStringExtra("description"));
    }
}
