package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 1/20/2019.
 */
public class Services_full_DataModel {
    private int id;
    private String Service_Name;
    private String Service_Logo;
    private String Services_Description;

    public int getId() {
        return id;
    }

    public String getService_Name() {
        return Service_Name;
    }

    public String getService_Logo() {
        return Service_Logo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setService_Name(String service_Name) {
        Service_Name = service_Name;
    }

    public void setService_Logo(String service_Logo) {
        Service_Logo = service_Logo;
    }

    public String getServices_Description() {
        return Services_Description;
    }

    public void setServices_Description(String services_Description) {
        Services_Description = services_Description;
    }
}
