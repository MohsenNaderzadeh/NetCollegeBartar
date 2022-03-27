package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 6/22/2019.
 */
public class ExClassListModel {
    private int ExClassId;
    private String ExLogoURL;
    private String ExName;
    private String ExTeacherName;
    private int ExDuration;
    private int ExFee;
    private int ExCapacity;

    public int getExClassId() {
        return ExClassId;
    }

    public void setExClassId(int exClassId) {
        ExClassId = exClassId;
    }

    public String getExLogoURL() {
        return ExLogoURL;
    }

    public void setExLogoURL(String exLogoURL) {
        ExLogoURL = exLogoURL;
    }

    public String getExName() {
        return ExName;
    }

    public void setExName(String exName) {
        ExName = exName;
    }

    public String getExTeacherName() {
        return ExTeacherName;
    }

    public void setExTeacherName(String exTeacherName) {
        ExTeacherName = exTeacherName;
    }

    public int getExDuration() {
        return ExDuration;
    }

    public void setExDuration(int exDuration) {
        ExDuration = exDuration;
    }

    public int getExFee() {
        return ExFee;
    }

    public void setExFee(int exFee) {
        ExFee = exFee;
    }

    public int getExCapacity() {
        return ExCapacity;
    }

    public void setExCapacity(int exCapacity) {
        ExCapacity = exCapacity;
    }
}
