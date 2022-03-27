package bluedragon.app.netcollegebartar.Views.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.Adapters.ImageGalleryRecyclerAdapter;
import bluedragon.app.netcollegebartar.Adapters.LibraryListAdapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.ImageGallery;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;

public class ImageGalleryActivity extends AppCompatActivity  implements ApiService.OnImageList{

    Typeface typeface;
    Toolbar ImageGalleryToolBar;
    TextView ImageGalleryToolbarTitle;
    RecyclerView ImageGallery;
    ImageGalleryRecyclerAdapter imageGalleryRecyclerAdapter;
    TextView image_gallery_error_txt;
    ProgressBar image_gallery_progressbar;
    ApiService apiService;
    SqliteManager sqliteManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        setviews();
        settoolbar();
        if(apiService.internetConnection())
        {
            apiService.LoadImageGalleryImAGE(AppConfig.URL_IMAGE_GALLERY,ImageGalleryActivity.this);

        }
        else
        {
            if(sqliteManager.checkImageGalleryTBAvailbilty())
            {
                image_gallery_progressbar.setVisibility(View.GONE);
                ImageGallery.setVisibility(View.VISIBLE);
                ImageGallery.setHasFixedSize(true);
                ImageGallery.setLayoutManager(new GridLayoutManager(ImageGalleryActivity.this,2, LinearLayoutManager.VERTICAL,false));
                imageGalleryRecyclerAdapter=new ImageGalleryRecyclerAdapter(getApplicationContext(),sqliteManager.getGalleryImageListOffline());
                ImageGallery.setAdapter(imageGalleryRecyclerAdapter);
            }
            else
            {
                image_gallery_progressbar.setVisibility(View.GONE);
                image_gallery_error_txt.setVisibility(View.VISIBLE);
            }
        }
    }
    public void setviews()
    {
        typeface= Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        ImageGalleryToolBar = (Toolbar) findViewById(R.id.ImageGalleryToolbar);
        ImageGalleryToolbarTitle=ImageGalleryToolBar.findViewById(R.id.ImageGalleryToolbarTitle);
        ImageGallery=(RecyclerView)findViewById(R.id.ImageGalleryRecycler);
        ImageGalleryToolbarTitle.setText("گالری تصاویر");
        ImageGalleryToolbarTitle.setTypeface(typeface);
        apiService=new ApiService(getApplicationContext());
        image_gallery_error_txt=(TextView)findViewById(R.id.image_gallery_error_txt);
        image_gallery_progressbar=(ProgressBar)findViewById(R.id.image_gallery_progressbar);
        sqliteManager=new SqliteManager(getApplicationContext());
    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(ImageGalleryToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.class_details_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_backbtn_Class_details_activity)
        {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnImageListRecieved(ArrayList<bluedragon.app.netcollegebartar.DataModels.ImageGallery> ImageList) {

        if(!ImageList.isEmpty())
        {
            sqliteManager.insertGalleryImage(ImageList);
            image_gallery_progressbar.setVisibility(View.GONE);
            ImageGallery.setVisibility(View.VISIBLE);
            ImageGallery.setHasFixedSize(true);
            ImageGallery.setLayoutManager(new GridLayoutManager(ImageGalleryActivity.this,2, LinearLayoutManager.VERTICAL,false));
            imageGalleryRecyclerAdapter=new ImageGalleryRecyclerAdapter(getApplicationContext(),ImageList);
            ImageGallery.setAdapter(imageGalleryRecyclerAdapter);
        }
        else
        {
            image_gallery_progressbar.setVisibility(View.GONE);
            image_gallery_error_txt.setVisibility(View.VISIBLE);
        }

    }
}
