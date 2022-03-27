package bluedragon.app.netcollegebartar.SharedPrefManager;

import android.content.Context;
import android.content.SharedPreferences;

import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserLoginInfoDataModel;

/**
 * Created by Blue_Dragon on 6/10/2019.
 */
public class UserLoginInfoSharedPrefManager {


    private static final String USER_LOGIN_INFO_SHARED_PREF_NAME="user_login_info_shared_pref";
    private static final String KEY_USER_LOGIN_USER_NAME="user_login_username";
    private static final String KEY_USER_LOGIN_USER_Password="user_login_Password";


    private SharedPreferences User_Login_Info_Shared_Pref;

    public UserLoginInfoSharedPrefManager(Context context)
    {
        User_Login_Info_Shared_Pref=context.getSharedPreferences(USER_LOGIN_INFO_SHARED_PREF_NAME,Context.MODE_PRIVATE);
    }

    public void SetUserLoginInfo(UserLoginInfoDataModel userLoginInfo)
    {
        if(userLoginInfo!=null)
        {
            SharedPreferences.Editor User_Login_Shared_Pref_Editor=User_Login_Info_Shared_Pref.edit();
            User_Login_Shared_Pref_Editor.putString(KEY_USER_LOGIN_USER_NAME,userLoginInfo.getUserLoginUserName());
            User_Login_Shared_Pref_Editor.putString(KEY_USER_LOGIN_USER_Password,userLoginInfo.getUserLoginPassWord());
            User_Login_Shared_Pref_Editor.apply();
        }

    }
    public UserLoginInfoDataModel getUserLoginInfo()
    {
        UserLoginInfoDataModel userLoginInfo=new UserLoginInfoDataModel();
        userLoginInfo.setUserLoginUserName(User_Login_Info_Shared_Pref.getString(KEY_USER_LOGIN_USER_NAME,""));
        userLoginInfo.setUserLoginPassWord(User_Login_Info_Shared_Pref.getString(KEY_USER_LOGIN_USER_Password,""));
        return  userLoginInfo;
    }
}
