package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;
import bluedragon.app.netcollegebartar.Views.Fragments.LoginFragment;
import bluedragon.app.netcollegebartar.Views.Fragments.MainFragment;
import bluedragon.app.netcollegebartar.Views.Fragments.UserPanelFragment;
import co.ronash.pushe.Pushe;
import de.hdodenhof.circleimageview.CircleImageView;
import ir.basalam.rtlnavigationview.RtlNavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout1;
   // NavigationView navigationView1;
    static  Typeface Iraniansans_font;
    Menu m;
    RtlNavigationView rtlNavigationView;
    public static FrameLayout fracontainer;
    SessionManager sessionManager;
    ApiService apiService;
    SqliteManager sqliteManager;
    private static final String TAG = "MainActivity";
   public static CircleImageView imageView;
    public static TextView Username_txv;
    public static TextView  Email_txv;
    UserInformationSharedPrefManager userInformationSharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        drawerLayout1 = (DrawerLayout) findViewById(R.id.Navigation_drawer_layout1);
        rtlNavigationView = (RtlNavigationView) findViewById(R.id.nav_view);
        userInformationSharedPrefManager=new UserInformationSharedPrefManager(getApplicationContext());
        fracontainer=(FrameLayout)findViewById(R.id.fracontainer);
        settoolbar();
        apiService=new ApiService(getApplicationContext());
        sqliteManager=new SqliteManager(getApplicationContext());
       // setnavifont();
      //  setNavigationTypeface();
        Pushe.initialize(this,true);
        final FragmentManager fragmentManager=getSupportFragmentManager();
        final MainFragment mainFragment =new MainFragment();
        final LoginFragment loginFragment =new LoginFragment();
        final UserPanelFragment userPanelFragment=new UserPanelFragment();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fracontainer, mainFragment);
        fragmentTransaction.commit();


        AppBarLayout appBarLayout=(AppBarLayout)findViewById(R.id.NetCollege_Tool_Bar_appbar);
        Toolbar Net_College_Tool_Bar11 =(Toolbar) appBarLayout.findViewById(R.id.NetCollege_Tool_Bar1);

        View view=Net_College_Tool_Bar11.findViewById(R.id.action_navigation);
        sessionManager=new SessionManager(getApplicationContext());




        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id)
                {
                    case R.id.item_Home:

                        FragmentTransaction fragmentTransaction1=fragmentManager.beginTransaction();
                        fragmentTransaction1.replace(R.id.fracontainer, mainFragment);
                        fragmentTransaction1.commit();
                        break;
                    case R.id.item_account:
                        if(sessionManager.isLoggedIn())
                        {
                            FragmentTransaction fragmentTransaction2=fragmentManager.beginTransaction();
                            fragmentTransaction2.replace(R.id.fracontainer, userPanelFragment);
                            fragmentTransaction2.commit();
                        }
                        else
                        {
                            FragmentTransaction fragmentTransaction2=fragmentManager.beginTransaction();
                            fragmentTransaction2.replace(R.id.fracontainer, loginFragment);
                            fragmentTransaction2.commit();
                        }
                        break;

                }



                return true;
            }
        });

        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        rtlNavigationView.setNavigationItemSelectedListener(this);
        rtlNavigationView.setTypeface(typeface);
        Bottom_Navigation_Set_Type_Face(getApplicationContext(),bottomNav);

        View hView =  rtlNavigationView.getHeaderView(0);
        imageView=hView.findViewById(R.id.user_profile_img_photo);
        Username_txv=hView.findViewById(R.id.Username_txv);
        Email_txv=hView.findViewById(R.id.Email_txv);
        Username_txv.setTypeface(typeface);
        if(sessionManager.isLoggedIn())
        {
            UserInformationDataModel userInformationDataModel=userInformationSharedPrefManager.getRegisterationInfo() ;
            if(userInformationDataModel.getCompelte_Profile()==0)
            {
                imageView.setImageResource(R.drawable.default_avatar);
                Username_txv.setText("کاربر عضو");
                Email_txv.setText("example@example.com");
            }
            else
            {
                Picasso.with(getApplicationContext()).load(userInformationDataModel.getUser_Profile()).into(imageView);
                Username_txv.setText(userInformationDataModel.getUser_First_Name()+" "+userInformationDataModel.getUser_User_Last_Name());
                Email_txv.setText(userInformationDataModel.getUser_Email_Address());
            }



        }


























    }
    
    public void  settoolbar()
    {
        //Set Activity Toolbar
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        Toolbar Net_College_Tool_Bar1 = (Toolbar) findViewById(R.id.NetCollege_Tool_Bar1);
        setSupportActionBar(Net_College_Tool_Bar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TextView toolbar_title=Net_College_Tool_Bar1.findViewById(R.id.Toolbar_TxtView_Title1);
        toolbar_title.setTypeface(typeface);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_navigation)
        {





            drawerLayout1.openDrawer(Gravity.RIGHT);

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        drawerLayout1 = (DrawerLayout) findViewById(R.id.Navigation_drawer_layout1);
        if (drawerLayout1.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout1.closeDrawer(Gravity.RIGHT);
        }else
        {
            MainActivity.this.finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }










    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.Last_News:
                if(apiService.internetConnection())
                {
                    Intent intent=new Intent(MainActivity.this,LastNewsActivity.class);
                    startActivity(intent);
                }else
                {
                    if(sqliteManager.checkNewsTBAvailbilty())
                    {
                        Intent intent=new Intent(MainActivity.this,LastNewsActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"لطفا اتصال خود به شبکه اینترنت را بررسی کنید ",Toast.LENGTH_LONG).show();
                    }
                }


                break;
            case R.id.Library:
                Intent intent1=new Intent(MainActivity.this,LibraryAcitiviy.class);
                startActivity(intent1);
                break;
            case R.id.image_gallery:
                if(apiService.internetConnection())
                {
                    Intent intent2=new Intent(MainActivity.this,ImageGalleryActivity.class);
                    startActivity(intent2);
                }
                else
                {
                    if(sqliteManager.checkImageGalleryTBAvailbilty())
                    {
                        Intent intent2=new Intent(MainActivity.this,ImageGalleryActivity.class);
                        startActivity(intent2);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"لطفا اتصال خود به شبکه اینترنت را بررسی کنید ",Toast.LENGTH_LONG).show();

                    }
                }

                break;
            case R.id.about_us:
                Intent intent3=new Intent(MainActivity.this,AboutUsActivity.class);
                startActivity(intent3);
                break;
            case  R.id.Contact_with_Us:
                Intent intent4=new Intent(MainActivity.this,ContactUsActivity.class);
                startActivity(intent4);
                break;


            case R.id.Exit:
                finish();
                break;
        }

        return true;
    }
    public static void Bottom_Navigation_Set_Type_Face(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    Bottom_Navigation_Set_Type_Face(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/iransansdn.ttf"));
            }
        } catch (Exception e) {
        }
    }



}
