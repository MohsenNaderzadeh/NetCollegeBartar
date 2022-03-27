package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 6/10/2019.
 */
public class Login {
    private boolean Error_Stat;
    private String  Error_Text;

    public boolean getError_Stat() {
        return Error_Stat;
    }

    public void setError_Stat(boolean error_Stat) {
        Error_Stat = error_Stat;
    }

    public String getError_Text() {
        return Error_Text;
    }

    public void setError_Text(String error_Text) {
        Error_Text = error_Text;
    }
}
