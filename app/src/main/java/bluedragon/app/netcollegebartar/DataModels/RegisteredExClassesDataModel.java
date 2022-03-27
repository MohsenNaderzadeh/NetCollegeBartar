package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 6/27/2019.
 */
public class RegisteredExClassesDataModel {
    private int RegisterId;
    private int RegisteredExClassID;
    private String RegisteredExClassLogo;
    private String RegisteredExClassName;
    private String RegisteredExClassTeacherName;
    private String RegisteredExClassRegDate;

    public int getRegisteredExClassID() {
        return RegisteredExClassID;
    }

    public void setRegisteredExClassID(int registeredExClassID) {
        RegisteredExClassID = registeredExClassID;
    }

    public String getRegisteredExClassLogo() {
        return RegisteredExClassLogo;
    }

    public void setRegisteredExClassLogo(String registeredExClassLogo) {
        RegisteredExClassLogo = registeredExClassLogo;
    }

    public String getRegisteredExClassName() {
        return RegisteredExClassName;
    }

    public void setRegisteredExClassName(String registeredExClassName) {
        RegisteredExClassName = registeredExClassName;
    }

    public String getRegisteredExClassTeacherName() {
        return RegisteredExClassTeacherName;
    }

    public void setRegisteredExClassTeacherName(String registeredExClassTeacherName) {
        RegisteredExClassTeacherName = registeredExClassTeacherName;
    }

    public String getRegisteredExClassRegDate() {
        return RegisteredExClassRegDate;
    }

    public void setRegisteredExClassRegDate(String registeredExClassRegDate) {
        RegisteredExClassRegDate = registeredExClassRegDate;
    }

    public int getRegisterId() {
        return RegisterId;
    }

    public void setRegisterId(int registerId) {
        RegisterId = registerId;
    }
}
