package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.Adapters.Teacher_Profile_View_Pager_Adapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.Camps;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.Views.Fragments.Fragment_Teacher_Profile;

public class Teacher_Profile_Acitivity extends AppCompatActivity {

    private int I_Teacher_ID;
    private String I_Teachers_full_name;
    private String I_Teachers_photo_Url;
    private String I_Teachers_Skill;
    private String I_Teachers_Resume;
    private String I_Teacher_Email;
    private int I_Teachers_Departemant_ID;
    TabLayout Teacher_Profile_Tab_Layout;
    Fragment_Teacher_Profile fragment_teacher_profile;
    ApiService apiService;

    public static ArrayList<Camps> camplist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__profile__acitivity);
        Toolbar Teacher_Profile_Acitivty_ToolBar = (Toolbar) findViewById(R.id.app_bar);
        ImageView Teacher_Photo_Image_View=(ImageView)findViewById(R.id.Teacher_Photo);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout_teacher_activity);
        setSupportActionBar(Teacher_Profile_Acitivty_ToolBar);
        getintentdata();
        apiService=new ApiService(getApplicationContext());
        Bundle teacherprofilebundle=new Bundle();
        teacherprofilebundle.putInt("Teacher_ID",I_Teacher_ID);
        teacherprofilebundle.putString("Teachers_Skill",I_Teachers_Skill);
        teacherprofilebundle.putString("Teachers_Resume",I_Teachers_Resume);
        teacherprofilebundle.putInt("Teachers_Departemant_ID",I_Teachers_Departemant_ID);
        teacherprofilebundle.putString("Teacher_Email",I_Teacher_Email);
        teacherprofilebundle.putString("Teacher_Name",I_Teachers_full_name);





        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/iransansdn.ttf");
        collapsingToolbarLayout.setCollapsedTitleTypeface(typeface);
        collapsingToolbarLayout.setExpandedTitleTypeface(typeface);

        collapsingToolbarLayout.setTitle(I_Teachers_full_name);
        Picasso.with(getBaseContext()).load(I_Teachers_photo_Url).into(Teacher_Photo_Image_View);
        Teacher_Profile_Tab_Layout=(TabLayout)findViewById(R.id.Teacher_Profile_Tab_Layout);
        ViewPager Teacher_Profile_View_Pager=(ViewPager)findViewById(R.id.Teacher_Profile_View_Pager);
        Teacher_Profile_View_Pager_Adapter teacher_profile_view_pager_adapter=new Teacher_Profile_View_Pager_Adapter(getSupportFragmentManager(),teacherprofilebundle);
        Teacher_Profile_View_Pager.setAdapter(teacher_profile_view_pager_adapter);
        Teacher_Profile_Tab_Layout.setupWithViewPager(Teacher_Profile_View_Pager);
        changeTabsFont();
        TabLayout.Tab tab = Teacher_Profile_Tab_Layout.getTabAt(1);
        tab.select();





    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menu_id = item.getItemId();
        if (menu_id == R.id.action_backbtn_teachers_profile_activity) {
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
        getMenuInflater().inflate(R.menu.teachers_profile_menu, menu);
        return true;
    }
    private void getintentdata()
    {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            I_Teacher_ID=mBundle.getInt("Teacher_ID");
            I_Teachers_full_name=mBundle.getString("Teachers_full_name");
            I_Teachers_photo_Url=mBundle.getString("Teachers_photo");
            I_Teachers_Skill=mBundle.getString("Teachers_Skill");
            I_Teachers_Resume=mBundle.getString("Teachers_Resume");
            I_Teacher_Email=mBundle.getString("Teacher_Email");
            I_Teachers_Departemant_ID=mBundle.getInt("Teachers_Departemant_ID");
        }
    }
    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup)  Teacher_Profile_Tab_Layout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    AssetManager mgr =getAssets();
                    Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/iransansdn.ttf");
                    ((TextView) tabViewChild).setTypeface(typeface);
                }
            }
        }
    }


}
