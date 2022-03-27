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

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.LastNews;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.Views.Activities.LastNewsFullActivity;

/**
 * Created by Blue_Dragon on 6/28/2019.
 */
public class LastNewsRecyclerAdapter extends RecyclerView.Adapter<LastNewsRecyclerAdapter.LastNewsRecyclerViewHolder> {

    private Context context;
    private ArrayList<LastNews> LastNewsList;
    public LastNewsRecyclerAdapter(Context context, ArrayList<LastNews> lastNewsList)
    {

        this.context = context;
        LastNewsList = lastNewsList;
    }
    @NonNull
    @Override
    public LastNewsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.last_news_recycler_view_item_layout,parent,false);
        return new LastNewsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LastNewsRecyclerViewHolder holder, final int position) {
         LastNews lastNews=LastNewsList.get(position);
        Picasso.with(context).load(lastNews.getLastNewsLogo()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(holder.LastNewsLogo);
        holder.Last_News_Title.setText(lastNews.getLastNewsTitle());
        holder.Last_News_Main_Text.setText(lastNews.getLastNewsText());
        holder.News_Date_Value.setText(lastNews.getLastNewsDate());
        holder.LastNewsCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LastNewsFullActivity.class);
                intent.putExtra("LastNews_ID",LastNewsList.get(holder.getAdapterPosition()).getLastNewsID());
                intent.putExtra("LastNews_Logo",LastNewsList.get(holder.getAdapterPosition()).getLastNewsLogo());
                intent.putExtra("LastNewsTitle",LastNewsList.get(holder.getAdapterPosition()).getLastNewsTitle());
                intent.putExtra("LastNewsMainText",LastNewsList.get(holder.getAdapterPosition()).getLastNewsText());
                intent.putExtra("LastNewsDate",LastNewsList.get(holder.getAdapterPosition()).getLastNewsDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return LastNewsList.size() ;
    }

    public class  LastNewsRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        CardView LastNewsCardview;
        ImageView LastNewsLogo;
        TextView Last_News_Title ,Last_News_Main_Text,News_Date_Title,News_Date_Value;
        public LastNewsRecyclerViewHolder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");
            LastNewsLogo=(ImageView)itemView.findViewById(R.id.LastNewsLogo);
            Last_News_Title=(TextView)itemView.findViewById(R.id.Last_News_Title);
            Last_News_Main_Text=(TextView)itemView.findViewById(R.id.Last_News_Main_Text);
            News_Date_Title=(TextView)itemView.findViewById(R.id.News_Date_Title);
            News_Date_Value=(TextView)itemView.findViewById(R.id.News_Date_Value);
            LastNewsCardview=(CardView)itemView.findViewById(R.id.LastNewsCardview);
            Last_News_Title.setTypeface(typeface);
            Last_News_Main_Text.setTypeface(typeface);
            News_Date_Title.setTypeface(typeface);
            News_Date_Value.setTypeface(typeface);
        }
    }
}
