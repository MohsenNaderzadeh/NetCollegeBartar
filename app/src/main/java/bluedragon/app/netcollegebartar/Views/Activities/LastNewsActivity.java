package bluedragon.app.netcollegebartar.Views.Activities;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.Adapters.ExListRecyclerViewAdapter;
import bluedragon.app.netcollegebartar.Adapters.LastNewsRecyclerAdapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.LastNews;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;

public class LastNewsActivity extends AppCompatActivity implements ApiService.OnLastNewsList {

    Toolbar LastNews_Toolbar;
    TextView LastNews_Toolbar_title;
    Typeface typeface;
    RecyclerView Last_News_Recycler;
    LastNewsRecyclerAdapter lastNewsRecyclerAdapter;
    ApiService apiService;
    ProgressBar LastNews_Progressbar;
    TextView LastNewsErrorMessage;
    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_news);
        setviews();
        settoolbar();
        if(apiService.internetConnection())
        {
            apiService.GetLastNewsList(AppConfig.URL_LAST_NEWS,LastNewsActivity.this);

        }
        else
        {
            if(sqliteManager.checkNewsTBAvailbilty())
            {
                LastNews_Progressbar.setVisibility(View.GONE);
                Last_News_Recycler.setVisibility(View.VISIBLE);
                Last_News_Recycler.setHasFixedSize(true);
                Last_News_Recycler.setLayoutManager(new LinearLayoutManager(LastNewsActivity.this, LinearLayoutManager.VERTICAL,false));
                lastNewsRecyclerAdapter=new LastNewsRecyclerAdapter(getApplicationContext(),sqliteManager.getLastNewsListOffline());
                Last_News_Recycler.setAdapter(lastNewsRecyclerAdapter);
            }
            else
            {
                LastNews_Progressbar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"لطفا اتصال خود را به اینترنت بررسی کنید",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void setviews()
    {

        typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        LastNews_Toolbar = (Toolbar) findViewById(R.id.LastNews_Toolbar);
        LastNews_Toolbar_title=LastNews_Toolbar.findViewById(R.id.LastNews_Toolbar_title);
        LastNews_Toolbar_title.setTypeface(typeface);
        Last_News_Recycler=(RecyclerView)findViewById(R.id.Last_News_Recycler);
        apiService=new ApiService(LastNewsActivity.this);
        LastNews_Progressbar=(ProgressBar)findViewById(R.id.LastNews_Progressbar);
        LastNewsErrorMessage=(TextView)findViewById(R.id.LastNewsErrorMessage);
        sqliteManager=new SqliteManager(this);
    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(LastNews_Toolbar);
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

    @Override
    public void OnLastNewsList(ArrayList<LastNews> LastNewsList) {

        if(!LastNewsList.isEmpty())
        {
            sqliteManager.insertNews(LastNewsList);
            LastNews_Progressbar.setVisibility(View.GONE);
            Last_News_Recycler.setVisibility(View.VISIBLE);
            Last_News_Recycler.setHasFixedSize(true);
            Last_News_Recycler.setLayoutManager(new LinearLayoutManager(LastNewsActivity.this, LinearLayoutManager.VERTICAL,false));
            lastNewsRecyclerAdapter=new LastNewsRecyclerAdapter(getApplicationContext(),LastNewsList);
            Last_News_Recycler.setAdapter(lastNewsRecyclerAdapter);
        }
        else
        {
            LastNews_Progressbar.setVisibility(View.GONE);
            LastNewsErrorMessage.setVisibility(View.VISIBLE);
        }

    }
}
