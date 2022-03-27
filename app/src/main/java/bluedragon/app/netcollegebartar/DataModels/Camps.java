package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 6/1/2019.
 */
public class Camps {


    private int Camp_Id;
    private int Camp_ClassID;
    private int Camp_Teacher_ID;
    private String Camp_Name;
    private int CampCapacity;
    private byte Camp_Duration_Value;
    private int Camp_fee;
    private String Camp_Logo_Url;
    private int CampdDepartemantsId;




    public int getCamp_Id() {
        return Camp_Id;
    }

    public void setCamp_Id(int camp_Id) {
        Camp_Id = camp_Id;
    }

    public String getCamp_Logo_Url() {
        return Camp_Logo_Url;
    }

    public void setCamp_Logo_Url(String camp_Logo_Url) {
        Camp_Logo_Url = camp_Logo_Url;
    }

    public String getCamp_Name() {
        return Camp_Name;
    }

    public void setCamp_Name(String camp_Name) {
        Camp_Name = camp_Name;
    }

    public byte getCamp_Duration_Value() {
        return Camp_Duration_Value;
    }

    public void setCamp_Duration_Value(byte camp_Duration_Value) {
        Camp_Duration_Value = camp_Duration_Value;
    }

    public int getCamp_fee() {
        return Camp_fee;
    }

    public void setCamp_fee(int camp_fee) {
        Camp_fee = camp_fee;
    }

    public int getCampCapacity() {
        return CampCapacity;
    }

    public void setCampCapacity(int campCapacity) {
        CampCapacity = campCapacity;
    }

    public int getCamp_ClassID() {
        return Camp_ClassID;
    }

    public void setCamp_ClassID(int camp_ClassID) {
        Camp_ClassID = camp_ClassID;
    }

    public int getCamp_Teacher_ID() {
        return Camp_Teacher_ID;
    }

    public void setCamp_Teacher_ID(int camp_Teacher_ID) {
        Camp_Teacher_ID = camp_Teacher_ID;
    }


    public int getCampdDepartemantsId() {
        return CampdDepartemantsId;
    }

    public void setCampdDepartemantsId(int campdDepartemantsId) {
        CampdDepartemantsId = campdDepartemantsId;
    }
}
