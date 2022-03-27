package bluedragon.app.netcollegebartar.ImageLoadingService;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import bluedragon.app.netcollegebartar.R;
import ss.com.bannerslider.ImageLoadingService;

/**
 * Created by Blue_Dragon on 11/18/2018.
 */
public class ImageLoadingService1 implements ImageLoadingService {

    public Context context;

    public ImageLoadingService1(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Picasso.with(context).load(url).placeholder(R.drawable.netcollege_place_holder).into(imageView);
    }

    @Override
    public void loadImage(int resource, ImageView imageView) {
        Picasso.with(context).load(resource).placeholder(R.drawable.netcollege_place_holder).into(imageView);
    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
        Picasso.with(context).load(url).placeholder(placeHolder).error(errorDrawable).into(imageView);
    }
}

