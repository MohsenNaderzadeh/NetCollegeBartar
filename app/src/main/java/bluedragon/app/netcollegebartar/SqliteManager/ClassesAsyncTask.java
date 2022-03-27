package bluedragon.app.netcollegebartar.SqliteManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.Classes;
import bluedragon.app.netcollegebartar.DataModels.Departemants;

/**
 * Created by Blue_Dragon on 7/1/2019.
 */
public class ClassesAsyncTask extends AsyncTask<Void,Void,Void> {

    private Context context;
    private ArrayList<Classes>ClassesList;
    private SqliteManager sqliteManager;
    public ClassesAsyncTask(Context context, ArrayList<Classes> classesList, SqliteManager sqliteManager)
    {
        this.context = context;

        ClassesList = classesList;
        this.sqliteManager = sqliteManager;
    }
    @Override
    protected Void doInBackground(Void... params) {

        SQLiteDatabase ClassesSqliteDatabase=sqliteManager.getWritableDatabase();
        ContentValues ClassesContentValues=new ContentValues();
        for (int i = 0; i <ClassesList.size() ; i++) {
            Classes ClassesListDataModel=ClassesList.get(i);
            if(!sqliteManager.ClassesisRepativeOrNot(ClassesListDataModel.getClass_id()))
            {
                ClassesContentValues.put(SqliteManager.KEY_CLASSID,ClassesListDataModel.getClass_id());
                ClassesContentValues.put(SqliteManager.KEY_DEPARTEMNATID,ClassesListDataModel.getDepartemant_id());
                ClassesContentValues.put(SqliteManager.KEY_CLASSNAME,ClassesListDataModel.getClass_Name());
                ClassesContentValues.put(SqliteManager.KEY_CLASSLOGO,ClassesListDataModel.getClass_Logo());
                ClassesContentValues.put(SqliteManager.KEY_CLASSDESC,ClassesListDataModel.getClass_Description());
                ClassesSqliteDatabase.insert(SqliteManager.CLASSESTBNAME,null,ClassesContentValues);

            }


        }

        return null;
    }
}
