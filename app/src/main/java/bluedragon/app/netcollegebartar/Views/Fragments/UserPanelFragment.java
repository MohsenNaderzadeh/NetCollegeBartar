package bluedragon.app.netcollegebartar.Views.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;

import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;
import bluedragon.app.netcollegebartar.Views.Activities.MainActivity;

/**
 * Created by Blue_Dragon on 6/18/2019.
 */
public class UserPanelFragment extends Fragment {
    View view;
    SessionManager sessionManager;
    UserInformationSharedPrefManager userInformationSharedPrefManager;
    FragmentActivity myContext;
    ApiService apiService;
    Button userpanel_teachers_submit_comment,userpanel_my_registered_classes,userpanel_edit_my_profile,userpanel_logout_btn,Show_Other_Registered_Classes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.userpanelfragment,container,false);
        SetViews();
        sessionManager=new SessionManager(myContext);
        userInformationSharedPrefManager=new UserInformationSharedPrefManager(myContext);
        userpanel_logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sessionManager.setLogin(false);
                FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fracontainer, new LoginFragment());
                fragmentTransaction.commit();
                MainActivity.imageView.setImageResource(R.drawable.default_avatar);
                MainActivity.Username_txv.setText("کاربر میهمان");
                MainActivity.Email_txv.setText("example@example.com");

            }
        });
        userpanel_edit_my_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apiService.internetConnection())
                {
                    FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fracontainer, new Fragment_Edit_Profile());
                    fragmentTransaction.commit();
                }

            }
        });
        userpanel_my_registered_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apiService.internetConnection())
                {
                    FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fracontainer, new Fragment_show_all_my_registered_exclasses());
                    fragmentTransaction.commit();
                }

            }
        });
        Show_Other_Registered_Classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apiService.internetConnection())
                {
                    FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fracontainer, new Fragment_Show_all_Other_Registered_Classes_Fragment());
                    fragmentTransaction.commit();
                }

            }
        });

       UserInformationDataModel userInformationDataModel=userInformationSharedPrefManager.getRegisterationInfo();
        if(userInformationDataModel.getCompelte_Profile()==0)
        {
            Toast.makeText(myContext,"کاربر گرامی لطفا پروفایل خود را تکمیل کنید تا در مراحل ثبت نام با مشکل مواجهه نشوید",Toast.LENGTH_LONG).show();
            MainActivity.imageView.setImageResource(R.drawable.default_avatar);
            MainActivity.Username_txv.setText("کاربر عضو");
            MainActivity.Email_txv.setText("example@example.com");
        }

        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }
    private void SetViews()
    {
        Typeface typeface=Typeface.createFromAsset(getContext().getAssets(),"fonts/iransansdn.ttf");

        userpanel_my_registered_classes=(Button)view.findViewById(R.id.userpanel_my_registered_classes);
        userpanel_edit_my_profile=(Button)view.findViewById(R.id.userpanel_edit_my_profile);
        userpanel_logout_btn=(Button)view.findViewById(R.id.userpanel_logout_btn);
        Show_Other_Registered_Classes=(Button)view.findViewById(R.id.Show_Other_Registered_Classes);
        userpanel_my_registered_classes.setTypeface(typeface);
        userpanel_edit_my_profile.setTypeface(typeface);
        userpanel_logout_btn.setTypeface(typeface);
        Show_Other_Registered_Classes.setTypeface(typeface);
        apiService=new ApiService(myContext);
    }



}
