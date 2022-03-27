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

import bluedragon.app.netcollegebartar.Views.Activities.Class_Details_Activity;
import bluedragon.app.netcollegebartar.DataModels.Classes;
import bluedragon.app.netcollegebartar.R;

/**
 * Created by Blue_Dragon on 12/28/2018.
 */
public class Actvity_details_recyclerview_adapter extends RecyclerView.Adapter<Actvity_details_recyclerview_adapter.Activity_details_view_holder> {

    private static final String TAG = "Actvity_details_recyclerview_adapter";
    private  Context context;
    private List<Classes> Classes_List;

    public Actvity_details_recyclerview_adapter(Context context, List<Classes> Classes_List)
    {
        this.context=context;
        this.Classes_List=Classes_List;

    }
    @NonNull
    @Override
    public Activity_details_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recyclerview_activity_detail,null);
        return new Activity_details_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Activity_details_view_holder holder, int position) {
        final Classes classes=Classes_List.get(position);

        //load details recylcerview imageview
        Picasso.with(context).load(classes.getClass_Logo()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(holder.Details_Image_view);
        holder.Details_Textview.setText(classes.getClass_Name());
        holder.Details_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Details_Intent=new Intent(context,Class_Details_Activity.class);
                Details_Intent.putExtra("ClassID",Classes_List.get(holder.getAdapterPosition()).getClass_id());
                Details_Intent.putExtra("Class_Depatemants_ID",Classes_List.get(holder.getAdapterPosition()).getDepartemant_id());
                Details_Intent.putExtra("ClassName",Classes_List.get(holder.getAdapterPosition()).getClass_Name());
                Details_Intent.putExtra("ClassLogo",Classes_List.get(holder.getAdapterPosition()).getClass_Logo());
                Details_Intent.putExtra("Class_Description",Classes_List.get(holder.getAdapterPosition()).getClass_Description());
                context.startActivity(Details_Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Classes_List.size();
    }

    public class Activity_details_view_holder extends RecyclerView.ViewHolder
    {
        private ImageView Details_Image_view;
        private TextView Details_Textview;
        private CardView Details_CardView;

        public Activity_details_view_holder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");
            Details_Image_view=itemView.findViewById(R.id.Image_recycler_details);
            Details_Textview=itemView.findViewById(R.id.details_name);
            Details_CardView=itemView.findViewById(R.id.details_cardview);
            Details_Textview.setTypeface(typeface);

        }
    }
}
