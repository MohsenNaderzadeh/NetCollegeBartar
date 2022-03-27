package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.icu.text.DisplayContext;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;

public class BookFullActivity extends AppCompatActivity {

    Toolbar Book_full_toolbar;
    TextView Book_full_toolbar_title,fullBookTitle,full_book_description;
    Button DownloadBook;
    Typeface typeface;
    ImageView BookFullLogo;
    int BookID,BookCategory;
    String BookName,BookDescription,BookLogo,BookDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_full);
        setviews();
        settoolbar();
        getintentdata();
        Book_full_toolbar_title.setText(BookName);
        full_book_description.setText(BookDescription);
        DownloadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(BookDownload));
                startActivity(Intent.createChooser(intent,"Choose Your Browser"));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.class_details_activity_menu,menu);
        return true;
    }
    public void setviews()
    {
        DownloadBook=(Button)findViewById(R.id.DownloadBook);
        fullBookTitle=(TextView)findViewById(R.id.fullBookTitle);
        typeface= Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        BookFullLogo=(ImageView)findViewById(R.id.BookFullLogo);
        Book_full_toolbar = (Toolbar) findViewById(R.id.Book_full_toolbar);
        Book_full_toolbar_title=Book_full_toolbar.findViewById(R.id.Book_full_toolbar_title);
        full_book_description=(TextView)findViewById(R.id.full_book_description);
        Book_full_toolbar_title.setTypeface(typeface);
        fullBookTitle.setTypeface(typeface);
        full_book_description.setTypeface(typeface);
        DownloadBook.setTypeface(typeface);


    }

    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(Book_full_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_backbtn_Class_details_activity)
        {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void getintentdata()
    {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            BookID=mBundle.getInt("BookID");
            BookCategory=mBundle.getInt("BookCategory");
            BookName=mBundle.getString("BookName");
            BookDescription=mBundle.getString("BookDescription");
            BookLogo=mBundle.getString("BookLogo");
            BookDownload=mBundle.getString("BookDownload");
        }
    }
}
