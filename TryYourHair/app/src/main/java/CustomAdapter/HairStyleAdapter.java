package CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryyourhair.R;

import java.util.List;

import Models.HairStyle;

public class HairStyleAdapter  extends RecyclerView.Adapter<HairStyleAdapter.HairStyleViewHolder>{

    private static final String TAG = "HairStyleAdapter";
    private List<HairStyle> listHairStyle;
    private Context context;
    private LayoutInflater layoutInflater;

    public HairStyleAdapter(Context context, List<HairStyle> lHairStyle) {
        this.context = context;
        this.listHairStyle = lHairStyle;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HairStyleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate view from Google Card View
        View itemView = layoutInflater.inflate(R.layout.activity_home_screen, parent, false);
        return new HairStyleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HairStyleViewHolder holder, int position) {
        // get hairstyle in listHairStyle via position
        HairStyle hairStyle = listHairStyle.get(position);

        // bind data to view holder
        holder.url.setText(hairStyle.getUrl());
        holder.des.setText(hairStyle.getDescription());

    }

    @Override
    public int getItemCount() {
        return listHairStyle.size();
    }


    // Create an inner class HairStyleViewHolder
    class HairStyleViewHolder extends RecyclerView.ViewHolder {
        private TextView url;
        private TextView des;

        public HairStyleViewHolder(View itemView) {
            super(itemView);
            url = (TextView) itemView.findViewById(R.id.txt_url);
            des = (TextView) itemView.findViewById(R.id.txt_hairdes);
        }
    }
}
