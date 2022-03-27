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

import bluedragon.app.netcollegebartar.DataModels.ImageGallery;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.Views.Activities.ImageGalleryFullActivity;

/**
 * Created by Blue_Dragon on 6/29/2019.
 */
public class ImageGalleryRecyclerAdapter extends RecyclerView.Adapter<ImageGalleryRecyclerAdapter.ImageGalleryViewHolder> {

    private Context context;
    private ArrayList<ImageGallery> ImageList;

    public ImageGalleryRecyclerAdapter(Context context, ArrayList<ImageGallery> imageList)
    {

        this.context = context;
        ImageList = imageList;
    }
    @NonNull
    @Override
    public ImageGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.imagegalleryrecyclerviewitems,parent,false);
        return new ImageGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageGalleryViewHolder holder, int position) {
        ImageGallery imageGallery=ImageList.get(position);
        Picasso.with(context).load(imageGallery.getImageLogoUrl()).into(holder.ImageGallery_recycler);
        holder.ImageCaption.setText(imageGallery.getImageCaption());
        holder.ImageGallreyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ImageGalleryFullActivity.class);
                intent.putExtra("ImageID",ImageList.get(holder.getAdapterPosition()).getImageID());
                intent.putExtra("Imageurl",ImageList.get(holder.getAdapterPosition()).getImageLogoUrl());
                intent.putExtra("ImageCaption",ImageList.get(holder.getAdapterPosition()).getImageCaption());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ImageList.size();
    }

    public class ImageGalleryViewHolder extends RecyclerView.ViewHolder
    {
        CardView ImageGallreyCardView;
        ImageView ImageGallery_recycler;
        TextView ImageCaption;
        public ImageGalleryViewHolder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");

            ImageGallreyCardView=(CardView)itemView.findViewById(R.id.imagegallerycardview);
            ImageGallery_recycler=(ImageView)itemView.findViewById(R.id.ImageGallery_recycler);
            ImageCaption=(TextView)itemView.findViewById(R.id.ImageCaption);
            ImageCaption.setTypeface(typeface);
        }
    }
}
