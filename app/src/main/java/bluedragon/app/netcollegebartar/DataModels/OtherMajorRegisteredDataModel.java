package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 6/27/2019.
 */
public class OtherMajorRegisteredDataModel {
    private int OtherMajorRegisterID;
    private int Registered_MajorID;
    private String RegisterDate;
    private String Major_Name;
    private String Major_logo;

    public int getOtherMajorRegisterID() {
        return OtherMajorRegisterID;
    }

    public void setOtherMajorRegisterID(int otherMajorRegisterID) {
        OtherMajorRegisterID = otherMajorRegisterID;
    }

    public int getRegistered_MajorID() {
        return Registered_MajorID;
    }

    public void setRegistered_MajorID(int registered_MajorID) {
        Registered_MajorID = registered_MajorID;
    }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }

    public String getMajor_Name() {
        return Major_Name;
    }

    public void setMajor_Name(String major_Name) {
        Major_Name = major_Name;
    }

    public String getMajor_logo() {
        return Major_logo;
    }

    public void setMajor_logo(String major_logo) {
        Major_logo = major_logo;
    }
}
