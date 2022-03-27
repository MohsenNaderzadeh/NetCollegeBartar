package bluedragon.app.netcollegebartar.SqliteManager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.ParcelUuid;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.DataModels.Camps;
import bluedragon.app.netcollegebartar.DataModels.Classes;
import bluedragon.app.netcollegebartar.DataModels.Departemants;
import bluedragon.app.netcollegebartar.DataModels.Departemants_full_DataModel;
import bluedragon.app.netcollegebartar.DataModels.ImageGallery;
import bluedragon.app.netcollegebartar.DataModels.LastNews;
import bluedragon.app.netcollegebartar.DataModels.Library;
import bluedragon.app.netcollegebartar.DataModels.Services;
import bluedragon.app.netcollegebartar.DataModels.Services_full_DataModel;
import bluedragon.app.netcollegebartar.DataModels.Teachers;
import bluedragon.app.netcollegebartar.DataModels.Teachers_full_DataModel;
import bluedragon.app.netcollegebartar.DataModels.User_Comments_Recycler_View_Data_Model;

/**
 * Created by Blue_Dragon on 6/30/2019.
 */
public class SqliteManager extends SQLiteOpenHelper {


    public static final String DBNAME="NetCollegeBartar.db";
    public static final int DBVERSION=1;


    public static final String NEWSTABLENAME="NewsTB";
    public static final String DEPARTEMANTSTBNAME="DepartemantsTB";
    public static final String TEACHERTBNAME="TeachersTB";
    public static final String OTHERMAJORSTB="OtherMajorTB";
    public static final String CLASSESTBNAME="ClassListTB";
    public static final String TEACHERCAMPTB="TeacherCampTB";
    public static final String TEACHERCommentsTB="TeacherCommentsTB";
    public static final String IMAGEGALLERYTB="ImageGalleryTB";
    public static final String LibraryBooksTB="LibraryTB";

    //News Columns Insert KEY
    public static final String KEY_NEWS_ID="NewsID";
    public static final String KEY_NEWS_TITLE="NewsTitle";
    public static final String KEY_NEWS_MAIN_TEXT="NewsMainText";
    public static final String KEY_NEWS_LOGO="NewsLogo";
    public static final String KEY_NEWS_DATE="NewsDate";

    //Departemants_TB Columns Insert KEY
    public static final String KEY_DepartemantsID="DepartemantsID";
    public static final String KEY_DepartemantsName="DepartemantsName";
    public static final String KEY_DepartemantsLogo="DepartemantsLogo";

    //Teachers TB Columns Insert KEY
    public static final String KEY_TEACHERSID="TeachersID";
    public static final String KEY_TEACHERSFULLNAME="Teachersfullname";
    public static final String KEY_TEACHERSDEPARTEMANTSNAME="TeachersDepartemantsname";
    public static final String KEY_TEACHERSPHOTO="Teachersphoto";
    public static final  String KEY_TEACHERSSKILL="TeachersSkill";
    public static final String KEY_TEACHERSRESUME="TeachersResume";
    public static final String KEY_TEACHEREMAIL="TeacherEmail";
    public static final String KEY_TEACHERSDEPARTEMANTID="TeachersDepartemantID";



    //Other OtherMajor TB Column Insert KEY
    public static final String KEY_OTHERMAJORID="OtherMajorID";
    public static final String KEY_OTHERMAJORNAME="OtherMajorname";
    public static final String KEY_OTHERMAJORLOGO="OtherMajorLogo";
    public static final String KEY_OTHERMAJORDESCRIPTION="OtherMajorsDESC";


    //ClassesList TB Column Insert Key
    public static final String KEY_CLASSID="ClassID";
    public static final String KEY_DEPARTEMNATID="DepartemantID";
    public static final String KEY_CLASSNAME="ClassName";
    public static final String KEY_CLASSLOGO="ClassLogo";
    public static final String KEY_CLASSDESC="ClassDescription";



