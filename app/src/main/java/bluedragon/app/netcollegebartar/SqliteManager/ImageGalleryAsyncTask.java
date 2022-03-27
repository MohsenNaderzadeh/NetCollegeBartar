package bluedragon.app.netcollegebartar.SqliteManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.ImageGallery;
import bluedragon.app.netcollegebartar.DataModels.Library;

/**
 * Created by Blue_Dragon on 7/2/2019.
 */
public class ImageGalleryAsyncTask  extends AsyncTask<Void,Void,Void> {


    private Context context;
    private ArrayList<ImageGallery> ImageGalleryOfflineList;
    private SqliteManager sqliteManager;

    public ImageGalleryAsyncTask(Context context, ArrayList<ImageGallery> imageGalleryList, SqliteManager sqliteManager)
    {

        this.context = context;
        this.ImageGalleryOfflineList = imageGalleryList;
        this.sqliteManager = sqliteManager;
    }
    @Override
    protected Void doInBackground(Void... params) {
        SQLiteDatabase ImageGallerySqliteDatabase=sqliteManager.getWritableDatabase();
        ContentValues ImageGalleryContentValues=new ContentValues();
        for (int i = 0; i <ImageGalleryOfflineList.size() ; i++) {
            ImageGallery ImageGalleryList=ImageGalleryOfflineList.get(i);
            if(!sqliteManager.ImageIDisRepativeOrNot(ImageGalleryList.getImageID()))
            {
                ImageGalleryContentValues.put(SqliteManager.KEY_IMAGE_ID,(ImageGalleryList.getImageID()));
                ImageGalleryContentValues.put(SqliteManager.KEY_IMAGE_LOGO_URL,ImageGalleryList.getImageLogoUrl());
                ImageGalleryContentValues.put(SqliteManager.KEY_IMAGE_CAPTION,ImageGalleryList.getImageCaption());
                ImageGallerySqliteDatabase.insert(SqliteManager.IMAGEGALLERYTB,null,ImageGalleryContentValues);
            }


        }
        return null;
    }
}
