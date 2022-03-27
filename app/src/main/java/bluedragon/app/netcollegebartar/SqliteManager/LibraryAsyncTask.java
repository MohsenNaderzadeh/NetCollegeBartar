package bluedragon.app.netcollegebartar.SqliteManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.LastNews;
import bluedragon.app.netcollegebartar.DataModels.Library;

/**
 * Created by Blue_Dragon on 7/2/2019.
 */
public class LibraryAsyncTask extends AsyncTask<Void,Void,Void> {

    private Context context;
    private ArrayList<Library> LibraryBooksList;
    private SqliteManager sqliteManager;

    public LibraryAsyncTask(Context context, ArrayList<Library> libraryBooksList, SqliteManager sqliteManager)
    {
        this.context = context;

        this.LibraryBooksList = libraryBooksList;
        this.sqliteManager = sqliteManager;
    }
    @Override
    protected Void doInBackground(Void... params) {
        SQLiteDatabase LibrarySqliteDatabase=sqliteManager.getWritableDatabase();
        ContentValues LibrayContentValues=new ContentValues();
        for (int i = 0; i <LibraryBooksList.size() ; i++) {
            Library LibraryList=LibraryBooksList.get(i);
            if(!sqliteManager.checkBookIDisRepativeOrNot(LibraryList.getBook_id()))
            {
                LibrayContentValues.put(SqliteManager.KEY_BOOK_ID,LibraryList.getBook_id());
                LibrayContentValues.put(SqliteManager.KEY_BOOK_CATEGORY,LibraryList.getBook_category());
                LibrayContentValues.put(SqliteManager.KEY_BOOK_NAME,LibraryList.getBookName());
                LibrayContentValues.put(SqliteManager.KEY_BOOK_IMAGE,LibraryList.getBookImage());
                LibrayContentValues.put(SqliteManager.KEY_BOOK_DOWNLOAD_LINK,LibraryList.getBookDownloadLink());
                LibrayContentValues.put(SqliteManager.KEY_BOOK_DESC,LibraryList.getBooKDescription());
                LibrarySqliteDatabase.insert(SqliteManager.LibraryBooksTB,null,LibrayContentValues);
            }


        }
        return null;
    }
}
