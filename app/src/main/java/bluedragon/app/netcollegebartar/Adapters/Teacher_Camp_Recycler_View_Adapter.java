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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.DataModels.Camps;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.Views.Activities.RegisterFormActivity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Blue_Dragon on 6/1/2019.
 */
public class Teacher_Camp_Recycler_View_Adapter extends RecyclerView.Adapter<Teacher_Camp_Recycler_View_Adapter.Teacher_Camp_ViewHolder> {

    private Context context;
    private ArrayList<Camps> CampsList;
    private ApiService apiService;
    private SessionManager sessionManager;
    public Teacher_Camp_Recycler_View_Adapter(Context context, ArrayList<Camps> campsList, ApiService apiService, SessionManager sessionManager)
    {

        this.context = context;
        this.CampsList = campsList;
        this.apiService=apiService;
        this.sessionManager=sessionManager;
    }

    @NonNull
    @Override
    public Teacher_Camp_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.teacher_camp_recyclerview_item_layout,parent,false);
        return new Teacher_Camp_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Teacher_Camp_ViewHolder holder, int position) {
        final Camps camps=CampsList.get(position);
        Picasso.with(context).load(camps.getCamp_Logo_Url()).into(holder.camp_logo);
        holder.CampName.setText(camps.getCamp_Name());
        holder.Camp_Duration_Value.setText(String.valueOf(camps.getCamp_Duration_Value()));
        holder.Camp_Fee_Text.setText(String.valueOf(camps.getCamp_fee()));
        holder.ClassCapacityValue.setText(String.valueOf(camps.getCampCapacity()));
        holder.teacher_camp_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apiService.internetConnection())
                {
                    if(sessionManager.isLoggedIn())
                    {
                        Intent intent=new Intent(context, RegisterFormActivity.class);
                        intent.putExtra("ClassID",CampsList.get(holder.getAdapterPosition()).getCamp_Id());
                        intent.putExtra("ClassName",CampsList.get(holder.getAdapterPosition()).getCamp_Name());
                        intent.putExtra("ClassType",1);
                        context.startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(context,"برای ثبت نام در این دوره باید وارد حساب کاربری خود شوید و یا ثبت نام کنید ",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(context,"لطفا اتصال خود به اینترنت را بررسی کنید ",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return CampsList.size();
    }

    public class Teacher_Camp_ViewHolder extends RecyclerView.ViewHolder
    {

        CircleImageView camp_logo;
        TextView CampName;
        TextView Camp_Duration_Title;
        TextView Camp_Duration_Value;
        TextView Camp_day;
        TextView Camp_fee_Title;
        TextView Camp_Fee_Text;
        TextView Camp_Currency;
        TextView ClassCapacityText;
        TextView ClassCapacityValue;
        TextView ClassCapacitypoint;
        CardView teacher_camp_cardview;

        public Teacher_Camp_ViewHolder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");
            camp_logo=(CircleImageView)itemView.findViewById(R.id.camp_logo);
            CampName=(TextView) itemView.findViewById(R.id.CampName);
            Camp_Duration_Title=(TextView) itemView.findViewById(R.id.Camp_Duration_Title);
            Camp_Duration_Value=(TextView) itemView.findViewById(R.id.Camp_Duration_Value);
            Camp_day=(TextView) itemView.findViewById(R.id.Camp_day);
            Camp_fee_Title=(TextView) itemView.findViewById(R.id.Camp_fee_Title);
            Camp_Fee_Text=(TextView) itemView.findViewById(R.id.Camp_Fee_Text);
            Camp_Currency=(TextView) itemView.findViewById(R.id.Camp_Currency);
            ClassCapacityText=(TextView)itemView.findViewById(R.id.ClassCapacityText);
            ClassCapacityValue=(TextView)itemView.findViewById(R.id.ClassCapacityValue);
            ClassCapacitypoint=(TextView)itemView.findViewById(R.id.ClassCapacitypoint);
            teacher_camp_cardview=(CardView)itemView.findViewById(R.id.teacher_camp_cardview);
            CampName.setTypeface(typeface);
            Camp_Duration_Title.setTypeface(typeface);
            Camp_Duration_Value.setTypeface(typeface);
            Camp_day.setTypeface(typeface);
            Camp_fee_Title.setTypeface(typeface);
            Camp_Fee_Text.setTypeface(typeface);
            Camp_Currency.setTypeface(typeface);
            ClassCapacityText.setTypeface(typeface);
            ClassCapacityValue.setTypeface(typeface);
            ClassCapacitypoint.setTypeface(typeface);
        }
    }
}