    //Teacher Camp TB Insert KEYS
    public static final String KEY_CAMP_ID="CampID";
    public static final String KEY_CAMP_CLASS_ID="CampClassID";
    public static final String KEY_CAMP_TEACHER_ID="CampTeacherID";
    public static final String KEY_CAMP_NAME="CampName";
    public static final String KEY_CAMP_CAPACITY="CampCapacity";
    public static final String KEY_CAMP_DURATION_VALUE="CampDurationValue";
    public static final String KEY_CAMP_FEE="CampFee";
    public static final String KEY_CAMP_LOGO_URL="CampLogoUrl";
    public static final String KEY_CAMP_Departemant_ID="CampDepartemantID";


    //Teacher Comments TB InsertsKey
    public static final String KEY_USER_COMMENT_ID="UserCommentID";
    public static final String KEY_USER_RATING_SCORE="UserRatingScore";
    public static final String KEY_USER_PROFILE_URL="UserProfileURL";
    public static final String KEY_USER_COMMENTER_NAME="UserCommenterName";
    public static final String KEY_COMMENT_TEXT="UserCommentText";
    public static final String KEY_COMMENT_SENDER_ID="CommentSenderID";
    public static final String KEY_COMMENT_SEND_DATE="CommentSendDate";
    public static final String KEY_COMMENT_SEND_TIME="CommentSendTime";
    public static final String KEY_TEACHER_ID="CommentTeacherID";


    //Image Gallery Insert Keys
    public static final String KEY_IMAGE_ID="ImageID";
    public static final String KEY_IMAGE_LOGO_URL="ImageLogoUrl";
    public static final String KEY_IMAGE_CAPTION="ImageCaption";


    //Library Books Insert Keys
    public static final String KEY_BOOK_ID="Bookid";
    public static final String KEY_BOOK_CATEGORY="Bookcategory";
    public static final String KEY_BOOK_NAME="BookName";
    public static final String KEY_BOOK_IMAGE="BookImage";
    public static final String KEY_BOOK_DOWNLOAD_LINK="BookDownloadLink";
    public static final String KEY_BOOK_DESC="BooKDescription";



    private Context context;


