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

import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.Adapters.ExListRecyclerViewAdapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.ExClassListModel;
import bluedragon.app.netcollegebartar.R;

public class ExClassesListActivity extends AppCompatActivity implements ApiService.OnExClassListByClassIDRecieved
{

    int ClassID;
    String ClassName;
    Toolbar Net_College_Tool_Bar_Class_Details;
    TextView Ex_Class_Details_Toolbar_TxtView_Title,ExClassMessagetext;
    ProgressBar Progressbar_ex_class_list;
    Typeface typeface;
    RecyclerView recyclerView;
    ExListRecyclerViewAdapter recyclerViewAdapter;
    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_classes_list);
        Setviews();
        settoolbar();
        getintentdata();
        Ex_Class_Details_Toolbar_TxtView_Title.setText(ClassName);
        apiService.GetExClassListByClassID(AppConfig.URL_GET_EX_CLASSES_LIST_BY_CLASS_ID,this,ClassID);

    }
    private void Setviews()
    {
        typeface= Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        Net_College_Tool_Bar_Class_Details = (Toolbar) findViewById(R.id.NetCollege_Tool_Bar_Ex_Class_List);
        Ex_Class_Details_Toolbar_TxtView_Title=(TextView)findViewById(R.id.Ex_Class_Details_Toolbar_TxtView_Title);
        Ex_Class_Details_Toolbar_TxtView_Title=Net_College_Tool_Bar_Class_Details.findViewById(R.id.Ex_Class_Details_Toolbar_TxtView_Title);
        Progressbar_ex_class_list=(ProgressBar)findViewById(R.id.Progressbar_ex_class_list);
        ExClassMessagetext=(TextView)findViewById(R.id.ExClassMessagetext);
        Ex_Class_Details_Toolbar_TxtView_Title.setTypeface(typeface);
        ExClassMessagetext.setTypeface(typeface);
        recyclerView=findViewById(R.id.ExClassActivityList);
        apiService=new ApiService(getApplicationContext());
    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(Net_College_Tool_Bar_Class_Details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    private void getintentdata()
    {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            ClassID=mBundle.getInt("ClassID");
            ClassName=mBundle.getString("ClassName");
        }
    }

    @Override
    public void ExClassListByClassIDRecieved(ArrayList<ExClassListModel> ExClassList) {

            Progressbar_ex_class_list.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(ExClassesListActivity.this, LinearLayoutManager.VERTICAL,false));
            recyclerViewAdapter=new ExListRecyclerViewAdapter(ExClassesListActivity.this,ExClassList);
            recyclerView.setAdapter(recyclerViewAdapter);




    }

    @Override
    public void ExClassListByClassIDRecievedhasError() {
        Progressbar_ex_class_list.setVisibility(View.GONE);
        ExClassMessagetext.setVisibility(View.VISIBLE);
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
}
