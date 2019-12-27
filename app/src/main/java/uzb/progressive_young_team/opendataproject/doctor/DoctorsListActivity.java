package uzb.progressive_young_team.opendataproject.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uzb.progressive_young_team.opendataproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DoctorsListActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private CollectionReference doctorRef;
    private DoctorAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        toolbar = findViewById(R.id.doctor_list_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.action_bar_doctor_list));
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = FirebaseFirestore.getInstance();
        doctorRef = db.collection("doctors");
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = doctorRef.orderBy("name");

        FirestoreRecyclerOptions<Doctor> options = new FirestoreRecyclerOptions.Builder<Doctor>()
                .setQuery(query, Doctor.class)
                .build();
        adapter = new DoctorAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.doctor_list_recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DoctorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
//                Toast.makeText(IllnessListActivity.this, "Title:" + documentSnapshot.getString("title"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DoctorsListActivity.this, DoctorInfoActivity.class);
                intent.putExtra("name", documentSnapshot.getString("name"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
