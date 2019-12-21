package uzb.progressive_young_team.opendataproject.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import uzb.progressive_young_team.opendataproject.R;

public class DoctorAdapter extends FirestoreRecyclerAdapter<Doctor, DoctorAdapter.DoctorHolder> {

    private OnItemClickListener listener;

    public DoctorAdapter(@NonNull FirestoreRecyclerOptions<Doctor> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DoctorAdapter.DoctorHolder holder, int position, @NonNull Doctor model) {
        holder.textViewDoctorName.setText(model.getName());
    }

    @NonNull
    @Override
    public DoctorAdapter.DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new DoctorAdapter.DoctorHolder(v);
    }

    class DoctorHolder extends RecyclerView.ViewHolder {

        TextView textViewDoctorName;

        public DoctorHolder(@NonNull View itemView) {
            super(itemView);
            textViewDoctorName = itemView.findViewById(R.id.doctor_name);

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

    public void setOnItemClickListener(DoctorAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
