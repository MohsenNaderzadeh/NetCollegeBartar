package bluedragon.app.netcollegebartar.Views.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bluedragon.app.netcollegebartar.R;

public class ImageGalleryFullActivity extends AppCompatActivity {

    int ImageID;
    String Imageurl, ImageCaption;
    Toolbar ImageGallery_Tool_Bar;
    TextView image_gallery_full_Toolbar_TxtView_Title1,ImageGallery_Title;
    ImageView ImageGallery_Logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery_full);
        setViews();
        settoolbar();
        getintentdata();
        Picasso.with(getApplicationContext()).load(Imageurl).placeholder(R.drawable.netcollege_place_holder).error(R.drawable.netcollege_place_holder).into(ImageGallery_Logo);
        ImageGallery_Title.setText(ImageCaption);
    }

    private void setViews()
    {
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        ImageGallery_Tool_Bar=(Toolbar)findViewById(R.id.ImageGallery_Tool_Bar);
        ImageGallery_Logo=(ImageView)findViewById(R.id.ImageGallery_Logo);
        image_gallery_full_Toolbar_TxtView_Title1=(TextView) ImageGallery_Tool_Bar.findViewById(R.id.image_gallery_full_Toolbar_TxtView_Title1);
        ImageGallery_Title=(TextView)findViewById(R.id.ImageGallery_Title);

        image_gallery_full_Toolbar_TxtView_Title1.setTypeface(typeface);
        image_gallery_full_Toolbar_TxtView_Title1.setText("گالری تصویر");
        ImageGallery_Title.setTypeface(typeface);

    }
    private void getintentdata()
    {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            ImageID=mBundle.getInt("ImageID");
            Imageurl=mBundle.getString("Imageurl");
            ImageCaption=mBundle.getString("ImageCaption");

        }
    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(ImageGallery_Tool_Bar);
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
}
