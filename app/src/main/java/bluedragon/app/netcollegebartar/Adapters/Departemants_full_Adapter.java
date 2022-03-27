package bluedragon.app.netcollegebartar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bluedragon.app.netcollegebartar.DataModels.Departemants_full_DataModel;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.Views.Activities.Details_Activity;

/**
 * Created by Blue_Dragon on 1/20/2019.
 */
public class Departemants_full_Adapter extends RecyclerView.Adapter<Departemants_full_Adapter.Departemants_full_View_Holder> {


    private Context context;
    private List<Departemants_full_DataModel> departemantsList;
    public Departemants_full_Adapter(Context context, List<Departemants_full_DataModel> departemantsList)
    {
        this.context=context;
        this.departemantsList=departemantsList;

    }

    @NonNull
    @Override
    public Departemants_full_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.departmans_recycler_layout,null);
        return new Departemants_full_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Departemants_full_View_Holder holder, int position) {

        Departemants_full_DataModel departemants=departemantsList.get(position);

        //load Departemants_logo
        Picasso.with(context).load(departemants.getDepartemants_Image()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(holder.Departemants_logo);
        //get Departemants Title
        holder.Departemants_Name.setText(departemants.getDepartemant_Title());
        holder.Departemants_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(context,Details_Activity.class);
                mIntent.putExtra("Departemants_ID",departemantsList.get(holder.getAdapterPosition()).getId());
                mIntent.putExtra("Departemants_Name",departemantsList.get(holder.getAdapterPosition()).getDepartemant_Title());
                mIntent.putExtra("Departemants_Logo",departemantsList.get(holder.getAdapterPosition()).getDepartemants_Image());
                context.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return departemantsList.size();
    }


    public class Departemants_full_View_Holder extends RecyclerView.ViewHolder
    {
        private ImageView Departemants_logo;
        private TextView Departemants_Name;
        private CardView Departemants_Card;

        public Departemants_full_View_Holder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");
            Departemants_logo=itemView.findViewById(R.id.Image_recycler_departemants);
            Departemants_Name=itemView.findViewById(R.id.departemants_name);
            Departemants_Card=itemView.findViewById(R.id.departemants_cardview);
            Departemants_Name.setTypeface(typeface);

        }
    }
}
