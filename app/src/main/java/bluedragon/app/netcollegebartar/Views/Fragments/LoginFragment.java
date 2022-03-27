package bluedragon.app.netcollegebartar.Views.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ProviderInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.squareup.picasso.Picasso;

import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.Login;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserLoginInfoDataModel;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserLoginInfoSharedPrefManager;
import bluedragon.app.netcollegebartar.Views.Activities.MainActivity;

/**
 * Created by Blue_Dragon on 11/29/2018.
 */
public class LoginFragment extends Fragment implements ApiService.OnLoginData {
    private View view;
    private EditText UserName_EditText,Password_EditText;
    private ImageButton Show_Password_Txt;
    private  Typeface typeface;
    private CheckBox Password_User_Session_Save;
    private TextView Account_Question,Account_Creation_Text_view;
    private Button Login_btn;
    private FragmentActivity myContext;
    private ApiService apiService;
    UserLoginInfoSharedPrefManager userLoginInfoSharedPrefManager;
    private UserInformationSharedPrefManager userInformationSharedPrefManager;
    private SessionManager LoginSession;
    private ProgressDialog progressBar;
    private static final String TAG = "LoginFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_login,container,false);
        SetViews();
        SetTypeFace();
        Password_EditText_Click();

        Show_Password_Txt_On_Click();

        UserLoginInfoDataModel getuserLoginInfoDataModel=userLoginInfoSharedPrefManager.getUserLoginInfo();
        if(getuserLoginInfoDataModel!=null)
        {

            UserName_EditText.setText(getuserLoginInfoDataModel.getUserLoginUserName());
            Password_EditText.setText(getuserLoginInfoDataModel.getUserLoginPassWord());
            Password_User_Session_Save.setChecked(true);
        }



        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apiService.internetConnection())
                {
                    if((UserName_EditText.getText().length()!=0)&&(Password_EditText.getText().length()!=0)) {
                        if (Password_User_Session_Save.isChecked()) {
                            UserLoginInfoDataModel userLoginInfoDataModel = new UserLoginInfoDataModel();
                            userLoginInfoDataModel.setUserLoginUserName(UserName_EditText.getText().toString());
                            userLoginInfoDataModel.setUserLoginPassWord(Password_EditText.getText().toString());
                            userLoginInfoSharedPrefManager.SetUserLoginInfo(userLoginInfoDataModel);
                        }
                        progressBar.setMessage("لطفا کمی صبر نمایید ...");
                        progressBar.setCancelable(false);
                        progressBar.show();
                        apiService.LoginAPI(AppConfig.URL_LOGIN,LoginFragment.this, String.valueOf(UserName_EditText.getText()), String.valueOf(Password_EditText.getText()),progressBar);



                    }
                    else
                    {
                        Toast.makeText(getContext(),"فیلدها را پر کنید",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getContext(),"کاربر گرامی لطفا اتصال خود را به شبکه اینترنت بررسی بفرمایید",Toast.LENGTH_LONG).show();
                }

            }
        });
        Account_Creation_Text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                     FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fracontainer, new RegisterFragment());
                   fragmentTransaction.commit();
            }
        });

        return view;

    }
    private void SetViews()
    {
        UserName_EditText=(EditText) view.findViewById(R.id.UserName_EditText);
        Password_EditText=(EditText)view.findViewById(R.id.Password_EditText);
        Show_Password_Txt=(ImageButton)view.findViewById(R.id.Show_Password_Txt);
        typeface = Typeface.createFromAsset(myContext.getAssets(), "fonts/iransansdn.ttf");
        Password_EditText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        Password_User_Session_Save=(CheckBox)view.findViewById(R.id.Password_User_Session_Save);
        Account_Question=(TextView)view.findViewById(R.id.Account_Question);
        Account_Creation_Text_view=(TextView)view.findViewById(R.id.Account_Creation_Text_view);
        Login_btn=(Button)view.findViewById(R.id.Login_btn);
        apiService=new ApiService(myContext);
        userLoginInfoSharedPrefManager= new UserLoginInfoSharedPrefManager(myContext);
        LoginSession=new SessionManager(myContext);
        progressBar=new ProgressDialog(myContext);
        userInformationSharedPrefManager=new UserInformationSharedPrefManager(myContext);
    }
    private void SetTypeFace()
    {
        UserName_EditText.setTypeface(typeface);
        Password_EditText.setTypeface(typeface);
        Password_User_Session_Save.setTypeface(typeface);
        Account_Question.setTypeface(typeface);
        Account_Creation_Text_view.setTypeface(typeface);
        Login_btn.setTypeface(typeface);
    }

    private void Show_Password_Txt_On_Click()
    {
        Show_Password_Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(Show_Password_Txt.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_eye_off).getConstantState()))
               {
                   Show_Password_Txt.setImageResource(R.drawable.ic_eye);
                   Password_EditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                   Password_EditText.setSelection(Password_EditText.length());
                   Password_EditText.setTypeface(typeface);
               }
                else
               {
                   Show_Password_Txt.setImageResource(R.drawable.ic_eye_off);
                   Password_EditText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                   Password_EditText.setSelection(Password_EditText.length());
                   Password_EditText.setTypeface(typeface);
               }
            }
        });
    }
    private void Password_EditText_Click()
    {
        Password_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Password_EditText.setTypeface(typeface);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public void onRecieved(UserInformationDataModel userInformationDataModel) {


        LoginSession.setLogin(true);
        userInformationSharedPrefManager.setRegisterInformationData(userInformationDataModel);
        progressBar.dismiss();
        FragmentManager fragmentManager=myContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fracontainer, new UserPanelFragment());
        fragmentTransaction.commit();

       if(userInformationDataModel.getCompelte_Profile()==1)
       {
           Picasso.with(myContext).load(userInformationDataModel.getUser_Profile()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.default_avatar).into(MainActivity.imageView);
           MainActivity.Username_txv.setText(userInformationDataModel.getUser_First_Name()+" "+userInformationDataModel.getUser_User_Last_Name());
           MainActivity.Email_txv.setText(userInformationDataModel.getUser_Email_Address());
       }else
       {
           MainActivity.imageView.setImageResource(R.drawable.default_avatar);
           MainActivity.Username_txv.setText("کاربر عضو");
           MainActivity.Email_txv.setText("example@example.com");
       }



    }

}
