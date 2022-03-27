package bluedragon.app.netcollegebartar.Views.Fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;
import bluedragon.app.netcollegebartar.Views.Activities.MainActivity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Blue_Dragon on 6/24/2019.
 */
public class Fragment_Edit_Profile extends Fragment implements ApiService.OnRegisterInformationRecieved {
    View view;
    private static final String TAG = "Fragment_Edit_Profile";
    FragmentActivity myContext;
    UserInformationSharedPrefManager userInformationSharedPrefManager;
    UserInformationDataModel userInformationDataModel;
    ApiService apiService;
    Button Submit_new_information_for_user_profile;
    EditText edit_profile_user_first_name,edit_profile_user_last_name,edit_profile_user_profile_url,edit_profile_user_email_url,edit_profile_user_phone_number,edit_profile_user_user_name,edit_profile_password_name;
    TextView edit_profile_user_first_name_txt,edit_profile_user_last_name_txt,edit_profile_user_profile_url_txt,edit_profile_user_email_url_txt,edit_profile_user_phone_number_txt,edit_profile_user_user_name_txt,edit_profile_user_password_txt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_edit_profile,container,false);
        SetViews();
        Submit_new_information_for_user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_profile_user_first_name.getText().toString().equals(userInformationDataModel.getUser_First_Name()) && edit_profile_user_last_name.getText().toString().equals(userInformationDataModel.getUser_User_Last_Name()) && edit_profile_user_profile_url.getText().toString().equals(userInformationDataModel.getUser_Profile()) && edit_profile_user_email_url.getText().toString().equals(userInformationDataModel.getUser_Email_Address())&& edit_profile_user_user_name.getText().toString().equals(userInformationDataModel.getUser_User_Name())&& edit_profile_password_name.getText().toString().equals(userInformationDataModel.getUser_Password()) && edit_profile_user_phone_number.getText().toString().equals(userInformationDataModel.getUser_Phone_Number()))
                {
                    Toast.makeText(myContext,"شما تغییراتی ایجاد نکردید", Toast.LENGTH_LONG).show();
                }
                else
                {
                    apiService.UpdateUserProfileInfo(AppConfig.URL_UPDATE_USER_PROFILE_INFO,Fragment_Edit_Profile.this,userInformationDataModel.getUser_ID(),edit_profile_user_first_name.getText().toString(),edit_profile_user_last_name.getText().toString(),edit_profile_user_profile_url.getText().toString(),edit_profile_user_email_url.getText().toString(),edit_profile_user_phone_number.getText().toString(), edit_profile_user_user_name.getText().toString(),edit_profile_password_name.getText().toString());
                }
            }
        });
        return view;
    }
    private void SetViews()
    {
        apiService=new ApiService(myContext);
        Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/iransansdn.ttf");
        edit_profile_user_first_name_txt=(TextView)view.findViewById(R.id.edit_profile_user_first_name_txt);
        edit_profile_user_last_name_txt=(TextView)view.findViewById(R.id.edit_profile_user_last_name_txt);
        edit_profile_user_profile_url_txt=(TextView)view.findViewById(R.id.edit_profile_user_profile_url_txt);
        edit_profile_user_email_url_txt=(TextView)view.findViewById(R.id.edit_profile_user_email_url_txt);
        edit_profile_user_phone_number_txt=(TextView)view.findViewById(R.id.edit_profile_user_phone_number_txt);
        edit_profile_user_user_name_txt=(TextView)view.findViewById(R.id.edit_profile_user_user_name_txt);
        edit_profile_user_password_txt=(TextView)view.findViewById(R.id.edit_profile_user_password_txt);

        edit_profile_user_first_name=(EditText)view.findViewById(R.id.edit_profile_user_first_name);
        edit_profile_user_last_name=(EditText)view.findViewById(R.id.edit_profile_user_last_name);
        edit_profile_user_profile_url=(EditText)view.findViewById(R.id.edit_profile_user_profile_url);
        edit_profile_user_email_url=(EditText)view.findViewById(R.id.edit_profile_user_email_url);
        edit_profile_user_user_name=(EditText)view.findViewById(R.id.edit_profile_user_user_name);
        edit_profile_password_name=(EditText)view.findViewById(R.id.edit_profile_password_name);
        edit_profile_user_phone_number=(EditText)view.findViewById(R.id.edit_profile_user_phone_number);

        Submit_new_information_for_user_profile=(Button)view.findViewById(R.id.Submit_new_information_for_user_profile);
        Submit_new_information_for_user_profile.setTypeface(typeface);
       // editprofile_userprofilephoto=(CircleImageView) view.findViewById(R.id.editprofile_userprofilephoto);
        edit_profile_user_first_name_txt.setTypeface(typeface);
        edit_profile_user_first_name.setTypeface(typeface);
        edit_profile_user_last_name.setTypeface(typeface);
        edit_profile_user_last_name_txt.setTypeface(typeface);
        edit_profile_user_profile_url_txt.setTypeface(typeface);
        edit_profile_user_email_url_txt.setTypeface(typeface);
        edit_profile_user_phone_number_txt.setTypeface(typeface);
        edit_profile_user_user_name_txt.setTypeface(typeface);
        edit_profile_user_password_txt.setTypeface(typeface);
        userInformationSharedPrefManager=new UserInformationSharedPrefManager(myContext);
         userInformationDataModel=userInformationSharedPrefManager.getRegisterationInfo();
        if(userInformationDataModel!=null) {
            Log.i(TAG, "user profile info ");
            Log.i(TAG, "fn" + userInformationDataModel.getUser_First_Name());
            Log.i(TAG, "ln" + userInformationDataModel.getUser_User_Last_Name());
            Log.i(TAG, "pofile" + userInformationDataModel.getUser_Profile());
            Log.i(TAG, "em" + userInformationDataModel.getUser_Email_Address());
            Log.i(TAG, "ph" + userInformationDataModel.getUser_Phone_Number());
            Log.i(TAG, "un" + userInformationDataModel.getUser_User_Name());
            Log.i(TAG, "ps" + userInformationDataModel.getUser_Password());
          if(userInformationDataModel.getCompelte_Profile()==0)
          {
              edit_profile_user_first_name.setText("");
              edit_profile_user_last_name.setText("");
              edit_profile_user_profile_url.setText("");
              edit_profile_user_email_url.setText("");
              edit_profile_user_phone_number.setText(userInformationDataModel.getUser_Phone_Number());
              edit_profile_user_user_name.setText("");
              edit_profile_password_name.setText(userInformationDataModel.getUser_Password());

          }else
          {
              edit_profile_user_first_name.setText(userInformationDataModel.getUser_First_Name());
              edit_profile_user_last_name.setText(userInformationDataModel.getUser_User_Last_Name());
              edit_profile_user_profile_url.setText(userInformationDataModel.getUser_Profile());
              edit_profile_user_email_url.setText(userInformationDataModel.getUser_Email_Address());
              edit_profile_user_phone_number.setText(userInformationDataModel.getUser_Phone_Number());
              edit_profile_user_user_name.setText(userInformationDataModel.getUser_User_Name());
              edit_profile_password_name.setText(userInformationDataModel.getUser_Password());
          }
          //  Picasso.with(myContext).load(userInformationDataModel.getUser_Profile()).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(editprofile_userprofilephoto);
        }


    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void OnRegisterInformationRecieved(UserInformationDataModel userInformationDataModel) {
        userInformationSharedPrefManager.setRegisterInformationData(userInformationDataModel);
        edit_profile_user_first_name.setText(userInformationDataModel.getUser_First_Name());
        edit_profile_user_last_name.setText(userInformationDataModel.getUser_User_Last_Name());
        edit_profile_user_profile_url.setText(userInformationDataModel.getUser_Profile());
        edit_profile_user_email_url.setText(userInformationDataModel.getUser_Email_Address());
        edit_profile_user_phone_number.setText(userInformationDataModel.getUser_Phone_Number());
        edit_profile_user_user_name.setText(userInformationDataModel.getUser_User_Name());
        edit_profile_password_name.setText(userInformationDataModel.getUser_Password());
       Picasso.with(myContext).load(userInformationDataModel.getUser_Profile()).error(R.drawable.netcollege_place_holder).placeholder(R.drawable.netcollege_place_holder).into(MainActivity.imageView);
        MainActivity.Username_txv.setText(userInformationDataModel.getUser_First_Name()+" "+userInformationDataModel.getUser_User_Last_Name());
        MainActivity.Email_txv.setText(userInformationDataModel.getUser_Email_Address());
        Toast.makeText(myContext,"عملیات موفقیت آمیز",Toast.LENGTH_LONG).show();
    }
}
