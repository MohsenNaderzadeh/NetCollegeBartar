package bluedragon.app.netcollegebartar.SqliteManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.Classes;
import bluedragon.app.netcollegebartar.DataModels.User_Comments_Recycler_View_Data_Model;

/**
 * Created by Blue_Dragon on 7/1/2019.
 */
public class TeacherCommentsAsyncTask extends AsyncTask<Void,Void,Void> {

    private Context context;
    private ArrayList<User_Comments_Recycler_View_Data_Model> CommentList;
    private SqliteManager sqliteManager;
    public TeacherCommentsAsyncTask(Context context, ArrayList<User_Comments_Recycler_View_Data_Model> commentList, SqliteManager sqliteManager)
    {

        this.context = context;
        CommentList = commentList;
        this.sqliteManager = sqliteManager;
    }
    @Override
    protected Void doInBackground(Void... params) {
        SQLiteDatabase CommentsListSqliteDatabase=sqliteManager.getWritableDatabase();
        ContentValues CommentsContentValues=new ContentValues();
        for (int i = 0; i <CommentList.size() ; i++) {
            User_Comments_Recycler_View_Data_Model CommentsList=CommentList.get(i);
            if(!sqliteManager.TeacherCommentsisRepativeOrNot(CommentsList.getUser_Comment_ID()))
            {
                CommentsContentValues.put(SqliteManager.KEY_USER_COMMENT_ID,CommentsList.getUser_Comment_ID());
                CommentsContentValues.put(SqliteManager.KEY_USER_RATING_SCORE,CommentsList.getUser_Rating_Score());
                CommentsContentValues.put(SqliteManager.KEY_USER_PROFILE_URL,CommentsList.getUser_Profile_URL());
                CommentsContentValues.put(SqliteManager.KEY_USER_COMMENTER_NAME,CommentsList.getUser_Commenter_Name());
                CommentsContentValues.put(SqliteManager.KEY_COMMENT_TEXT,CommentsList.getUser_Comment_Text());
                CommentsContentValues.put(SqliteManager.KEY_COMMENT_SENDER_ID,CommentsList.getCommentSenderID());
                CommentsContentValues.put(SqliteManager.KEY_COMMENT_SEND_DATE,CommentsList.getCommentSendDate());
                CommentsContentValues.put(SqliteManager.KEY_COMMENT_SEND_TIME,CommentsList.getCommentSendTime());
                CommentsContentValues.put(SqliteManager.KEY_TEACHER_ID,CommentsList.getTeacherID());
                CommentsListSqliteDatabase.insert(SqliteManager.TEACHERCommentsTB,null,CommentsContentValues);

            }


        }
        return null;
    }
}
