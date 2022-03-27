package bluedragon.app.netcollegebartar.Views.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;

public class AboutUsActivity extends AppCompatActivity {

    Toolbar AboutUsToolbar;
    TextView About_Us_TxtView_Title1,Last_fullNews_Title;
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setviews();
        settoolbar();
    }
    public void setviews()
    {
        AboutUsToolbar=(Toolbar)findViewById(R.id.About_Us_Tool_Bar) ;
        About_Us_TxtView_Title1=AboutUsToolbar.findViewById(R.id.About_Us_TxtView_Title1);
        Last_fullNews_Title=(TextView)findViewById(R.id.Last_fullNews_Title);
        typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        About_Us_TxtView_Title1.setTypeface(typeface);
        Last_fullNews_Title.setTypeface(typeface);


    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(AboutUsToolbar);
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
}
