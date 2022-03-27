package bluedragon.app.netcollegebartar.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bluedragon.app.netcollegebartar.R;


public class SplashScreen extends AppCompatActivity {



    RelativeLayout rootview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            Intent intent=new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);

            finish();
            }
        },3000);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(!internetConnection())
        {
            Snackbar snackbar = Snackbar.make(SplashScreen.this.getWindow().getDecorView(),"شما به اینترنت متصل نیستید !", Snackbar.LENGTH_LONG);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                TextView view1 = (TextView)snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                view1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
            snackbar.show();
        }

    }
    public boolean internetConnection (){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifi != null && wifi.isConnected()){
            return true;
        }
        NetworkInfo data = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (data != null && data.isConnected()){
            return true;
        }
        NetworkInfo active = cm.getActiveNetworkInfo();
        if (active != null && active.isConnected()){
            return true;
        }

        return  false;
    }



}
