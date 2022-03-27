package bluedragon.app.netcollegebartar.SharedPrefDataModels;

/**
 * Created by Blue_Dragon on 6/10/2019.
 */
public class UserLoginInfoDataModel {
    private String UserLoginUserName;
    private String UserLoginPassWord;

    public String getUserLoginUserName() {
        return UserLoginUserName;
    }

    public void setUserLoginUserName(String userLoginUserName) {
        UserLoginUserName = userLoginUserName;
    }

    public String getUserLoginPassWord() {
        return UserLoginPassWord;
    }

    public void setUserLoginPassWord(String userLoginPassWord) {
        UserLoginPassWord = userLoginPassWord;
    }
}
