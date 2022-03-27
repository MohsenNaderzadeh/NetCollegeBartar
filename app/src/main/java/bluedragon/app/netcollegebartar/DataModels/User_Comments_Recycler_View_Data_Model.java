package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 5/3/2019.
 */
public class User_Comments_Recycler_View_Data_Model {

    private int User_Comment_ID;
    private float User_Rating_Score;
    private String User_Profile_URL;
    private String User_Commenter_Name;
    private String User_Comment_Text;
    private int CommentSenderID;
    private String CommentSendDate;
    private String CommentSendTime;
    private int TeacherID;

    public int getUser_Comment_ID() {
        return User_Comment_ID;
    }

    public void setUser_Comment_ID(int user_Comment_ID) {
        User_Comment_ID = user_Comment_ID;
    }

    public float getUser_Rating_Score() {
        return User_Rating_Score;
    }

    public void setUser_Rating_Score(float user_Rating_Score) {
        User_Rating_Score =user_Rating_Score;
    }

    public String getUser_Profile_URL() {
        return User_Profile_URL;
    }

    public void setUser_Profile_URL(String user_Profile_URL) {
        User_Profile_URL = user_Profile_URL;
    }

    public String getUser_Comment_Text() {
        return User_Comment_Text;
    }

    public void setUser_Comment_Text(String user_Comment_Text) {
        User_Comment_Text = user_Comment_Text;
    }

    public String getUser_Commenter_Name() {
        return User_Commenter_Name;
    }

    public void setUser_Commenter_Name(String user_Commenter_Name) {
        User_Commenter_Name = user_Commenter_Name;
    }

    public int getCommentSenderID() {
        return CommentSenderID;
    }

    public void setCommentSenderID(int commentSenderID) {
        CommentSenderID = commentSenderID;
    }

    public String getCommentSendDate() {
        return CommentSendDate;
    }

    public void setCommentSendDate(String commentSendDate) {
        CommentSendDate = commentSendDate;
    }

    public String getCommentSendTime() {
        return CommentSendTime;
    }

    public void setCommentSendTime(String commentSendTime) {
        CommentSendTime = commentSendTime;
    }

    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int teacherID) {
        TeacherID = teacherID;
    }
}
