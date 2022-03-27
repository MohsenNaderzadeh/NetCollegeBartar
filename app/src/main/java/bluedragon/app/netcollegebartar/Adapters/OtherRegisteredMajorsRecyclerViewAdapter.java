package bluedragon.app.netcollegebartar.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.OtherMajorRegisteredDataModel;
import bluedragon.app.netcollegebartar.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Blue_Dragon on 6/27/2019.
 */
public class OtherRegisteredMajorsRecyclerViewAdapter extends RecyclerView.Adapter<OtherRegisteredMajorsRecyclerViewAdapter.OtherRegisteredMajorRecyclerviewHolder> {

    private Context context;
    private ArrayList<OtherMajorRegisteredDataModel>OtherMajorRegisteredList;
public OtherRegisteredMajorsRecyclerViewAdapter(Context context, ArrayList<OtherMajorRegisteredDataModel> otherMajorRegisteredList)
{

    this.context = context;
    OtherMajorRegisteredList = otherMajorRegisteredList;
}
    @NonNull
    @Override
    public OtherRegisteredMajorRecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_show_all_others_registered_classes_recycler_view_layout,parent,false);
        return new OtherRegisteredMajorRecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherRegisteredMajorRecyclerviewHolder holder, int position) {
        OtherMajorRegisteredDataModel otherMajorRegisteredDataModel=OtherMajorRegisteredList.get(position);
        Picasso.with(context).load(otherMajorRegisteredDataModel.getMajor_logo()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(holder.RegisteredOtherClassLogo);
        holder.RegisteredOtherClassName.setText(otherMajorRegisteredDataModel.getMajor_Name());
        holder.Registered_OtherClasses_Date.setText(otherMajorRegisteredDataModel.getRegisterDate());
    }

    @Override
    public int getItemCount() {
        return OtherMajorRegisteredList.size();
    }

    public class OtherRegisteredMajorRecyclerviewHolder extends RecyclerView.ViewHolder{

        CircleImageView RegisteredOtherClassLogo;
        TextView RegisteredOtherClassName,Registered_OtherClasses_Date_Title,Registered_OtherClasses_Date;

        public OtherRegisteredMajorRecyclerviewHolder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");
            RegisteredOtherClassLogo=(CircleImageView)itemView.findViewById(R.id.RegisteredOtherClassLogo);
            RegisteredOtherClassName=(TextView)itemView.findViewById(R.id.RegisteredOtherClassName);
            Registered_OtherClasses_Date_Title=(TextView)itemView.findViewById(R.id.Registered_OtherClasses_Date_Title);
            Registered_OtherClasses_Date=(TextView)itemView.findViewById(R.id.Registered_OtherClasses_Date);
            RegisteredOtherClassName.setTypeface(typeface);
            Registered_OtherClasses_Date_Title.setTypeface(typeface);
            Registered_OtherClasses_Date.setTypeface(typeface);
            Registered_OtherClasses_Date.setTypeface(typeface);

        }
    }
}
