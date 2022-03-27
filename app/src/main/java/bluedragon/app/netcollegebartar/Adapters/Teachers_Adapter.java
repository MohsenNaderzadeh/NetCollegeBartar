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
import bluedragon.app.netcollegebartar.DataModels.Teachers;
import bluedragon.app.netcollegebartar.Views.Activities.Teacher_Profile_Acitivity;

/**
 * Created by Blue_Dragon on 12/1/2018.
 */
public class Teachers_Adapter extends RecyclerView.Adapter<Teachers_Adapter.Teachers_Adapter_view_Holder> {

    private Context context;
    private List<Teachers> Teacher_List;

    public Teachers_Adapter(Context context, List<Teachers> Teachers_list)
    {
        this.context=context;
        this.Teacher_List=Teachers_list;

    }

    @NonNull
    @Override
    public Teachers_Adapter_view_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.teachers_recyclerview_adapter,null);
        return new Teachers_Adapter_view_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Teachers_Adapter_view_Holder holder, int position) {

        Teachers Teachers=Teacher_List.get(position);

        //load Teachers_Photo
        Picasso.with(context).load(Teachers.getTeachers_photo()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(holder.Teachers_photo);
        //get Departemants Title
        holder.Teachers_name.setText(Teachers.getTeachers_full_name());
        holder.Teachers_Departement_name.setText(Teachers.getTeachers_Departemants_name());
        holder.Teacher_Card_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send Needed Data for Teachers_Profile_Activity
                Intent Teacher_Profile_Activity_Send_Data=new Intent(context,Teacher_Profile_Acitivity.class);
                Teacher_Profile_Activity_Send_Data.putExtra("Teacher_ID",Teacher_List.get(holder.getAdapterPosition()).getId());
                Teacher_Profile_Activity_Send_Data.putExtra("Teachers_full_name",Teacher_List.get(holder.getAdapterPosition()).getTeachers_full_name());
                Teacher_Profile_Activity_Send_Data.putExtra("Teachers_photo",Teacher_List.get(holder.getAdapterPosition()).getTeachers_photo());
                Teacher_Profile_Activity_Send_Data.putExtra("Teacher_Email",Teacher_List.get(holder.getAdapterPosition()).getTeacher_Email());
                Teacher_Profile_Activity_Send_Data.putExtra("Teachers_Skill",Teacher_List.get(holder.getAdapterPosition()).getTeachers_Skill());
                Teacher_Profile_Activity_Send_Data.putExtra("Teachers_Resume",Teacher_List.get(holder.getAdapterPosition()).getTeachers_Resume());
                Teacher_Profile_Activity_Send_Data.putExtra("Teachers_Departemant_ID",Teacher_List.get(holder.getAdapterPosition()).getTeachers_Departemant_ID());
                context.startActivity(Teacher_Profile_Activity_Send_Data);




            }
        });
    }

    @Override
    public int getItemCount() {
        return Teacher_List.size();
    }

    public class Teachers_Adapter_view_Holder extends RecyclerView.ViewHolder
    {
        private ImageView Teachers_photo;
        private TextView Teachers_name;
        private TextView Teachers_Departement_name;
        private CardView Teacher_Card_View;

        public Teachers_Adapter_view_Holder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");
            Teachers_photo=itemView.findViewById(R.id.Image_recycler_teachers);
            Teachers_name=itemView.findViewById(R.id.teachers_name);
            Teachers_Departement_name=itemView.findViewById(R.id.departemans_group_name);
            Teacher_Card_View=itemView.findViewById(R.id.Teacher_Card_View);
            Teachers_name.setTypeface(typeface);
            Teachers_Departement_name.setTypeface(typeface);

        }
    }
}
