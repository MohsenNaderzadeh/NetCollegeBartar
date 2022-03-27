package bluedragon.app.netcollegebartar.Views.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import bluedragon.app.netcollegebartar.R;

public class ContactUsActivity extends AppCompatActivity {

    Toolbar ContactUs;
    Typeface typeface;
    TextView Contact_with_us_txt,PhoneNumbers_Title,mail_address,address_title,AddressValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        setviews();
        settoolbar();
    }
    public void setviews()
    {
        ContactUs=(Toolbar)findViewById(R.id.Contact_with_Usttolbar) ;
        Contact_with_us_txt=ContactUs.findViewById(R.id.Contact_with_us_txt);
        PhoneNumbers_Title=(TextView)findViewById(R.id.PhoneNumbers_Title);
        mail_address=(TextView)findViewById(R.id.mail_address);
        address_title=(TextView)findViewById(R.id.address_title);
        AddressValue=(TextView)findViewById(R.id.AddressValue);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/iransansdn.ttf");
        Contact_with_us_txt.setTypeface(typeface);
        address_title.setTypeface(typeface);
        mail_address.setTypeface(typeface);
        PhoneNumbers_Title.setTypeface(typeface);
        AddressValue.setTypeface(typeface);


    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(ContactUs);
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
