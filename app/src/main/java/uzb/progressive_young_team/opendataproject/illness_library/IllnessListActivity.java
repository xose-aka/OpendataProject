package uzb.progressive_young_team.opendataproject.illness_library;

import androidx.appcompat.app.AppCompatActivity;
import uzb.progressive_young_team.opendataproject.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.internal.InternalTokenProvider;

import android.os.Bundle;
import android.widget.Toast;

public class IllnessListActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private CollectionReference illnessRef;
    private IllnessAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illness_list);

        db = FirebaseFirestore.getInstance();
        illnessRef = db.collection("Illness List");
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = illnessRef.orderBy("title", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Illness> options = new FirestoreRecyclerOptions.Builder<Illness>()
                .setQuery(query, Illness.class)
                .build();
        adapter = new IllnessAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.illnesses_list_recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new IllnessAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
//                Toast.makeText(IllnessListActivity.this, "Title:" + documentSnapshot.getString("title"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(IllnessListActivity.this, IllnessInfoActivity.class);
                intent.putExtra("title", documentSnapshot.getString("title"));
                intent.putExtra("description", documentSnapshot.getString("description"));
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
}
