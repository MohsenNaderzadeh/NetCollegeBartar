package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 12/28/2018.
 */
public class Classes {

    private int Class_id;
    private int Departemant_id;
    private String Class_Name;
    private String Class_Logo;
    private String Class_Description;


    public int getClass_id() {
        return Class_id;
    }

    public int getDepartemant_id() {
        return Departemant_id;
    }

    public String getClass_Name() {
        return Class_Name;
    }

    public String getClass_Logo() {
        return Class_Logo;
    }

    public String getClass_Description() {
        return Class_Description;
    }

    public void setClass_id(int class_id) {
        Class_id = class_id;
    }

    public void setDepartemant_id(int departemant_id) {
        Departemant_id = departemant_id;
    }

    public void setClass_Name(String class_Name) {
        Class_Name = class_Name;
    }

    public void setClass_Description(String class_Description) {
        Class_Description = class_Description;
    }

    public void setClass_Logo(String class_Logo) {
        Class_Logo = class_Logo;
    }
}
