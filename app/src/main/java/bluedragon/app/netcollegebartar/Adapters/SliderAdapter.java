package bluedragon.app.netcollegebartar.Adapters;

import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

/**
 * Created by Blue_Dragon on 11/18/2018.
 */
public class SliderAdapter extends ss.com.bannerslider.adapters.SliderAdapter {

    @Override
    public int getItemCount() {
        return 7;
    }
    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide("http://netcollege-bartar.ir/categorypic/177vmware.jpeg");

                break;
            case 1:
                viewHolder.bindImageSlide("http://netcollege-bartar.ir/categorypic/710voip.jpeg");
                break;
            case 2:
                viewHolder.bindImageSlide("http://netcollege-bartar.ir/categorypic/601python.jpeg");
                break;
            case 3:
                viewHolder.bindImageSlide("http://netcollege-bartar.ir/categorypic/861cisco.jpeg");
                break;
            case 4:
                viewHolder.bindImageSlide("http://netcollege-bartar.ir/categorypic/268unity.jpeg");
                break;
            case 5:
                viewHolder.bindImageSlide("http://netcollege-bartar.ir/categorypic/786web%20design.jpeg");
                break;
            case 6:
                viewHolder.bindImageSlide("http://netcollege-bartar.ir/categorypic/640mikrotik.jpeg");
                break;
        }
    }
}
