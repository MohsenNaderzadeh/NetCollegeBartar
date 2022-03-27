package bluedragon.app.netcollegebartar.SqliteManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.android.volley.ServerError;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.Departemants;
import bluedragon.app.netcollegebartar.DataModels.Services;

/**
 * Created by Blue_Dragon on 7/1/2019.
 */
public class OtherMajorsAsyncTask extends AsyncTask<Void,Void,Void> {
    private Context context;
    private ArrayList<Services> OtherMajorList;
    private SqliteManager sqliteManager;

    public OtherMajorsAsyncTask(Context context, ArrayList<Services> OtherMajorList, SqliteManager sqliteManager)
    {
        this.context = context;

        this.OtherMajorList = OtherMajorList;
        this.sqliteManager = sqliteManager;
    }
    @Override
    protected Void doInBackground(Void... params) {
        SQLiteDatabase OtherMajorsSqliteDatabase=sqliteManager.getWritableDatabase();
        ContentValues OtherMajorsContentValues=new ContentValues();
        for (int i = 0; i <OtherMajorList.size() ; i++) {
            Services Services=OtherMajorList.get(i);
            if(!sqliteManager.checkOtherMajorisRepativeOrNot(Services.getId()))
            {
                OtherMajorsContentValues.put(SqliteManager.KEY_OTHERMAJORID,Services.getId());
                OtherMajorsContentValues.put(SqliteManager.KEY_OTHERMAJORNAME,Services.getService_Name());
                OtherMajorsContentValues.put(SqliteManager.KEY_OTHERMAJORLOGO,Services.getService_Logo());
                OtherMajorsContentValues.put(SqliteManager.KEY_OTHERMAJORDESCRIPTION,Services.getServices_Description());
                OtherMajorsSqliteDatabase.insert(SqliteManager.OTHERMAJORSTB,null,OtherMajorsContentValues);

            }


        }



        return null;
    }
}
