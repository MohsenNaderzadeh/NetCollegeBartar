package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.Intent;
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

import bluedragon.app.netcollegebartar.Adapters.Departemants_full_Adapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.Departemants;
import bluedragon.app.netcollegebartar.DataModels.Departemants_full_DataModel;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;

public class DepartemantsActivity extends AppCompatActivity {

    private ApiService apiService;
    private RecyclerView Departemants_recyclerView;
    private ProgressBar Departemants_ProgressBar;
    ArrayList<Departemants_full_DataModel> Departemants_List;
    SqliteManager sqliteManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departemants);
        settoolbar();
        apiService=new ApiService(DepartemantsActivity.this);
        Departemants_recyclerView =(RecyclerView) findViewById(R.id.full_departemants_recycler);
        Departemants_ProgressBar=(ProgressBar)findViewById(R.id.Departemants_ProgressBar);
        Departemants_recyclerView.setHasFixedSize(true);
        Departemants_recyclerView.setLayoutManager(new GridLayoutManager(DepartemantsActivity.this,2, LinearLayoutManager.VERTICAL,false));
        Departemants_List=new ArrayList<>();
        sqliteManager=new SqliteManager(getApplicationContext());
        if(apiService.internetConnection())
        {
            apiService.loadfullDepartemants(AppConfig.URL_Departemants,Departemants_List,Departemants_recyclerView,Departemants_ProgressBar,sqliteManager);

        }
        else
        {
            if(sqliteManager.checkDepatemantsTBAvailbilty())
            {
                Departemants_full_Adapter adapter = new Departemants_full_Adapter(getApplicationContext(), sqliteManager.getDepartemantsListForFUllPageOffline());
                Departemants_recyclerView.setAdapter(adapter);
                Departemants_ProgressBar.setVisibility(View.INVISIBLE);
                Departemants_recyclerView.setVisibility(View.VISIBLE);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.departemants_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_backbtn)
        {
            // Toast.makeText(Depatemants_Actvitiy.this,"clicked",Toast.LENGTH_LONG).show();
            Intent main_activity_Intent=new Intent(DepartemantsActivity.this,MainActivity.class);
            startActivity(main_activity_Intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        Toolbar Net_College_Tool_Bar_Departemants = (Toolbar) findViewById(R.id.NetCollege_Tool_Bar_Departemants);
        setSupportActionBar(Net_College_Tool_Bar_Departemants);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TextView toolbar_title=Net_College_Tool_Bar_Departemants.findViewById(R.id.Departemants_Toolbar_TxtView_Title1);
        toolbar_title.setTypeface(typeface);

    }
    @Override
    public void onBackPressed() {

        Intent main_activity_Intent=new Intent(DepartemantsActivity.this,MainActivity.class);
        startActivity(main_activity_Intent);
        finish();
    }

}
