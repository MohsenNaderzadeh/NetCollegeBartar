package bluedragon.app.netcollegebartar.SharedPrefManager;

import android.content.Context;
import android.content.SharedPreferences;

import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;

/**
 * Created by Blue_Dragon on 6/13/2019.
 */
public class UserInformationSharedPrefManager {


    private static final String REGISTER_INFORMATION_SHARED_PREF_NAME="user_registeration_info";
    private static final String KEY_USER_ID="user_user_id";
    private static final String KEY_USER_FIRST_NAME="user_first_name";
    private static final String KEY_USER_USER_LAST_NAME="user_last_name";
    private static final String KEY_USER_PHONE_NUMBER="user_phone_number";
    private static final String KEY_USER_USER_NAME="user_user_name";
    private static final String KEY_USER_PASSWORD="user_password";
    private static final String KEY_USER_PROFILE_URL="user_profile_url";
    private static final String KEY_USER_EMAIL_Address="user_email_address";
    private static final String KEY_COMPELTE_PROFILE="user_profile_stat";

    public SharedPreferences RegisterInformationSharedPrefManager;

    public UserInformationSharedPrefManager(Context context)
    {
        RegisterInformationSharedPrefManager=context.getSharedPreferences(REGISTER_INFORMATION_SHARED_PREF_NAME,Context.MODE_PRIVATE);
    }

    public void setRegisterInformationData(UserInformationDataModel userInformationDataModel)
    {
        if(userInformationDataModel !=null)
        {
            SharedPreferences.Editor RegisterInformation=RegisterInformationSharedPrefManager.edit();
            RegisterInformation.putInt(KEY_USER_ID, userInformationDataModel.getUser_ID());
            RegisterInformation.putString(KEY_USER_FIRST_NAME, userInformationDataModel.getUser_First_Name());
            RegisterInformation.putString(KEY_USER_USER_LAST_NAME, userInformationDataModel.getUser_User_Last_Name());
            RegisterInformation.putString(KEY_USER_PHONE_NUMBER, userInformationDataModel.getUser_Phone_Number());
            RegisterInformation.putString(KEY_USER_USER_NAME, userInformationDataModel.getUser_User_Name());
            RegisterInformation.putString(KEY_USER_PASSWORD, userInformationDataModel.getUser_Password());
            RegisterInformation.putString(KEY_USER_PROFILE_URL, userInformationDataModel.getUser_Profile());
            RegisterInformation.putString(KEY_USER_EMAIL_Address, userInformationDataModel.getUser_Email_Address());
            RegisterInformation.putInt(KEY_COMPELTE_PROFILE, userInformationDataModel.getCompelte_Profile());
            RegisterInformation.apply();
        }
    }
    public UserInformationDataModel getRegisterationInfo()
    {
        UserInformationDataModel userInformationDataModel =new UserInformationDataModel();
        userInformationDataModel.setUser_ID(RegisterInformationSharedPrefManager.getInt(KEY_USER_ID,0));
        userInformationDataModel.setUser_First_Name(RegisterInformationSharedPrefManager.getString(KEY_USER_FIRST_NAME,""));
        userInformationDataModel.setUser_User_Last_Name(RegisterInformationSharedPrefManager.getString(KEY_USER_USER_LAST_NAME,""));
        userInformationDataModel.setUser_Phone_Number(RegisterInformationSharedPrefManager.getString(KEY_USER_PHONE_NUMBER,""));
        userInformationDataModel.setUser_User_Name(RegisterInformationSharedPrefManager.getString(KEY_USER_USER_NAME,""));
        userInformationDataModel.setUser_Password(RegisterInformationSharedPrefManager.getString(KEY_USER_PASSWORD,""));
        userInformationDataModel.setUser_Profile(RegisterInformationSharedPrefManager.getString(KEY_USER_PROFILE_URL,""));
        userInformationDataModel.setUser_Email_Address(RegisterInformationSharedPrefManager.getString(KEY_USER_EMAIL_Address,""));
        userInformationDataModel.setCompelte_Profile(RegisterInformationSharedPrefManager.getInt(KEY_COMPELTE_PROFILE,0));
        return userInformationDataModel;
    }
}
