package uzb.progressive_young_team.opendataproject.forum;

import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.R;

import android.os.Bundle;
import android.view.Menu;

public class MainForumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forum);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_post_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
