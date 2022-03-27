package bluedragon.app.netcollegebartar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bluedragon.app.netcollegebartar.DataModels.ExClassListModel;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.Views.Activities.RegisterFormActivity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Blue_Dragon on 6/22/2019.
 */
public class ExListRecyclerViewAdapter extends RecyclerView.Adapter<ExListRecyclerViewAdapter.ExListRecyclerViewHolder> {


    private Context context;
    private ArrayList<ExClassListModel> ExClassList;
    public ExListRecyclerViewAdapter(Context context,ArrayList<ExClassListModel> ExClassList)
    {
        this.context=context;
        this.ExClassList=ExClassList;
    }
    @NonNull
    @Override
    public ExListRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ex_classes_list_activity,parent,false);
        return new ExListRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExListRecyclerViewHolder holder, final int position) {

        ExClassListModel exClassList=ExClassList.get(position);
        Picasso.with(context).load(exClassList.getExLogoURL()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(holder.ExLogo);
        holder.ExCampName.setText(exClassList.getExName());
        holder.Teacher_Name_Value.setText(exClassList.getExTeacherName());
        holder.Camp_Duration_Value.setText(String.valueOf(exClassList.getExDuration()));
        holder.Camp_Fee_Text.setText(String.valueOf(exClassList.getExFee()));
        holder.ClassCapacityValue.setText(String.valueOf(exClassList.getExCapacity()));
        holder.ExClassCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, RegisterFormActivity.class);
                intent.putExtra("ClassID",ExClassList.get(holder.getAdapterPosition()).getExClassId());
                intent.putExtra("ClassName",ExClassList.get(holder.getAdapterPosition()).getExName());
                intent.putExtra("ClassType",1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ExClassList.size();
    }

    public class ExListRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        private CircleImageView ExLogo;


        private CardView ExClassCardview;
        private TextView ExCampName;
        private TextView Teacher_Name_Title;
        private TextView Teacher_Name_Value;
        private TextView Camp_Duration_Title;
        private TextView Camp_Duration_Value;
        private TextView ExCamp_day;
        private TextView Camp_fee_Title;
        private TextView Camp_Fee_Text;
        private TextView Camp_Currency;
        private TextView ClassCapacityText;
        private TextView ClassCapacityValue;
        private TextView ClassCapacitypoint;

        public ExListRecyclerViewHolder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");

            ExLogo=(CircleImageView)itemView.findViewById(R.id.Excamp_logo);

            ExCampName=(TextView)itemView.findViewById(R.id.ExCampName);
            Teacher_Name_Title=(TextView)itemView.findViewById(R.id.Teacher_Name_Title);
            Teacher_Name_Value=(TextView)itemView.findViewById(R.id.Teacher_Name_Value);
            Camp_Duration_Title=(TextView)itemView.findViewById(R.id.Camp_Duration_Title);
            Camp_Duration_Value=(TextView)itemView.findViewById(R.id.Camp_Duration_Value);
            ExCamp_day=(TextView) itemView.findViewById(R.id.ExCamp_day);
            Camp_fee_Title=(TextView)itemView.findViewById(R.id.Camp_fee_Title);
            Camp_Fee_Text=(TextView)itemView.findViewById(R.id.Camp_Fee_Text);
            Camp_Currency=(TextView)itemView.findViewById(R.id.Camp_Currency);
            ClassCapacityText=(TextView)itemView.findViewById(R.id.ClassCapacityText);
            ClassCapacityValue=(TextView)itemView.findViewById(R.id.ClassCapacityValue);
            ClassCapacitypoint=(TextView)itemView.findViewById(R.id.ClassCapacitypoint);

            ExClassCardview=(CardView)itemView.findViewById(R.id.ExClassCardview);

            ExCampName.setTypeface(typeface);
            Teacher_Name_Title.setTypeface(typeface);
            Teacher_Name_Value.setTypeface(typeface);
            Camp_Duration_Title.setTypeface(typeface);
            Camp_Duration_Value.setTypeface(typeface);
            ExCamp_day.setTypeface(typeface);
            Camp_fee_Title.setTypeface(typeface);
            Camp_Fee_Text.setTypeface(typeface);
            Camp_Currency.setTypeface(typeface);
            ClassCapacityText.setTypeface(typeface);
            ClassCapacityValue.setTypeface(typeface);
            ClassCapacitypoint.setTypeface(typeface);
        }
    }

}