    public SqliteManager(Context context) {
            super(context, DBNAME,null, DBVERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+NEWSTABLENAME+"(NewsID INTEGER PRIMARY KEY AUTOINCREMENT,NewsTitle Text,NewsMainText Text,NewsLogo Text,NewsDate Text )");
        db.execSQL("create table "+DEPARTEMANTSTBNAME+" (DepartemantsID INTEGER PRIMARY KEY AUTOINCREMENT,DepartemantsName Text,DepartemantsLogo Text)");
        db.execSQL("create table "+TEACHERTBNAME+ " (TeachersID INTEGER PRIMARY KEY AUTOINCREMENT, Teachersfullname Text,TeachersDepartemantsname Text,Teachersphoto Text,TeachersSkill Text,TeachersResume Text,TeacherEmail Text,TeachersDepartemantID INTEGER)");
        db.execSQL("create table "+OTHERMAJORSTB+ " (OtherMajorID INTEGER PRIMARY KEY AUTOINCREMENT,OtherMajorname Text,OtherMajorLogo Text,OtherMajorsDESC Text)");
        db.execSQL("create table "+CLASSESTBNAME+" (ClassID INTEGER PRIMARY KEY AUTOINCREMENT,DepartemantID INTEGER , ClassName Text,ClassLogo Text,ClassDescription Text)");
        db.execSQL("create table "+TEACHERCAMPTB+" (CampID INTEGER PRIMARY KEY AUTOINCREMENT,CampClassID INTEGER,CampTeacherID INTEGER,CampName TEXT,CampCapacity INTEGER,CampDurationValue INTEGER,CampFee INTEGER,CampLogoUrl TEXT,CampDepartemantID INTEGER)");
        db.execSQL("create table "+TEACHERCommentsTB+" (UserCommentID INTEGER PRIMARY KEY AUTOINCREMENT,UserRatingScore FLOAT,UserProfileURL TEXT,UserCommenterName TEXT,UserCommentText Text,CommentSenderID INTEGER,CommentSendDate TEXT,CommentSendTime TEXT,CommentTeacherID INTEGER)");
        db.execSQL("create table "+IMAGEGALLERYTB+" (ImageID INTEGER PRIMARY KEY AUTOINCREMENT,ImageLogoUrl Text,ImageCaption Text)");
        db.execSQL("create table "+LibraryBooksTB+ "(Bookid INTEGER PRIMARY KEY AUTOINCREMENT,Bookcategory INTEGER,BookName TEXT,BookImage TEXT,BookDownloadLink TEXT,BooKDescription Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+NEWSTABLENAME);
        db.execSQL("DROP TABLE IF EXISTS "+DEPARTEMANTSTBNAME);
        db.execSQL("DROP TABLE IF EXISTS "+TEACHERTBNAME);
        db.execSQL("DROP TABLE IF EXISTS "+OTHERMAJORSTB);
        db.execSQL("DROP TABLE IF EXISTS "+CLASSESTBNAME);
        db.execSQL("DROP TABLE IF EXISTS "+TEACHERCAMPTB);
        db.execSQL("DROP TABLE IF EXISTS "+TEACHERCommentsTB);
        db.execSQL("DROP TABLE IF EXISTS "+IMAGEGALLERYTB);
        db.execSQL("DROP TABLE IF EXISTS "+LibraryBooksTB);
        onCreate(db);
    }


    public Boolean checkBookIDisRepativeOrNot(int BookID)
    {
        SQLiteDatabase LibraryBooksisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor LibraryBooksisRepativeOrNotCursor=LibraryBooksisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+LibraryBooksTB + " where Bookid = '"+BookID+ "'" ,null );
        if(LibraryBooksisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkLibraryBooksTBAvailbilty()
    {
        SQLiteDatabase LibraryBooksisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor LibraryBooksisRepativeOrNotCursor=LibraryBooksisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+LibraryBooksTB  ,null );
        if(LibraryBooksisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public ArrayList<Library> getLibraryBooksTBListOffline(int CategoryID)
    {
        ArrayList<Library>GalleryImageistOffline=new ArrayList<>();
        SQLiteDatabase LibraryBooksTBOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor LibraryBooksTBOfflineCursor=LibraryBooksTBOfflineSqliteDataBase.rawQuery("select * from " + LibraryBooksTB+ " where Bookcategory = '"+CategoryID+"'",null);
        LibraryBooksTBOfflineCursor.moveToFirst();
        if (LibraryBooksTBOfflineCursor.getCount()>0)
        {
            while (!LibraryBooksTBOfflineCursor.isAfterLast())
            {
                Library LibraryBooksListOfflineDataModel=new Library();
                LibraryBooksListOfflineDataModel.setBook_id(LibraryBooksTBOfflineCursor.getInt(0));
                LibraryBooksListOfflineDataModel.setBook_category(LibraryBooksTBOfflineCursor.getInt(1));
                LibraryBooksListOfflineDataModel.setBookName(LibraryBooksTBOfflineCursor.getString(2));
                LibraryBooksListOfflineDataModel.setBookImage(LibraryBooksTBOfflineCursor.getString(3));
                LibraryBooksListOfflineDataModel.setBookDownloadLink(LibraryBooksTBOfflineCursor.getString(4));
                LibraryBooksListOfflineDataModel.setBooKDescription(LibraryBooksTBOfflineCursor.getString(5));
                GalleryImageistOffline.add(LibraryBooksListOfflineDataModel);
                LibraryBooksTBOfflineCursor.moveToNext();
            }
        }
        return GalleryImageistOffline;
    }



























    public Boolean ImageIDisRepativeOrNot(int ImageID)
    {
        SQLiteDatabase ImageGalleryImageisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor ImageGalleryImageisRepativeOrNotCursor=ImageGalleryImageisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+IMAGEGALLERYTB + " where ImageID = '"+ImageID+ "'" ,null );
        if(ImageGalleryImageisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkImageGalleryTBAvailbilty()
    {
        SQLiteDatabase checkImageGalleryisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkImageGalleryisRepativeOrNotCursor=checkImageGalleryisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+IMAGEGALLERYTB  ,null );
        if(checkImageGalleryisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<ImageGallery> getGalleryImageListOffline()
    {
        ArrayList<ImageGallery>GalleryImageistOffline=new ArrayList<>();
        SQLiteDatabase TeacherCommentListOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor GalleryImageListOfflineCursor=TeacherCommentListOfflineSqliteDataBase.rawQuery("select * from " + IMAGEGALLERYTB,null);
        GalleryImageListOfflineCursor.moveToFirst();
        if (GalleryImageListOfflineCursor.getCount()>0)
        {
            while (!GalleryImageListOfflineCursor.isAfterLast())
            {
                ImageGallery GalleryImageListOfflineDataModel=new ImageGallery();
                GalleryImageListOfflineDataModel.setImageID(GalleryImageListOfflineCursor.getInt(0));
                GalleryImageListOfflineDataModel.setImageLogoUrl(GalleryImageListOfflineCursor.getString(1));
                GalleryImageListOfflineDataModel.setImageCaption(GalleryImageListOfflineCursor.getString(2));
                GalleryImageistOffline.add(GalleryImageListOfflineDataModel);
                GalleryImageListOfflineCursor.moveToNext();
            }
        }
        return GalleryImageistOffline;
    }

    public Boolean TeacherCommentsisRepativeOrNot(int UserCommentID)
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+TEACHERCommentsTB + " where UserCommentID = '"+UserCommentID+ "'" ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkTeacherCommentsTBAvailbilty()
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+TEACHERCommentsTB  ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public ArrayList<User_Comments_Recycler_View_Data_Model> getTeacherCommentListOffline(int TeacherID)
    {
        ArrayList<User_Comments_Recycler_View_Data_Model>TeacherCommentsListOffline=new ArrayList<>();
        SQLiteDatabase TeacherCommentListOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor TeacherCampsListOfflineCursor=TeacherCommentListOfflineSqliteDataBase.rawQuery("select * from " + TEACHERCommentsTB+ " where CommentTeacherID = '"+TeacherID+"'",null);
        TeacherCampsListOfflineCursor.moveToFirst();
        if (TeacherCampsListOfflineCursor.getCount()>0)
        {
            while (!TeacherCampsListOfflineCursor.isAfterLast())
            {
                User_Comments_Recycler_View_Data_Model TeacherCommentListOfflineDataModel=new User_Comments_Recycler_View_Data_Model();
                TeacherCommentListOfflineDataModel.setUser_Comment_ID(TeacherCampsListOfflineCursor.getInt(0));
                TeacherCommentListOfflineDataModel.setUser_Rating_Score(TeacherCampsListOfflineCursor.getFloat(1));
                TeacherCommentListOfflineDataModel.setUser_Profile_URL(TeacherCampsListOfflineCursor.getString(2));
                TeacherCommentListOfflineDataModel.setUser_Commenter_Name(TeacherCampsListOfflineCursor.getString(3));
                TeacherCommentListOfflineDataModel.setUser_Comment_Text(TeacherCampsListOfflineCursor.getString(4));
                TeacherCommentListOfflineDataModel.setCommentSenderID(TeacherCampsListOfflineCursor.getInt(5));
                TeacherCommentListOfflineDataModel.setCommentSendDate(TeacherCampsListOfflineCursor.getString(6));
                TeacherCommentListOfflineDataModel.setCommentSendTime(TeacherCampsListOfflineCursor.getString(7));
                TeacherCommentListOfflineDataModel.setTeacherID(TeacherCampsListOfflineCursor.getInt(8));
                TeacherCommentsListOffline.add(TeacherCommentListOfflineDataModel);
                TeacherCampsListOfflineCursor.moveToNext();
            }
        }
        return TeacherCommentsListOffline;
    }

    public void insertTeacherCommentList(ArrayList<User_Comments_Recycler_View_Data_Model>TeacherCommentList)
    {
        TeacherCommentsAsyncTask teacherscommentslistAsyncTask=new TeacherCommentsAsyncTask(context,TeacherCommentList,this);
        teacherscommentslistAsyncTask.execute();
    }











































    public Boolean TeacherClassesisRepativeOrNot(int TeacherClassID)
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+TEACHERCAMPTB + " where CampID = '"+TeacherClassID+ "'" ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkTeachersClassesTBAvailbilty()
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+TEACHERCAMPTB  ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public ArrayList<Camps> getTeacherCampsListOffline(int TeacherID)
    {
        ArrayList<Camps>TeacherCampsListOffline=new ArrayList<>();
        SQLiteDatabase TeacherCampsListOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor TeacherCampsListOfflineCursor=TeacherCampsListOfflineSqliteDataBase.rawQuery("select * from " + TEACHERCAMPTB+ " where CampTeacherID = '"+TeacherID+"'",null);
        TeacherCampsListOfflineCursor.moveToFirst();
        if (TeacherCampsListOfflineCursor.getCount()>0)
        {
            while (!TeacherCampsListOfflineCursor.isAfterLast())
            {
                Camps TeacherCampsListOfflineDataModel=new Camps();
                TeacherCampsListOfflineDataModel.setCamp_Id(TeacherCampsListOfflineCursor.getInt(0));
                TeacherCampsListOfflineDataModel.setCamp_ClassID(TeacherCampsListOfflineCursor.getInt(1));
                TeacherCampsListOfflineDataModel.setCamp_Teacher_ID(TeacherCampsListOfflineCursor.getInt(2));
                TeacherCampsListOfflineDataModel.setCamp_Name(TeacherCampsListOfflineCursor.getString(3));
                TeacherCampsListOfflineDataModel.setCampCapacity(TeacherCampsListOfflineCursor.getInt(4));
                TeacherCampsListOfflineDataModel.setCamp_Duration_Value(Byte.parseByte(String.valueOf(TeacherCampsListOfflineCursor.getInt(5))));
                TeacherCampsListOfflineDataModel.setCamp_fee(TeacherCampsListOfflineCursor.getInt(6));
                TeacherCampsListOfflineDataModel.setCamp_Logo_Url(TeacherCampsListOfflineCursor.getString(7));
                TeacherCampsListOfflineDataModel.setCampdDepartemantsId(TeacherCampsListOfflineCursor.getInt(8));
                TeacherCampsListOffline.add(TeacherCampsListOfflineDataModel);
                TeacherCampsListOfflineCursor.moveToNext();
            }
        }
        return TeacherCampsListOffline;
    }

    public void insertTeacherClassesList(ArrayList<Camps>TeacherClassesList)
    {
        TeacherCampsAsyncTask teacherscampslistAsyncTask=new TeacherCampsAsyncTask(context,TeacherClassesList,this);
        teacherscampslistAsyncTask.execute();
    }
    public Boolean ClassesisRepativeOrNot(int ClassID)
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+CLASSESTBNAME + " where ClassID = '"+ClassID+ "'" ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkOtherMajorisRepativeOrNot(int OtherMajorID)
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+OTHERMAJORSTB + " where OtherMajorID = '"+OtherMajorID+ "'" ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkNewsisRepativeOrNot(int NewsID)
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+NEWSTABLENAME + " where NewsID = '"+NewsID+ "'" ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkTeacherisRepativeOrNot(int TeacherID)
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+TEACHERTBNAME + " where TeachersID = '"+TeacherID+ "'" ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkDepatemantsisRepativeOrNot(int DepartemantsID)
    {
        SQLiteDatabase checkDepatemantsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkDepatemantsisRepativeOrNotCursor=checkDepatemantsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+DEPARTEMANTSTBNAME + " where DepartemantsID = '"+DepartemantsID+ "'" ,null );
        if(checkDepatemantsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkClassesTBAvailbilty()
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+CLASSESTBNAME  ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkDepatemantsTBAvailbilty()
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+DEPARTEMANTSTBNAME  ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkTeachersTBAvailbilty()
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+TEACHERTBNAME  ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkOtherMajorTBAvailbilty()
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+OTHERMAJORSTB  ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkNewsTBAvailbilty()
    {
        SQLiteDatabase checkNewsisRepativeOrNotSqliteDataBaseManager=this.getReadableDatabase();
        Cursor checkNewsisRepativeOrNotCursor=checkNewsisRepativeOrNotSqliteDataBaseManager.rawQuery("select * from "+NEWSTABLENAME  ,null );
        if(checkNewsisRepativeOrNotCursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void insertNews(ArrayList<LastNews>NewsList)
    {
        LastNewsAsyncTask lastNewsAsyncTask=new LastNewsAsyncTask(context,NewsList,this);
        lastNewsAsyncTask.execute();
    }
    public void insertDepartemants(ArrayList<Departemants>DepartemantsList)
    {
        DepartemantsAsyncTask departemantsAsyncTask=new DepartemantsAsyncTask(context,DepartemantsList,this);
        departemantsAsyncTask.execute();
    }
    public void insertfullDepartemants(ArrayList<Departemants_full_DataModel>fullDepartemantsList)
    {
        FullDepartemantsAsyncTask departemantsAsyncTask=new FullDepartemantsAsyncTask(context,fullDepartemantsList,this);
        departemantsAsyncTask.execute();
    }
    public void insertTeachers(ArrayList<Teachers>TeachersList)
    {
        TeachersAsyncTask teachersAsyncTask=new TeachersAsyncTask(context,TeachersList,this);
        teachersAsyncTask.execute();
    }
    public void insertfullTeachers(ArrayList<Teachers_full_DataModel>TeachersList)
    {
        TeachersFullAsyncTask teachersAsyncTask=new TeachersFullAsyncTask(context,TeachersList,this);
        teachersAsyncTask.execute();
    }
    public void insertOtherMajor(ArrayList<Services>OtherMajorList)
    {
        OtherMajorsAsyncTask teachersAsyncTask=new OtherMajorsAsyncTask(context,OtherMajorList,this);
        teachersAsyncTask.execute();
    }
    public void insertFullOtherMajor(ArrayList<Services_full_DataModel>OtherMajorList)
    {
        OtherMajorFullAsyncTask teachersAsyncTask=new OtherMajorFullAsyncTask(context,OtherMajorList,this);
        teachersAsyncTask.execute();
    }
    public void insertClassesList(ArrayList<Classes>ClassesList)
    {
        ClassesAsyncTask teachersAsyncTask=new ClassesAsyncTask(context,ClassesList,this);
        teachersAsyncTask.execute();
    }
    public void insertBookList(ArrayList<Library>BooksList)
    {
        LibraryAsyncTask libraryAsyncTask=new LibraryAsyncTask(context,BooksList,this);
        libraryAsyncTask.execute();
    }
    public void insertGalleryImage(ArrayList<ImageGallery>ImageGalleryList)
    {
        ImageGalleryAsyncTask imageGalleryAsyncTask=new ImageGalleryAsyncTask(context,ImageGalleryList,this);
        imageGalleryAsyncTask.execute();
    }


    public ArrayList<LastNews> getLastNewsListOffline()
    {
        ArrayList<LastNews>LastNewsListOffline=new ArrayList<>();
        SQLiteDatabase LastNewsListOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor LastNewsListOfflineCursor=LastNewsListOfflineSqliteDataBase.rawQuery("select * from " + NEWSTABLENAME,null);
        LastNewsListOfflineCursor.moveToFirst();
        if (LastNewsListOfflineCursor.getCount()>0)
        {
            while (!LastNewsListOfflineCursor.isAfterLast())
            {
                LastNews lastNewsModelOffline=new LastNews();
                lastNewsModelOffline.setLastNewsID(LastNewsListOfflineCursor.getInt(0));
                lastNewsModelOffline.setLastNewsTitle(LastNewsListOfflineCursor.getString(1));
                lastNewsModelOffline.setLastNewsText(LastNewsListOfflineCursor.getString(2));
                lastNewsModelOffline.setLastNewsLogo(LastNewsListOfflineCursor.getString(3));
                lastNewsModelOffline.setLastNewsDate(LastNewsListOfflineCursor.getString(4));
                LastNewsListOffline.add(lastNewsModelOffline);
                LastNewsListOfflineCursor.moveToNext();
            }
        }
        return LastNewsListOffline;
    }
    public ArrayList<Departemants> getDepartemantsListForMainPageOffline()
    {
        ArrayList<Departemants>DepartemantsListOffline=new ArrayList<>();
        SQLiteDatabase DepartemantsOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor DepartemantsOfflineCursor=DepartemantsOfflineSqliteDataBase.rawQuery("select * from " + DEPARTEMANTSTBNAME,null);
        DepartemantsOfflineCursor.moveToFirst();
        if (DepartemantsOfflineCursor.getCount()>0)
        {

            while (!DepartemantsOfflineCursor.isAfterLast())
                {
                Departemants DepartemantsOffline=new Departemants();
                DepartemantsOffline.setId(DepartemantsOfflineCursor.getInt(0));
                DepartemantsOffline.setDepartemant_Title(DepartemantsOfflineCursor.getString(1));
                DepartemantsOffline.setDepartemants_Image(DepartemantsOfflineCursor.getString(2));
                DepartemantsListOffline.add(DepartemantsOffline);
                DepartemantsOfflineCursor.moveToNext();

            }
        }
        return DepartemantsListOffline;
    }
    public ArrayList<Departemants_full_DataModel> getDepartemantsListForFUllPageOffline()
    {
        ArrayList<Departemants_full_DataModel>DepartemantsListOffline=new ArrayList<>();
        SQLiteDatabase DepartemantsOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor DepartemantsOfflineCursor=DepartemantsOfflineSqliteDataBase.rawQuery("select * from " + DEPARTEMANTSTBNAME,null);
        DepartemantsOfflineCursor.moveToFirst();
        if (DepartemantsOfflineCursor.getCount()>0)
        {

            while (!DepartemantsOfflineCursor.isAfterLast())
            {
                Departemants_full_DataModel DepartemantsOffline=new Departemants_full_DataModel();
                DepartemantsOffline.setId(DepartemantsOfflineCursor.getInt(0));
                DepartemantsOffline.setDepartemant_Title(DepartemantsOfflineCursor.getString(1));
                DepartemantsOffline.setDepartemants_Image(DepartemantsOfflineCursor.getString(2));
                DepartemantsListOffline.add(DepartemantsOffline);
                DepartemantsOfflineCursor.moveToNext();

            }
        }
        return DepartemantsListOffline;
    }

    public ArrayList<Teachers> getTeachersListForMainPage()
    {
        ArrayList<Teachers>TeachersListOffline=new ArrayList<>();
        SQLiteDatabase TeachersOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor TeachersOfflineCursor=TeachersOfflineSqliteDataBase.rawQuery("select * from " + TEACHERTBNAME,null);
        TeachersOfflineCursor.moveToFirst();
        if (TeachersOfflineCursor.getCount()>0)
        {

            while (!TeachersOfflineCursor.isAfterLast())
            {
                Teachers TeachersListOfflineDataModel=new Teachers();
                TeachersListOfflineDataModel.setTeacher_ID(TeachersOfflineCursor.getInt(0));
                TeachersListOfflineDataModel.setTeachers_full_name(TeachersOfflineCursor.getString(1));
                TeachersListOfflineDataModel.setTeachers_Departemants_name(TeachersOfflineCursor.getString(2));
                TeachersListOfflineDataModel.setTeachers_photo(TeachersOfflineCursor.getString(3));
                TeachersListOfflineDataModel.setTeachers_Skill(TeachersOfflineCursor.getString(4));
                TeachersListOfflineDataModel.setTeachers_Resume(TeachersOfflineCursor.getString(5));
                TeachersListOfflineDataModel.setTeacher_Email(TeachersOfflineCursor.getString(6));
                TeachersListOfflineDataModel.setTeachers_Departemant_ID(TeachersOfflineCursor.getInt(7));
                TeachersListOffline.add(TeachersListOfflineDataModel);
                TeachersOfflineCursor.moveToNext();

            }
        }
        return TeachersListOffline;
    }
    public ArrayList<Teachers_full_DataModel> getAllTeachersList()
    {
        ArrayList<Teachers_full_DataModel>TeachersListOffline=new ArrayList<>();
        SQLiteDatabase TeachersOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor TeachersOfflineCursor=TeachersOfflineSqliteDataBase.rawQuery("select * from " + TEACHERTBNAME,null);
        TeachersOfflineCursor.moveToFirst();
        if (TeachersOfflineCursor.getCount()>0)
        {

            while (!TeachersOfflineCursor.isAfterLast())
            {
                Teachers_full_DataModel TeachersListOfflineDataModel=new Teachers_full_DataModel();
                TeachersListOfflineDataModel.setTeacher_ID(TeachersOfflineCursor.getInt(0));
                TeachersListOfflineDataModel.setTeachers_full_name(TeachersOfflineCursor.getString(1));
                TeachersListOfflineDataModel.setTeachers_Departemants_name(TeachersOfflineCursor.getString(2));
                TeachersListOfflineDataModel.setTeachers_photo(TeachersOfflineCursor.getString(3));
                TeachersListOfflineDataModel.setTeachers_Skill(TeachersOfflineCursor.getString(4));
                TeachersListOfflineDataModel.setTeachers_Resume(TeachersOfflineCursor.getString(5));
                TeachersListOfflineDataModel.setTeacher_Email(TeachersOfflineCursor.getString(6));
                TeachersListOfflineDataModel.setTeachers_Departemant_ID(TeachersOfflineCursor.getInt(7));
                TeachersListOffline.add(TeachersListOfflineDataModel);
                TeachersOfflineCursor.moveToNext();

            }
        }
        return TeachersListOffline;
    }
    public ArrayList<Services> getOtherMajorListOfflineForMainPage()
    {
        ArrayList<Services>OtherMajorListOffline=new ArrayList<>();
        SQLiteDatabase OtherMajorOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor OtherMajorListOfflineCursor=OtherMajorOfflineSqliteDataBase.rawQuery("select * from " + OTHERMAJORSTB,null);
        OtherMajorListOfflineCursor.moveToFirst();
        if (OtherMajorListOfflineCursor.getCount()>0)
        {
            while (!OtherMajorListOfflineCursor.isAfterLast())
            {
                Services OtherMajorModelOffline=new Services();
                OtherMajorModelOffline.setId(OtherMajorListOfflineCursor.getInt(0));
                OtherMajorModelOffline.setService_Name(OtherMajorListOfflineCursor.getString(1));
                OtherMajorModelOffline.setService_Logo(OtherMajorListOfflineCursor.getString(2));
                OtherMajorModelOffline.setServices_Description(OtherMajorListOfflineCursor.getString(3));
                OtherMajorListOffline.add(OtherMajorModelOffline);
                OtherMajorListOfflineCursor.moveToNext();
            }
        }
        return OtherMajorListOffline;
    }
    public ArrayList<Services_full_DataModel> getAllOtherMajorListOffline()
    {
        ArrayList<Services_full_DataModel>OtherMajorListOffline=new ArrayList<>();
        SQLiteDatabase OtherMajorOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor OtherMajorListOfflineCursor=OtherMajorOfflineSqliteDataBase.rawQuery("select * from " + OTHERMAJORSTB,null);
        OtherMajorListOfflineCursor.moveToFirst();
        if (OtherMajorListOfflineCursor.getCount()>0)
        {
            while (!OtherMajorListOfflineCursor.isAfterLast())
            {
                Services_full_DataModel OtherMajorModelOffline=new Services_full_DataModel();
                OtherMajorModelOffline.setId(OtherMajorListOfflineCursor.getInt(0));
                OtherMajorModelOffline.setService_Name(OtherMajorListOfflineCursor.getString(1));
                OtherMajorModelOffline.setService_Logo(OtherMajorListOfflineCursor.getString(2));
                OtherMajorModelOffline.setServices_Description(OtherMajorListOfflineCursor.getString(3));
                OtherMajorListOffline.add(OtherMajorModelOffline);
                OtherMajorListOfflineCursor.moveToNext();
            }
        }
        return OtherMajorListOffline;
    }
    public ArrayList<Classes> getAllClassesListOffline(int DepartemantsID)
    {
        ArrayList<Classes>OtherMajorListOffline=new ArrayList<>();
        SQLiteDatabase OtherMajorOfflineSqliteDataBase=this.getReadableDatabase();
        Cursor OtherMajorListOfflineCursor=OtherMajorOfflineSqliteDataBase.rawQuery("select * from " + CLASSESTBNAME+ " where DepartemantID= '"+DepartemantsID+"'",null);
        OtherMajorListOfflineCursor.moveToFirst();
        if (OtherMajorListOfflineCursor.getCount()>0)
        {
            while (!OtherMajorListOfflineCursor.isAfterLast())
            {
                Classes ClassesListOffline=new Classes();
                ClassesListOffline.setClass_id(OtherMajorListOfflineCursor.getInt(0));
                ClassesListOffline.setDepartemant_id(OtherMajorListOfflineCursor.getInt(1));
                ClassesListOffline.setClass_Name(OtherMajorListOfflineCursor.getString(2));
                ClassesListOffline.setClass_Logo(OtherMajorListOfflineCursor.getString(3));
                ClassesListOffline.setClass_Description(OtherMajorListOfflineCursor.getString(4));
                OtherMajorListOffline.add(ClassesListOffline);
                OtherMajorListOfflineCursor.moveToNext();
            }
        }
        return OtherMajorListOffline;
    }





}
