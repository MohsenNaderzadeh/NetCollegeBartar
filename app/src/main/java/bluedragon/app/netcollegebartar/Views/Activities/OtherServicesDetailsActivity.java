package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class OtherServicesDetailsActivity extends AppCompatActivity {

    private int Other_ClassID;
    private String Other_ClassName;
    private String Other_ClassLogo;
    private String Other_Class_Description;
    Toolbar NetCollege_Tool_Bar_Other_Class_Details;
    ImageView Other_Classes_Details_ImageView;
    TextView Other_Class_Details_Toolbar_TxtView_Title;
    TextView Other_class_description;
    Button Other_Classes_Register_Btn;
    UserInformationSharedPrefManager userInformationSharedPrefManager;
    SessionManager sessionManager;
    Typeface typeface;
    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_classes);
        SetViews();
        settoolbar();
        getintentdata();
        process();
        Other_Classes_Register_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apiService.internetConnection())
                {
                    if(sessionManager.isLoggedIn())
                    {
                        UserInformationDataModel userInformationDataModel=userInformationSharedPrefManager.getRegisterationInfo();
                        if(userInformationDataModel.getCompelte_Profile()==0)
                        {
                            Toast.makeText(OtherServicesDetailsActivity.this,"کاربر گرامی برای ادامه مرحله ثبت نام باید پروفایل خود را تکمیل بفرمایید ",Toast.LENGTH_LONG).show();
                        }else
                        {
                            Intent sendClassName=new Intent(OtherServicesDetailsActivity.this,RegisterFormActivity.class);
                            sendClassName.putExtra("ClassName",Other_ClassName);
                            sendClassName.putExtra("ClassID",Other_ClassID);
                            sendClassName.putExtra("ClassType",0);
                            startActivity(sendClassName);
                        }
                    }
                    else {
                        Toast.makeText(OtherServicesDetailsActivity.this,"برای ثبت نام می بایست در صورتی که حساب کاربری دارید ، به حساب کاربری خود وارد شوید و در غیراینصورت نیز لازم است تا در نرم افزار ثبت نام کنید",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(OtherServicesDetailsActivity.this,"کاربر گرامی لطفا اتصال خود به اینترنت را بررسی بفرمایید",Toast.LENGTH_LONG).show();

                }


            }
        });
    }
    private void SetViews()
    {
        NetCollege_Tool_Bar_Other_Class_Details=(Toolbar)findViewById(R.id.NetCollege_Tool_Bar_Other_Class_Details);
        Other_Classes_Details_ImageView=(ImageView)findViewById(R.id.Other_Classes_Details_ImageView);
        Other_Class_Details_Toolbar_TxtView_Title=(TextView)findViewById(R.id.Other_Class_Details_Toolbar_TxtView_Title);
        Other_class_description=(TextView)findViewById(R.id.Other_class_description);
        Other_Classes_Register_Btn=(Button)findViewById(R.id.Other_Classes_Register_Btn);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/iransansdn.ttf");
        Other_Class_Details_Toolbar_TxtView_Title.setTypeface(typeface);
        userInformationSharedPrefManager=new UserInformationSharedPrefManager(getApplicationContext());
        sessionManager=new SessionManager(getApplicationContext());
        apiService=new ApiService(getApplicationContext());
    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(NetCollege_Tool_Bar_Other_Class_Details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    private void getintentdata()
    {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            Other_ClassID=mBundle.getInt("ClassID");
            Other_ClassName=mBundle.getString("ClassName");
            Other_ClassLogo=mBundle.getString("ClassLogo");
            Other_Class_Description=mBundle.getString("Class_Description");
        }
    }
    private void process()
    {
        Other_Class_Details_Toolbar_TxtView_Title.setText(Other_ClassName);
        Other_class_description.setText(Other_Class_Description);
        Other_class_description.setTypeface(typeface);
        Other_Classes_Register_Btn.setTypeface(typeface);
        Picasso.with(getBaseContext()).load(Other_ClassLogo).placeholder(R.drawable.netcollege_place_holder).into(Other_Classes_Details_ImageView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.other_services_details_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_backbtn_other_Class_details_activity)
        {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
