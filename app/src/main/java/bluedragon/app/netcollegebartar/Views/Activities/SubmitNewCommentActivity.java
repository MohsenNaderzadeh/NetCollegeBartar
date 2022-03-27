package bluedragon.app.netcollegebartar.Views.Activities;

import android.graphics.Typeface;
import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;

public class SubmitNewCommentActivity extends AppCompatActivity implements ApiService.OnSubmitNewCommentResult {

    int Teacher_ID;
    String Teacher_Name;
    Toolbar NetCollege_Tool_Bar_Submit_Comment;
    TextView ToolBartitletxt,user_commenter_first_name_txt,user_commenter_last_name_txt,teacher_commenter_name,teacher_ratingbartxt,comment_text_txt;
    EditText user_commenter_first_name_ed,user_commenter_last_name,teacher_commenter_name_ed,comment_text;
    Typeface typeface;
    RatingBar teacher_ratingbar;
    ApiService apiService;
    Button Submit_new_information_for_user_profile;
    UserInformationSharedPrefManager userInformationSharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment_submit);
        setViews();
        getBundleData();
        settoolbar();
        ToolBartitletxt.setText(Teacher_Name);
        final UserInformationDataModel userInformationDataModel=userInformationSharedPrefManager.getRegisterationInfo();
        user_commenter_first_name_ed.setText(userInformationDataModel.getUser_First_Name());
        user_commenter_last_name.setText(userInformationDataModel.getUser_User_Last_Name());
        teacher_commenter_name_ed.setText(Teacher_Name);


        Submit_new_information_for_user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!comment_text.getText().toString().isEmpty())
                {
                    apiService.SubmitNewCommentForTeacher(AppConfig.URL_SUBMIT_NEW_COMMENT,SubmitNewCommentActivity.this,userInformationDataModel.getUser_ID(),comment_text.getText().toString(),Teacher_ID,(float) teacher_ratingbar.getRating());

                }else {
                    Toast.makeText(SubmitNewCommentActivity.this,"لطفا متن نظر خود را بنویسید",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void setViews()
    {
        typeface= Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");

        NetCollege_Tool_Bar_Submit_Comment=(Toolbar)findViewById(R.id.NetCollege_Tool_Bar_Submit_Comment);

        ToolBartitletxt=(TextView) NetCollege_Tool_Bar_Submit_Comment.findViewById(R.id.SubmitComment_Toolbar_TxtView_Title1);
        user_commenter_first_name_txt=(TextView)findViewById(R.id.user_commenter_first_name_txt);
        user_commenter_last_name_txt=(TextView)findViewById(R.id.user_commenter_last_name_txt);
        teacher_commenter_name=(TextView)findViewById(R.id.teacher_commenter_name);
        teacher_ratingbartxt=(TextView)findViewById(R.id.teacher_ratingbartxt);
        comment_text_txt=(TextView)findViewById(R.id.comment_text_txt);


        user_commenter_first_name_ed=(EditText)findViewById(R.id.user_commenter_first_name_ed);
        user_commenter_last_name=(EditText)findViewById(R.id.user_commenter_last_name);
        teacher_commenter_name_ed=(EditText)findViewById(R.id.teacher_commenter_name_ed);
        comment_text=(EditText)findViewById(R.id.comment_text);

        teacher_ratingbar=(RatingBar)findViewById(R.id.teacher_ratingbar);

        Submit_new_information_for_user_profile=(Button)findViewById(R.id.Submit_new_information_for_user_profile);

        userInformationSharedPrefManager=new UserInformationSharedPrefManager(getApplicationContext());

        apiService=new ApiService(getApplicationContext());

        ToolBartitletxt.setTypeface(typeface);
        user_commenter_first_name_txt.setTypeface(typeface);
        user_commenter_last_name_txt.setTypeface(typeface);
        teacher_commenter_name.setTypeface(typeface);
        teacher_ratingbartxt.setTypeface(typeface);
        comment_text_txt.setTypeface(typeface);
        ToolBartitletxt.setText(Teacher_Name);
        user_commenter_first_name_ed.setTypeface(typeface);
        user_commenter_last_name.setTypeface(typeface);
        teacher_commenter_name_ed.setTypeface(typeface);
        comment_text.setTypeface(typeface);
        Submit_new_information_for_user_profile.setTypeface(typeface);
    }
    private void getBundleData()
    {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            Teacher_ID=mBundle.getInt("TeacherID");
            Teacher_Name=mBundle.getString("Teacher_Name");
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
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(NetCollege_Tool_Bar_Submit_Comment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void OnSubmitNewCommentResultRecieved(Boolean hasError) {
        if(!hasError)
        {
            Toast.makeText(getApplicationContext(),"نظر شما با موفقیت ثبت گردید و به زودی پس از تایید ، نمایش داده خواهد شد",Toast.LENGTH_LONG).show();
        }
    }
}
