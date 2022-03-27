package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;

public class LibraryAcitiviy extends AppCompatActivity implements View.OnClickListener {

    Typeface typeface;
    Toolbar LibraryToolBar;
    TextView LibraryToolBarTitle;
    Button ProgrammingsBooks,Graphic,WebDesign,Network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_acitiviy);
        setviews();
        settoolbar();

    }
    public void setviews()
    {
        typeface= Typeface.createFromAsset(getAssets(),"fonts/iransansdn.ttf");
        LibraryToolBar = (Toolbar) findViewById(R.id.LibraryToolBar);
        LibraryToolBarTitle=LibraryToolBar.findViewById(R.id.LibraryToolBarTitle);
        ProgrammingsBooks=(Button)findViewById(R.id.ProgrammingsBooks);
        Graphic=(Button)findViewById(R.id.Graphic);
        WebDesign=(Button)findViewById(R.id.WebDesign);
        Network=(Button)findViewById(R.id.Network);
        LibraryToolBarTitle.setTypeface(typeface);
        ProgrammingsBooks.setTypeface(typeface);
        Graphic.setTypeface(typeface);
        WebDesign.setTypeface(typeface);
        Network.setTypeface(typeface);



    }
    public void  settoolbar()
    {
        //Set Activity Toolbar
        setSupportActionBar(LibraryToolBar);
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
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ProgrammingsBooks:
                Intent intent=new Intent(LibraryAcitiviy.this,BookListActivity.class);
                intent.putExtra("BookCategory",1);
                intent.putExtra("BookCategoryName",ProgrammingsBooks.getText().toString());
                startActivity(intent);
                break;
            case R.id.Graphic:
                Intent intent1=new Intent(LibraryAcitiviy.this,BookListActivity.class);
                intent1.putExtra("BookCategory",2);
                intent1.putExtra("BookCategoryName",Graphic.getText().toString());
                startActivity(intent1);
                break;
            case R.id.WebDesign:
                Intent intent2=new Intent(LibraryAcitiviy.this,BookListActivity.class);
                intent2.putExtra("BookCategory",3);
                intent2.putExtra("BookCategoryName",WebDesign.getText().toString());
                startActivity(intent2);
                break;
            case R.id.Network:
                Intent intent3=new Intent(LibraryAcitiviy.this,BookListActivity.class);
                intent3.putExtra("BookCategory",4);
                intent3.putExtra("BookCategoryName",Network.getText().toString());
                startActivity(intent3);
                break;
        }
    }
}
