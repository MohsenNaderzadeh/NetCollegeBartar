package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bluedragon.app.netcollegebartar.Adapters.Actvity_details_recyclerview_adapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.DataModels.Classes;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;

public class Details_Activity extends AppCompatActivity {
    private ApiService apiService;
    ArrayList<Classes> ClassesList;
    int departemant_id;
    ProgressBar Full_Classes_ProgressBar;
    RecyclerView details_activity_recyclerview;
    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_);
        settoolbar();
        ImageView departemants_logo = (ImageView) findViewById(R.id.departemants_logo);
        Toolbar Net_College_Tool_Bar_deatil = (Toolbar) findViewById(R.id.app_bar);
        Full_Classes_ProgressBar=(ProgressBar)findViewById(R.id.Activity_Detail_Progressbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/iransansdn.ttf");
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout);
        TextView details_internetcheck=(TextView)findViewById(R.id.details_internetcheck);
        details_internetcheck.setTypeface(typeface);
        collapsingToolbarLayout.setCollapsedTitleTypeface(typeface);
        collapsingToolbarLayout.setExpandedTitleTypeface(typeface);
        TextView textViewtitle=(TextView)findViewById(R.id.textview_title);
        textViewtitle.setTypeface(typeface);
        sqliteManager=new SqliteManager(Details_Activity.this);
        apiService=new ApiService(Details_Activity.this);



        details_activity_recyclerview=(RecyclerView)findViewById(R.id.recyclerview_deatails_activity);
        details_activity_recyclerview.setHasFixedSize(true);
       details_activity_recyclerview.setLayoutManager(new GridLayoutManager(Details_Activity.this,2, LinearLayoutManager.VERTICAL,false));
       ClassesList=new ArrayList<>();




        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
             departemant_id = mBundle.getInt("Departemants_ID");
            collapsingToolbarLayout.setTitle(mBundle.getString("Departemants_Name"));

            String imageuri = mBundle.getString("Departemants_Logo");
            Picasso.with(getBaseContext()).load(imageuri).into(departemants_logo);

        }
        if(apiService.internetConnection())
        {
            apiService.getfullclaseslist(departemant_id,ClassesList,details_activity_recyclerview,Full_Classes_ProgressBar,sqliteManager);

        }else
        {
            if(sqliteManager.checkClassesTBAvailbilty())
            {
                if(!sqliteManager.getAllClassesListOffline(departemant_id).isEmpty())
                {
                    Actvity_details_recyclerview_adapter recyclerview_details = new Actvity_details_recyclerview_adapter(getApplicationContext(), sqliteManager.getAllClassesListOffline(departemant_id));
                    details_activity_recyclerview.setAdapter(recyclerview_details);
                    Full_Classes_ProgressBar.setVisibility(View.GONE);
                    details_activity_recyclerview.setVisibility(View.VISIBLE);
                }
                else
                {
                    details_internetcheck.setVisibility(View.VISIBLE);
                    Full_Classes_ProgressBar.setVisibility(View.GONE);
                }

            }
            else
            {
                details_internetcheck.setVisibility(View.VISIBLE);
                Full_Classes_ProgressBar.setVisibility(View.GONE);
            }
        }

    }

    public void settoolbar() {
      //  Set Activity Toolbar
       Toolbar Net_College_Tool_Bar1 = (Toolbar) findViewById(R.id.app_bar);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/iransansdn.ttf");
       // TextView toolbar_title = Net_College_Tool_Bar1.findViewById(R.id.Toolbar_TxtView_details_Title);
        setSupportActionBar(Net_College_Tool_Bar1);
       getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       // toolbar_title.setTypeface(typeface);
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menu_id = item.getItemId();
        if (menu_id == R.id.action_backbtn_details_activity) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_activity_menu, menu);
        return true;
    }



}
