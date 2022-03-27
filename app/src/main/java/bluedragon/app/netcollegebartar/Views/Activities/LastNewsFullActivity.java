package bluedragon.app.netcollegebartar.Views.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bluedragon.app.netcollegebartar.R;

public class LastNewsFullActivity extends AppCompatActivity {

    Toolbar LastNewsfull_Tool_Bar;
    ImageView LastfullNewsLogo;
    TextView Last_News_full_Toolbar_TxtView_Title1,Last_fullNews_Title,Last_fullNews_Main_Text,News_fullDate_Title,News_fullDate_Value;
    int LastNews_ID;
    String LastNewsTitle,LastNewsMainText,LastNewsDate,LastNews_Logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_news_full);
        setViews();
        settoolbar();
        getintentdata();
        Picasso.with(getApplicationContext()).load(LastNews_Logo).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(LastfullNewsLogo);
        Last_News_full_Toolbar_TxtView_Title1.setText(LastNewsTitle);
        Last_fullNews_Title.setText(LastNewsTitle);
        Last_fullNews_Main_Text.setText(LastNewsMainText);
        News_fullDate_Value.setText(LastNewsDate);
    }

    private void setViews()
    {
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        LastNewsfull_Tool_Bar=(Toolbar)findViewById(R.id.LastNewsfull_Tool_Bar);
        LastfullNewsLogo=(ImageView)findViewById(R.id.LastfullNewsLogo);
        Last_News_full_Toolbar_TxtView_Title1=(TextView) LastNewsfull_Tool_Bar.findViewById(R.id.Last_News_full_Toolbar_TxtView_Title1);
        Last_fullNews_Title=(TextView)findViewById(R.id.Last_fullNews_Title);
        Last_fullNews_Main_Text=(TextView)findViewById(R.id.Last_fullNews_Main_Text);
        News_fullDate_Title=(TextView)findViewById(R.id.News_fullDate_Title);
        News_fullDate_Value=(TextView)findViewById(R.id.News_fullDate_Value);

        Last_fullNews_Title.setTypeface(typeface);
        Last_fullNews_Main_Text.setTypeface(typeface);
        News_fullDate_Title.setTypeface(typeface);
        News_fullDate_Value.setTypeface(typeface);
        Last_News_full_Toolbar_TxtView_Title1.setTypeface(typeface);

    }

    private void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(LastNewsfull_Tool_Bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    private void getintentdata()
    {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            LastNews_ID=mBundle.getInt("LastNews_ID");
            LastNewsTitle=mBundle.getString("LastNewsTitle");
            LastNewsMainText=mBundle.getString("LastNewsMainText");
            LastNewsDate=mBundle.getString("LastNewsDate");
            LastNews_Logo=mBundle.getString("LastNews_Logo");
        }
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
