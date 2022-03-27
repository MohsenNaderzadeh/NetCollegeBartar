package bluedragon.app.netcollegebartar.Views.Fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;

/**
 * Created by Blue_Dragon on 6/10/2019.
 */
public class RegisterFragment extends Fragment implements ApiService.OnRegisterInformationRecieved {

    private View view;
    private EditText register_user_phone_number_ed,register_user_password_ed;
    private ImageButton register_Show_Password_ImV;
    private Button Register_btn;
    private TextView Account_Having_Question,Account_login_Text_view;
    private FragmentActivity myContext;
    private ApiService apiService;
    private Typeface typeface;
    private UserInformationSharedPrefManager userInformationSharedPrefManager;
    private SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.registerfragment,container,false);
        setViews();
        setTypeFace();
        Password_EditText_Click();
        Show_Password_Txt_On_Click();
        account_Login_Text_View_Click();
        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apiService.internetConnection())
                {
                    apiService.RegisterApi(AppConfig.URL_REGISTER,RegisterFragment.this,register_user_phone_number_ed.getText().toString(),register_user_password_ed.getText().toString());

                }
                else
                {
                    Toast.makeText(myContext,"کاربر گرامی لطفا اتصال خود را به اینترنت بررسی بفرمایید ",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    private void setViews()
    {
        register_user_phone_number_ed=(EditText)view.findViewById(R.id.register_user_phone_number_ed);
        register_user_password_ed=(EditText)view.findViewById(R.id.register_user_password_ed);
        register_Show_Password_ImV=(ImageButton)view.findViewById(R.id.register_Show_Password_ImV);
        register_user_password_ed.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        Register_btn=(Button)view.findViewById(R.id.Register_btn);
        Account_Having_Question=(TextView)view.findViewById(R.id.Account_Having_Question);
        Account_login_Text_view=(TextView)view.findViewById(R.id.Account_login_Text_view);
        apiService=new ApiService(myContext);
        typeface= Typeface.createFromAsset(myContext.getAssets(), "fonts/iransansdn.ttf");
        userInformationSharedPrefManager =new UserInformationSharedPrefManager(myContext);
        sessionManager=new SessionManager(myContext);

    }
    private void setTypeFace()
    {
        register_user_phone_number_ed.setTypeface(typeface);
        register_user_password_ed.setTypeface(typeface);
        Register_btn.setTypeface(typeface);
        Account_Having_Question.setTypeface(typeface);
        Account_login_Text_view.setTypeface(typeface);
        Register_btn.setTypeface(typeface);
    }
    private void Show_Password_Txt_On_Click()
    {
        register_Show_Password_ImV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(register_Show_Password_ImV.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_eye_off).getConstantState()))
                {
                    register_Show_Password_ImV.setImageResource(R.drawable.ic_eye);
                    register_user_password_ed.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    register_user_password_ed.setSelection(register_user_password_ed.length());
                    register_user_password_ed.setTypeface(typeface);
                }
                else
                {
                    register_Show_Password_ImV.setImageResource(R.drawable.ic_eye_off);
                    register_user_password_ed.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    register_user_password_ed.setSelection(register_user_password_ed.length());
                    register_user_password_ed.setTypeface(typeface);
                }
            }
        });
    }
    private void Password_EditText_Click() {
        register_user_password_ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                register_user_password_ed.setTypeface(typeface);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void account_Login_Text_View_Click()
    {
        Account_login_Text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fracontainer, new LoginFragment());
                fragmentTransaction.commit();
            }
        });
    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void OnRegisterInformationRecieved(UserInformationDataModel userInformationDataModel) {
        userInformationSharedPrefManager.setRegisterInformationData(userInformationDataModel);
        Toast.makeText(getActivity().getApplicationContext(),"ثبت نام موفقیت آمیز",Toast.LENGTH_LONG).show();
        sessionManager.setLogin(true);
    }
}
