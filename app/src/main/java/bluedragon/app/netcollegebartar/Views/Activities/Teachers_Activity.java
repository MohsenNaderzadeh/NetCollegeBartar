package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bluedragon.app.netcollegebartar.Adapters.Teachers_full_recyclerview_Adapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.Teachers_full_DataModel;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;

public class Teachers_Activity extends AppCompatActivity {


    ArrayList<Teachers_full_DataModel> Teachers_list;
    RecyclerView Teachers_recyclerview;
    Teachers_full_recyclerview_Adapter recyclerview_details;
    private ProgressBar teachers_ProgressBar;
    private ApiService apiService;
    private ProgressBar teachers_progressbar_LoadMore;
    SqliteManager sqliteManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_);
        settoolbar();
        apiService=new ApiService(Teachers_Activity.this);
        sqliteManager=new SqliteManager(getApplicationContext());
        teachers_ProgressBar=(ProgressBar) findViewById(R.id.teachers_progressbar);
        Teachers_recyclerview=(RecyclerView) findViewById(R.id.full_teachers_recycler);
        teachers_progressbar_LoadMore=(ProgressBar) findViewById(R.id.teachers_progressbar_LoadMore);
        Teachers_recyclerview.setHasFixedSize(true);
        Teachers_recyclerview.setLayoutManager(new GridLayoutManager(Teachers_Activity.this,2,LinearLayoutManager.VERTICAL,false));
        Teachers_list=new ArrayList<>();

        if(apiService.internetConnection())
        {
            recyclerview_details=new Teachers_full_recyclerview_Adapter(Teachers_Activity.this,Teachers_list);
            Teachers_recyclerview.setAdapter(recyclerview_details);
            apiService.getFullTeacher_DataFirstLoad(Teachers_list,recyclerview_details,teachers_ProgressBar,Teachers_recyclerview,sqliteManager);
        }else
        {
            if(sqliteManager.checkTeachersTBAvailbilty())
            {
                recyclerview_details=new Teachers_full_recyclerview_Adapter(Teachers_Activity.this,sqliteManager.getAllTeachersList());
                Teachers_recyclerview.setAdapter(recyclerview_details);
                teachers_ProgressBar.setVisibility(View.GONE);
                Teachers_recyclerview.setVisibility(View.VISIBLE);
            }
        }





        Teachers_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLastItemDisplaying(recyclerView)) {
                    if(apiService.internetConnection())
                    {
                        //Calling the method getdata again
                        teachers_progressbar_LoadMore.setVisibility(View.VISIBLE);
                        apiService.getFullTeachersSecondPartData(Teachers_list,recyclerview_details,teachers_progressbar_LoadMore,sqliteManager);
                    }

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.teachers_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_backbtn_t)
        {

            Intent intent=new Intent(Teachers_Activity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        Toolbar Net_College_Tool_Bar_Departemants = (Toolbar) findViewById(R.id.NetCollege_Tool_Bar_teachers);
        setSupportActionBar(Net_College_Tool_Bar_Departemants);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TextView toolbar_title=Net_College_Tool_Bar_Departemants.findViewById(R.id.teachers_Toolbar_TxtView_Title1);
        toolbar_title.setTypeface(typeface);

    }
    @Override
    public void onBackPressed() {

        Intent intent=new Intent(Teachers_Activity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }





    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }


    }


