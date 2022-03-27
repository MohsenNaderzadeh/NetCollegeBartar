package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 11/30/2018.
 */
public class Departemants {

    private int id;
    private String Departemant_Title;
    private String Departemants_Image;




    public int getId() {
        return id;
    }


    public String getDepartemant_Title() {
        return Departemant_Title;
    }



    public String getDepartemants_Image() {
        return Departemants_Image;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDepartemant_Title(String departemant_Title) {
        Departemant_Title = departemant_Title;
    }

    public void setDepartemants_Image(String departemants_Image) {
        Departemants_Image = departemants_Image;
    }
}
