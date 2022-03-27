package bluedragon.app.netcollegebartar.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import bluedragon.app.netcollegebartar.DataModels.User_Comments_Recycler_View_Data_Model;
import bluedragon.app.netcollegebartar.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Blue_Dragon on 5/9/2019.
 */
public class User_Comments_Fragment_Recycler_View_Adapter extends RecyclerView.Adapter<User_Comments_Fragment_Recycler_View_Adapter.User_Comments_Fragment_Recycler_View_view_Holder> {

    private Context context;
    private List<User_Comments_Recycler_View_Data_Model> Comments_List;

    public User_Comments_Fragment_Recycler_View_Adapter(Context context, List<User_Comments_Recycler_View_Data_Model> Comments_List)
    {
        this.context = context;
        this.Comments_List=Comments_List;
    }

    @NonNull
    @Override
    public User_Comments_Fragment_Recycler_View_view_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.user_comments_recyclerview_items_layout,parent,false);
        return new User_Comments_Fragment_Recycler_View_view_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull User_Comments_Fragment_Recycler_View_view_Holder holder, int position) {
        User_Comments_Recycler_View_Data_Model user_comments_recycler_view_data_model=Comments_List.get(position);
        Picasso.with(context).load(user_comments_recycler_view_data_model.getUser_Profile_URL()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(holder.User_Avatar);
        holder.User_Rate_To_Teacher.setRating(user_comments_recycler_view_data_model.getUser_Rating_Score());
        holder.UserCommenterName.setText(user_comments_recycler_view_data_model.getUser_Commenter_Name());
        holder.User_Comment_Text_View.setText(user_comments_recycler_view_data_model.getUser_Comment_Text());


    }

    @Override
    public int getItemCount() {
        return Comments_List.size();
    }

    public class User_Comments_Fragment_Recycler_View_view_Holder extends RecyclerView.ViewHolder
    {

        private CircleImageView User_Avatar;
        private CardView comment_cardview;
        private RatingBar User_Rate_To_Teacher;
        private TextView User_Comment_Text_View;
        private TextView UserCommenterName;
        public User_Comments_Fragment_Recycler_View_view_Holder(View itemView) {
            super(itemView);
            User_Avatar=(CircleImageView)itemView.findViewById(R.id.User_Avatar);
            comment_cardview=(CardView)itemView.findViewById(R.id.comment_cardview);
            User_Rate_To_Teacher=(RatingBar)itemView.findViewById(R.id.User_Rate_To_Teacher);
            User_Comment_Text_View=(TextView)itemView.findViewById(R.id.User_Comment_Text_View);
            UserCommenterName=(TextView)itemView.findViewById(R.id.UserCommenterName);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");
            User_Comment_Text_View.setTypeface(typeface);
            UserCommenterName.setTypeface(typeface);

        }
    }
}
