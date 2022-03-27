package bluedragon.app.netcollegebartar.SqliteManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.Departemants;
import bluedragon.app.netcollegebartar.DataModels.LastNews;

/**
 * Created by Blue_Dragon on 6/30/2019.
 */
public class DepartemantsAsyncTask extends AsyncTask<Void,Void,Void> {


    private Context context;
    private ArrayList<Departemants> DepartemantsList;
    private SqliteManager sqliteManager;


    public DepartemantsAsyncTask(Context context, ArrayList<Departemants> DepartemantsList, SqliteManager sqliteManager)
    {
        this.context = context;
        this.DepartemantsList = DepartemantsList;
        this.sqliteManager = sqliteManager;
    }




    @Override
    protected Void doInBackground(Void... params) {
        SQLiteDatabase DepartemantsSqliteDatabase=sqliteManager.getWritableDatabase();
        ContentValues DepartemantsContentValues=new ContentValues();
        for (int i = 0; i <DepartemantsList.size() ; i++) {
            Departemants DepartemantsListDataModel=DepartemantsList.get(i);
            if(!sqliteManager.checkDepatemantsisRepativeOrNot(DepartemantsListDataModel.getId()))
            {
                DepartemantsContentValues.put(SqliteManager.KEY_DepartemantsID,DepartemantsListDataModel.getId());
                DepartemantsContentValues.put(SqliteManager.KEY_DepartemantsName,DepartemantsListDataModel.getDepartemant_Title());
                DepartemantsContentValues.put(SqliteManager.KEY_DepartemantsLogo,DepartemantsListDataModel.getDepartemants_Image());
                DepartemantsSqliteDatabase.insert(SqliteManager.DEPARTEMANTSTBNAME,null,DepartemantsContentValues);

            }


        }


        return null;
    }
}
