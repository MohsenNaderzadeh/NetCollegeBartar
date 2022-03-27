package bluedragon.app.netcollegebartar.SqliteManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.Camps;
import bluedragon.app.netcollegebartar.DataModels.Services_full_DataModel;

/**
 * Created by Blue_Dragon on 7/1/2019.
 */
public class TeacherCampsAsyncTask extends AsyncTask<Void,Void,Void> {
    private Context context;
    private ArrayList<Camps> TeacherCampsList;
    private SqliteManager sqliteManager;

    public  TeacherCampsAsyncTask(Context context, ArrayList<Camps> teacherCampsList, SqliteManager sqliteManager)
    {
        this.context = context;

        TeacherCampsList = teacherCampsList;
        this.sqliteManager = sqliteManager;
    }
    @Override
    protected Void doInBackground(Void... params) {
        SQLiteDatabase TeacherCampsSqliteDatabase=sqliteManager.getWritableDatabase();
        ContentValues TeacherCampsContentValues=new ContentValues();
        for (int i = 0; i <TeacherCampsList.size() ; i++) {
            Camps  Teachercampslist=TeacherCampsList.get(i);
            if(!sqliteManager.TeacherClassesisRepativeOrNot(Teachercampslist.getCamp_Id()))
            {
                TeacherCampsContentValues.put(SqliteManager.KEY_CAMP_ID,Teachercampslist.getCamp_Id());
                TeacherCampsContentValues.put(SqliteManager.KEY_CAMP_CLASS_ID,Teachercampslist.getCamp_ClassID());
                TeacherCampsContentValues.put(SqliteManager.KEY_CAMP_TEACHER_ID,Teachercampslist.getCamp_Teacher_ID());
                TeacherCampsContentValues.put(SqliteManager.KEY_CAMP_NAME,Teachercampslist.getCamp_Name());
                TeacherCampsContentValues.put(SqliteManager.KEY_CAMP_CAPACITY,Teachercampslist.getCampCapacity());
                TeacherCampsContentValues.put(SqliteManager.KEY_CAMP_DURATION_VALUE,Teachercampslist.getCamp_Duration_Value());
                TeacherCampsContentValues.put(SqliteManager.KEY_CAMP_FEE,Teachercampslist.getCamp_fee());
                TeacherCampsContentValues.put(SqliteManager.KEY_CAMP_LOGO_URL,Teachercampslist.getCamp_Logo_Url());
                TeacherCampsContentValues.put(SqliteManager.KEY_CAMP_Departemant_ID,Teachercampslist.getCampdDepartemantsId());





                TeacherCampsSqliteDatabase.insert(SqliteManager.TEACHERCAMPTB,null,TeacherCampsContentValues);

            }


        }
        return null;
    }
}
