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

import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.DataModels.Services;
import bluedragon.app.netcollegebartar.Views.Activities.OtherServicesDetailsActivity;
import bluedragon.app.netcollegebartar.Views.Activities.Teacher_Profile_Acitivity;

/**
 * Created by Blue_Dragon on 12/2/2018.
 */
public class Services_Adapter extends RecyclerView.Adapter<Services_Adapter.Services_View_Holder> {

    private Context context;
    private List<Services> Services_List;
    public Services_Adapter(Context context, List<Services> Services_List)
    {
        this.context=context;
        this.Services_List=Services_List;

    }
    @NonNull
    @Override
    public Services_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.services_recyclerview_layout,null);
        return new Services_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Services_View_Holder holder, int position) {

        Services services=Services_List.get(position);

        //load Teachers_Photo
        Picasso.with(context).load(services.getService_Logo()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(holder.Service_Logo);
        //get Departemants Title
        holder.Service_Name.setText(services.getService_Name());
        holder.Services_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send Needed Data for Teachers_Profile_Activity
                Intent Other_Services_Send_Data_Intent=new Intent(context,OtherServicesDetailsActivity.class);
                Other_Services_Send_Data_Intent.putExtra("ClassID",Services_List.get(holder.getAdapterPosition()).getId());
                Other_Services_Send_Data_Intent.putExtra("ClassName",Services_List.get(holder.getAdapterPosition()).getService_Name());
                Other_Services_Send_Data_Intent.putExtra("ClassLogo",Services_List.get(holder.getAdapterPosition()).getService_Logo());
                Other_Services_Send_Data_Intent.putExtra("Class_Description",Services_List.get(holder.getAdapterPosition()).getServices_Description());
                context.startActivity(Other_Services_Send_Data_Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Services_List.size();
    }

    public class Services_View_Holder extends RecyclerView.ViewHolder
    {
        private ImageView Service_Logo;
        private TextView Service_Name;
        private CardView Services_CardView;
        public Services_View_Holder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");
            Service_Logo=itemView.findViewById(R.id.Image_recycler_Services);
            Service_Name=itemView.findViewById(R.id.Service_Name);
            Services_CardView=itemView.findViewById(R.id.Services_CardView);
            Service_Name.setTypeface(typeface);
        }
    }
}
