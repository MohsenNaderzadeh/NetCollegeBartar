package bluedragon.app.netcollegebartar.Views.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.Adapters.LastNewsRecyclerAdapter;
import bluedragon.app.netcollegebartar.Adapters.LibraryListAdapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.Library;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;

public class BookListActivity extends AppCompatActivity implements ApiService.OnBookList {

    Toolbar BookList_Tool_Bar_Class_Details;
    TextView toolbar_BooKList;
    Typeface typeface;
    int BookCategory;
    String BookCategoryName;
    RecyclerView recyclerView;
    LibraryListAdapter LibraryListAdapter;
    ApiService apiService;
    SqliteManager sqliteManager;
    ProgressBar BookListProgressBar;
    TextView BookList_error_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        Setviews();
        getintentdata();
        settoolbar();
        toolbar_BooKList.setText(BookCategoryName);
        if(apiService.internetConnection())
        {
            apiService.GetBookList(AppConfig.URL_LIBRARY,BookListActivity.this,BookCategory);

        }
        else
        {
            if(sqliteManager.checkLibraryBooksTBAvailbilty() && !sqliteManager.getLibraryBooksTBListOffline(BookCategory).isEmpty())
            {
                BookListProgressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(BookListActivity.this,2,LinearLayoutManager.VERTICAL,false));
                LibraryListAdapter=new LibraryListAdapter(getApplicationContext(),sqliteManager.getLibraryBooksTBListOffline(BookCategory));
                recyclerView.setAdapter(LibraryListAdapter);
            }
            else
            {
                BookListProgressBar.setVisibility(View.GONE);
                BookList_error_txt.setVisibility(View.VISIBLE);
            }
        }
    }
    private void Setviews()
    {
        typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        BookList_Tool_Bar_Class_Details = (Toolbar) findViewById(R.id.BookList_NetCollege_Tool_Bar);
        toolbar_BooKList=BookList_Tool_Bar_Class_Details.findViewById(R.id.BookList_Toolbar_TxtView_Title1);
        recyclerView=(RecyclerView)findViewById(R.id.booklistrecycler);
        BookListProgressBar=(ProgressBar)findViewById(R.id.BookListProgressBar);
        BookList_error_txt=(TextView)findViewById(R.id.BookList_error_txt);
        apiService=new ApiService(getApplicationContext());
        toolbar_BooKList.setTypeface(typeface);
        BookList_error_txt.setTypeface(typeface);
        sqliteManager=new SqliteManager(getApplicationContext());

    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(BookList_Tool_Bar_Class_Details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.class_details_activity_menu,menu);
        return true;
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
            BookCategory=mBundle.getInt("BookCategory");
            BookCategoryName=mBundle.getString("BookCategoryName");
        }
    }

    @Override
    public void OnBookListRecieved(ArrayList<Library> BookList) {
        if(!BookList.isEmpty())
        {
            sqliteManager.insertBookList(BookList);
            BookListProgressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(BookListActivity.this,2,LinearLayoutManager.VERTICAL,false));
            LibraryListAdapter=new LibraryListAdapter(getApplicationContext(),BookList);
            recyclerView.setAdapter(LibraryListAdapter);
        }
        else
        {
            BookListProgressBar.setVisibility(View.GONE);
            BookList_error_txt.setVisibility(View.VISIBLE);
        }

    }
}
