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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.ExClassListModel;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;

public class RegisterFormActivity extends AppCompatActivity implements ApiService.OnOtherMajorRegisterResultRecieved,ApiService.OnExClassRegisterResult{

    Toolbar NetCollege_Tool_Bar_Class_Register;
    Typeface typeface;
    TextView toolbar_Register_title;
    Button Class_Registeration_Form;
    UserInformationSharedPrefManager userInformationSharedPrefManager;
    ApiService apiService;
    String ClassName;
    int ClassType;
    int ClassID;
    UserInformationDataModel userInformationDataModel;
    TextView register_user_first_name_txt,register_user_last_name_txt,register_class_name_txt,Register_user_email_url_txt,Register_user_phone_number_txt;
    EditText register_user_first_name,Register_user_last_name,register_classname,Register_user_email_url,Register_user_phone_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        setviews();
        settoolbar();
        getintentdata();
         userInformationDataModel=userInformationSharedPrefManager.getRegisterationInfo();
        toolbar_Register_title.setText(ClassName);
        register_user_first_name.setText(userInformationDataModel.getUser_First_Name());
        Register_user_last_name.setText(userInformationDataModel.getUser_User_Last_Name());
        register_classname.setText(ClassName);
        Register_user_email_url.setText(userInformationDataModel.getUser_Email_Address());
        Register_user_phone_number.setText(userInformationDataModel.getUser_Phone_Number());
        Class_Registeration_Form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClassType==0)
                {
                    apiService.UserOtherMajorRegister(AppConfig.URL_OTHER_MAJOR_REGISTER,RegisterFormActivity.this,userInformationDataModel.getUser_ID(),ClassID);

                }else if(ClassType==1){
                    apiService.UserExClassRegisterApi(AppConfig.URL_REGISTER_EX_CLASS,RegisterFormActivity.this,userInformationDataModel.getUser_ID(),ClassID);

                }
            }
        });
    }
    public void setviews()
    {
        typeface= Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        apiService=new ApiService(getApplicationContext());
        NetCollege_Tool_Bar_Class_Register = (Toolbar) findViewById(R.id.NetCollege_Tool_Bar_Class_Register);
        toolbar_Register_title=NetCollege_Tool_Bar_Class_Register.findViewById(R.id.Register_Class_Name);
        toolbar_Register_title.setTypeface(typeface);
        register_user_first_name_txt=(TextView)findViewById(R.id.register_user_first_name_txt);
        register_user_last_name_txt=(TextView)findViewById(R.id.register_user_last_name_txt);
        register_class_name_txt=(TextView)findViewById(R.id.register_class_name_txt);
        Register_user_email_url_txt=(TextView)findViewById(R.id.Register_user_email_url_txt);
        Register_user_phone_number_txt=(TextView)findViewById(R.id.Register_user_phone_number_txt);
        register_user_first_name=(EditText)findViewById(R.id.register_user_first_name);
        Register_user_last_name=(EditText)findViewById(R.id.Register_user_last_name);
        register_classname=(EditText)findViewById(R.id.register_classname);
        Register_user_email_url=(EditText)findViewById(R.id.Register_user_email_url);
        Register_user_phone_number=(EditText)findViewById(R.id.Register_user_phone_number);
        Class_Registeration_Form=(Button)findViewById(R.id.Class_Registeration_Form);


        register_user_first_name_txt.setTypeface(typeface);
        register_user_last_name_txt.setTypeface(typeface);
        register_class_name_txt.setTypeface(typeface);
        Register_user_email_url_txt.setTypeface(typeface);
        Register_user_phone_number_txt.setTypeface(typeface);
        register_user_first_name.setTypeface(typeface);
        Register_user_last_name.setTypeface(typeface);
        register_classname.setTypeface(typeface);
        Register_user_email_url.setTypeface(typeface);
        Register_user_phone_number.setTypeface(typeface);
        Class_Registeration_Form.setTypeface(typeface);

        userInformationSharedPrefManager=new UserInformationSharedPrefManager(getApplicationContext());
    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(NetCollege_Tool_Bar_Class_Register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    private void getintentdata()
    {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            ClassName=mBundle.getString("ClassName");
            ClassID=mBundle.getInt("ClassID");
            ClassType=mBundle.getInt("ClassType");
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

    @Override
    public void OnOtherMajorResultRecieved(boolean HasError) {
       if(!HasError)
       {
           Toast.makeText(RegisterFormActivity.this,"ثبت نام موفقیت آمیز",Toast.LENGTH_LONG).show();
           onBackPressed();
       }
        else
       {
           Toast.makeText(RegisterFormActivity.this,"شما قبلا در این دوره ثبت نام کرده اید و به زودی با شما تماس گرفته خواهد شد",Toast.LENGTH_LONG).show();
           onBackPressed();
       }

    }


    @Override
    public void OnExClassRegisterResultRecieved(String ErrorText) {
        if(ErrorText.equals("This Register already exist."))
        {
            Toast.makeText(getApplicationContext(),"شما قبلا در این دوره ثبت نام کرده اید و به زودی با شما تماس گرفته خواهد شد",Toast.LENGTH_LONG).show();
            onBackPressed();
        }else if(ErrorText.equals("No_Problem"))
        {
            Toast.makeText(getApplicationContext(),"ثبت نام موفقیت آمیز",Toast.LENGTH_LONG).show();
            RegisterFormActivity.this.finish();
            onBackPressed();

        }
    }

}
