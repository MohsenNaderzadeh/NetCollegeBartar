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

import bluedragon.app.netcollegebartar.DataModels.RegisteredExClassesDataModel;
import bluedragon.app.netcollegebartar.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Blue_Dragon on 6/27/2019.
 */
public class RegisteredExClassRecyclerViewAdapter extends RecyclerView.Adapter<RegisteredExClassRecyclerViewAdapter.RegisteredExClassRecyclerViewHolder> {

    private Context context;
    private ArrayList<RegisteredExClassesDataModel>RegisteredExClassList;
    public RegisteredExClassRecyclerViewAdapter(Context context, ArrayList<RegisteredExClassesDataModel> registeredExClassList)
    {
        this.context = context;
        RegisteredExClassList = registeredExClassList;
    }
    @NonNull
    @Override
    public RegisteredExClassRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_registered_exclasses_recylcerview_layout,parent,false);
        return new RegisteredExClassRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegisteredExClassRecyclerViewHolder holder, int position) {
        RegisteredExClassesDataModel registeredExClassesDataModel=RegisteredExClassList.get(position);
        Picasso.with(context).load(registeredExClassesDataModel.getRegisteredExClassLogo()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(holder.RegisteredCampLogo);
        holder.RegisteredExCampName.setText(registeredExClassesDataModel.getRegisteredExClassName());
        holder.Registered_Teacher_Name_Value.setText(registeredExClassesDataModel.getRegisteredExClassTeacherName());
        holder.Registered_Camp_Date.setText(registeredExClassesDataModel.getRegisteredExClassRegDate());
    }

    @Override
    public int getItemCount() {
        return RegisteredExClassList.size();
    }

    public class RegisteredExClassRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        private CircleImageView RegisteredCampLogo;
        TextView RegisteredExCampName,Registered_Teacher_Name_Title,Registered_Teacher_Name_Value,Registered_Camp_Date_Title,Registered_Camp_Date;
        public RegisteredExClassRecyclerViewHolder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");
            RegisteredCampLogo=(CircleImageView)itemView.findViewById(R.id.RegisteredCampLogo);
            RegisteredExCampName=(TextView)itemView.findViewById(R.id.RegisteredExCampName);
            Registered_Teacher_Name_Title=(TextView)itemView.findViewById(R.id.Registered_Teacher_Name_Title);
            Registered_Teacher_Name_Value=(TextView)itemView.findViewById(R.id.Registered_Teacher_Name_Value);
            Registered_Camp_Date_Title=(TextView)itemView.findViewById(R.id.Registered_Camp_Date_Title);
            Registered_Camp_Date=(TextView)itemView.findViewById(R.id.Registered_Camp_Date);
            RegisteredExCampName.setTypeface(typeface);
            Registered_Teacher_Name_Title.setTypeface(typeface);
            Registered_Teacher_Name_Value.setTypeface(typeface);
            Registered_Camp_Date_Title.setTypeface(typeface);
            Registered_Camp_Date.setTypeface(typeface);
        }
    }
}
