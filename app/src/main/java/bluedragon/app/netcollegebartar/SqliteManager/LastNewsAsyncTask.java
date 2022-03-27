package bluedragon.app.netcollegebartar.SqliteManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.LastNews;

/**
 * Created by Blue_Dragon on 6/30/2019.
 */
public class LastNewsAsyncTask extends AsyncTask<Void,Void,Void> {

    private Context context;
    private ArrayList<LastNews>LastNewsList;
    private SqliteManager sqliteManager;


    public LastNewsAsyncTask(Context context, ArrayList<LastNews> lastNewsList, SqliteManager sqliteManager)
    {
        this.context = context;
        LastNewsList = lastNewsList;
        this.sqliteManager = sqliteManager;
    }

    @Override
    protected Void doInBackground(Void... params) {

        SQLiteDatabase LastNewsSqliteDatabase=sqliteManager.getWritableDatabase();
        ContentValues LastNewsContentValues=new ContentValues();
        for (int i = 0; i <LastNewsList.size() ; i++) {
            LastNews lastnews=LastNewsList.get(i);
            if(!sqliteManager.checkNewsisRepativeOrNot(lastnews.getLastNewsID()))
            {
                LastNewsContentValues.put(SqliteManager.KEY_NEWS_ID,lastnews.getLastNewsID());
                LastNewsContentValues.put(SqliteManager.KEY_NEWS_TITLE,lastnews.getLastNewsTitle());
                LastNewsContentValues.put(SqliteManager.KEY_NEWS_MAIN_TEXT,lastnews.getLastNewsText());
                LastNewsContentValues.put(SqliteManager.KEY_NEWS_LOGO,lastnews.getLastNewsLogo());
                LastNewsContentValues.put(SqliteManager.KEY_NEWS_DATE,lastnews.getLastNewsDate());
                LastNewsSqliteDatabase.insert(SqliteManager.NEWSTABLENAME,null,LastNewsContentValues);
            }


        }

        return null;
    }
}
