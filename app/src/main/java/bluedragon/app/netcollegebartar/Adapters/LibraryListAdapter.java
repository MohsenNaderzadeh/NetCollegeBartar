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

import bluedragon.app.netcollegebartar.DataModels.Library;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.Views.Activities.BookFullActivity;

/**
 * Created by Blue_Dragon on 6/28/2019.
 */
public class LibraryListAdapter extends RecyclerView.Adapter<LibraryListAdapter.LibraryViewHolder> {


    private Context context;
    private ArrayList<Library>BookList;
    public LibraryListAdapter(Context context, ArrayList<Library> bookList)
    {

        this.context = context;
        BookList = bookList;
    }
    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.booklistrecyclerlayout,parent,false);
        return new LibraryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LibraryViewHolder holder, final int position) {

        Library library=BookList.get(position);
        holder.book_name.setText(library.getBookName());
        Picasso.with(context).load(library.getBookImage()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(holder.BookLogo_recycler);
        holder.Book_list_Cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BookFullActivity.class);
                intent.putExtra("BookID",BookList.get(holder.getAdapterPosition()).getBook_id());
                intent.putExtra("BookName",BookList.get(holder.getAdapterPosition()).getBookName());
                intent.putExtra("BookCategory",BookList.get(holder.getAdapterPosition()).getBook_category());
                intent.putExtra("BookDescription",BookList.get(holder.getAdapterPosition()).getBooKDescription());
                intent.putExtra("BookLogo",BookList.get(holder.getAdapterPosition()).getBookImage());
                intent.putExtra("BookDownload",BookList.get(holder.getAdapterPosition()).getBookDownloadLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return BookList.size();
    }

    public class LibraryViewHolder extends RecyclerView.ViewHolder
    {
        ImageView BookLogo_recycler;
        TextView book_name;
        CardView Book_list_Cardview;

        public LibraryViewHolder(View itemView) {
            super(itemView);
            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/iransansdn.ttf");
            BookLogo_recycler=(ImageView)itemView.findViewById(R.id.BookLogo_recycler);
            book_name=(TextView)itemView.findViewById(R.id.book_name);
            Book_list_Cardview=(CardView)itemView.findViewById(R.id.Book_list_Cardview);
            book_name.setTypeface(typeface);
        }
    }
}
