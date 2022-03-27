package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
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
import java.util.List;

import bluedragon.app.netcollegebartar.Adapters.Services_full_Adapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.Services_full_DataModel;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;

public class OtherServicesfullactivity extends AppCompatActivity {

    private ArrayList<Services_full_DataModel> Services_list;
    private RecyclerView full_Services_recyclerview;
    private ApiService apiService;
    private ProgressBar Other_Services_Main_ProgressBar;
    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_services);
        settoolbar();
        sqliteManager=new SqliteManager(getApplicationContext());
        full_Services_recyclerview=(RecyclerView)findViewById(R.id.full_services_recycler);
        Other_Services_Main_ProgressBar=(ProgressBar)findViewById(R.id.Other_Services_Main_ProgressBar);
        Services_list=new ArrayList<>();
        apiService=new ApiService(getApplicationContext());
        if(apiService.internetConnection())
        {
            apiService.LoadfullServices(AppConfig.URL_Services,Services_list,full_Services_recyclerview,Other_Services_Main_ProgressBar,sqliteManager);
            full_Services_recyclerview.setHasFixedSize(true);
            full_Services_recyclerview.setLayoutManager(new GridLayoutManager(OtherServicesfullactivity.this,2, LinearLayoutManager.VERTICAL,false));
        }
        else
        {
            if(sqliteManager.checkOtherMajorTBAvailbilty())
            {
                Services_full_Adapter services_full_adapter = new Services_full_Adapter(getApplicationContext(),sqliteManager.getAllOtherMajorListOffline());
                full_Services_recyclerview.setAdapter(services_full_adapter);
                Other_Services_Main_ProgressBar.setVisibility(View.INVISIBLE);
                full_Services_recyclerview.setVisibility(View.VISIBLE);
                full_Services_recyclerview.setHasFixedSize(true);
                full_Services_recyclerview.setLayoutManager(new GridLayoutManager(OtherServicesfullactivity.this,2, LinearLayoutManager.VERTICAL,false));
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent main_activity_Intent=new Intent(OtherServicesfullactivity.this,MainActivity.class);
        startActivity(main_activity_Intent);
        finish();
    }

    public void  settoolbar()
    {
        //Set Activity Toolbar
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        Toolbar Net_College_Tool_Bar_Services = (Toolbar) findViewById(R.id.NetCollege_Tool_Bar_services);
        setSupportActionBar(Net_College_Tool_Bar_Services);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TextView toolbar_title=Net_College_Tool_Bar_Services.findViewById(R.id.services_Toolbar_TxtView_Title1);
        toolbar_title.setTypeface(typeface);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_backbtn_full_services)
        {
            // Toast.makeText(Depatemants_Actvitiy.this,"clicked",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(OtherServicesfullactivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fullservicesmenu,menu);
        return true;
    }
}
