package bluedragon.app.netcollegebartar.DataFakeGenerator;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.Camps;
import bluedragon.app.netcollegebartar.DataModels.User_Comments_Recycler_View_Data_Model;


/**
 * Created by Blue_Dragon on 5/9/2019.
 */
public class Data_Fake_Generator {



    public  static ArrayList<User_Comments_Recycler_View_Data_Model> GetCommentsList()
    {
        ArrayList<User_Comments_Recycler_View_Data_Model>Comments_List=new ArrayList<>();
        for (int i=1;i<5;i++)
        {
            User_Comments_Recycler_View_Data_Model comments_List=new User_Comments_Recycler_View_Data_Model();
            comments_List.setUser_Comment_ID(i);
            comments_List.setUser_Commenter_Name("محسن نادرزاده");
            comments_List.setUser_Comment_Text("بسیار عالی هستند");
            comments_List.setUser_Profile_URL("http://netcollege-bartar.ir/categorypic/813IMG_20160409_080626.jpg");
            comments_List.setUser_Rating_Score(0f);
            Comments_List.add(comments_List);
        }
        return Comments_List;
    }


    public static ArrayList<Camps> GetCapmsList()
    {
        ArrayList<Camps> camp_List=new ArrayList<>();

        for(int i=1;i<5;i++)
        {
            Camps Camp_list=new Camps();
            Camp_list.setCamp_Id(i);
            Camp_list.setCamp_Name("دوره سیسکو");
            Camp_list.setCamp_Duration_Value((byte)50);
            Camp_list.setCamp_fee(3000000);
            Camp_list.setCampCapacity(30);
            Camp_list.setCamp_Logo_Url("https://pbs.twimg.com/profile_images/877385547370475520/CCBRiqHQ_400x400.jpg");

            camp_List.add(Camp_list);
        }
        return camp_List;
    }

}
