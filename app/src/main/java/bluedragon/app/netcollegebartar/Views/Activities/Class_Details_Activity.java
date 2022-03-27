package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;

public class Class_Details_Activity extends AppCompatActivity {

    private int ClassID;
    private int Class_Depatemants_ID;
    private String ClassName;
    private String ClassLogo;
    private String Class_Description;
    TextView class_description;
    Toolbar Net_College_Tool_Bar_Class_Details;
    TextView toolbar_Details_title;
    Typeface typeface;
    Button button_register;
    ImageView Class_Details_ImageView;
    SessionManager sessionManager;
    ApiService apiService;
    UserInformationSharedPrefManager userInformationSharedPrefManager;
    private static final String TAG = "Class_Details_Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class__details_);
        Log.i(TAG, "Class_Details_Activity");
        setviews();
        settoolbar();
        getintentdata();
        process();
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apiService.internetConnection())
                {
                    if(sessionManager.isLoggedIn())
                    {
                        UserInformationDataModel userInformationDataModel=userInformationSharedPrefManager.getRegisterationInfo();
                        if(userInformationDataModel.getCompelte_Profile()==0)
                        {
                            Toast.makeText(Class_Details_Activity.this,"کاربر گرامی برای ادامه مرحله ثبت نام باید پروفایل خود را تکمیل بفرمایید ",Toast.LENGTH_LONG).show();
                        }else
                        {
                            Intent sendClassName=new Intent(Class_Details_Activity.this,ExClassesListActivity.class);
                            sendClassName.putExtra("ClassID",ClassID);
                            sendClassName.putExtra("ClassName",ClassName);
                            startActivity(sendClassName);
                        }
                    }
                    else {
                        Toast.makeText(Class_Details_Activity.this,"برای ثبت نام می بایست در صورتی که حساب کاربری دارید ، به حساب کاربری خود وارد شوید و در غیراینصورت نیز لازم است تا در نرم افزار ثبت نام کنید",Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(Class_Details_Activity.this,"کاربر گرامی برای مشاهده لیست دوره های در حال برگزاری این کلاس باید به اینترنت متصل باشید",Toast.LENGTH_LONG).show();

                }

            }
        });





    }
    public void setviews()
    {
        button_register=(Button)findViewById(R.id.DorehListbtn);
        class_description=(TextView)findViewById(R.id.class_description);
        typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        Class_Details_ImageView=(ImageView)findViewById(R.id.Class_Details_ImageView);
        Net_College_Tool_Bar_Class_Details = (Toolbar) findViewById(R.id.NetCollege_Tool_Bar_Class_Details);
        toolbar_Details_title=Net_College_Tool_Bar_Class_Details.findViewById(R.id.Class_Details_Toolbar_TxtView_Title1);
        toolbar_Details_title.setTypeface(typeface);
        sessionManager=new SessionManager(getApplicationContext());
        userInformationSharedPrefManager=new UserInformationSharedPrefManager(getApplicationContext());
        apiService=new ApiService(getApplicationContext());

    }
    private void getintentdata()
    {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            ClassID=mBundle.getInt("ClassID");
            Class_Depatemants_ID=mBundle.getInt("Class_Depatemants_ID");
            ClassName=mBundle.getString("ClassName");
            ClassLogo=mBundle.getString("ClassLogo");
            Class_Description=mBundle.getString("Class_Description");
        }
    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(Net_College_Tool_Bar_Class_Details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    private void process()
    {
        toolbar_Details_title.setText(ClassName);
        class_description.setText(Class_Description);
        class_description.setTypeface(typeface);
        button_register.setTypeface(typeface);
        Picasso.with(getBaseContext()).load(ClassLogo).placeholder(R.drawable.netcollege_place_holder).into(Class_Details_ImageView);
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
