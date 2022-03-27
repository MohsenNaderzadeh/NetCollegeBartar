package bluedragon.app.netcollegebartar.SqliteManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.Departemants;
import bluedragon.app.netcollegebartar.DataModels.Teachers;

/**
 * Created by Blue_Dragon on 7/1/2019.
 */
public class TeachersAsyncTask extends AsyncTask<Void,Void,Void> {
    private Context context;
    private ArrayList<Teachers> TeachersList;
    private SqliteManager sqliteManager;

    public TeachersAsyncTask(Context context, ArrayList<Teachers> TeachersList, SqliteManager sqliteManager)
    {
        this.context = context;
        this.TeachersList = TeachersList;
        this.sqliteManager = sqliteManager;
    }
    @Override
    protected Void doInBackground(Void... params) {
        SQLiteDatabase TeachersSqliteDatabase=sqliteManager.getWritableDatabase();
        ContentValues TeachersContentValues=new ContentValues();
        for (int i = 0; i <TeachersList.size() ; i++) {
            Teachers teacherslist=TeachersList.get(i);
            if(!sqliteManager.checkTeacherisRepativeOrNot(teacherslist.getId()))
            {
                TeachersContentValues.put(SqliteManager.KEY_TEACHERSID,teacherslist.getId());
                TeachersContentValues.put(SqliteManager.KEY_TEACHERSFULLNAME,teacherslist.getTeachers_full_name());
                TeachersContentValues.put(SqliteManager.KEY_TEACHERSDEPARTEMANTSNAME,teacherslist.getTeachers_Departemants_name());
                TeachersContentValues.put(SqliteManager.KEY_TEACHERSPHOTO,teacherslist.getTeachers_photo());
                TeachersContentValues.put(SqliteManager.KEY_TEACHERSSKILL,teacherslist.getTeachers_Skill());
                TeachersContentValues.put(SqliteManager.KEY_TEACHERSRESUME,teacherslist.getTeachers_Resume());
                TeachersContentValues.put(SqliteManager.KEY_TEACHEREMAIL,teacherslist.getTeacher_Email());
                TeachersContentValues.put(SqliteManager.KEY_TEACHERSDEPARTEMANTID,teacherslist.getTeachers_Departemant_ID());
                TeachersSqliteDatabase.insert(SqliteManager.TEACHERTBNAME,null,TeachersContentValues);

            }

        }

            return null;
    }
}
