package uzb.progressive_young_team.opendataproject.illness_library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import  uzb.progressive_young_team.opendataproject.R;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IllnessAdapter extends FirestoreRecyclerAdapter<Illness, IllnessAdapter.IllnessHolder> {

    private OnItemClickListener listener;

    public IllnessAdapter(@NonNull FirestoreRecyclerOptions<Illness> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull IllnessHolder holder, int position, @NonNull Illness model) {
        holder.textViewIllnessTitle.setText(model.getTitle());
    }

    @NonNull
    @Override
    public IllnessHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.illness_item, parent, false);
        return new IllnessHolder(v);
    }

    class IllnessHolder extends RecyclerView.ViewHolder {

        TextView textViewIllnessTitle;

        public IllnessHolder(@NonNull View itemView) {
            super(itemView);
            textViewIllnessTitle = itemView.findViewById(R.id.illness_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
