package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 6/28/2019.
 */
public class ImageGallery {
    private int ImageID;
    private String ImageLogoUrl;
    private String ImageCaption;

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    public String getImageLogoUrl() {
        return ImageLogoUrl;
    }

    public void setImageLogoUrl(String imageLogoUrl) {
        ImageLogoUrl = imageLogoUrl;
    }

    public String getImageCaption() {
        return ImageCaption;
    }

    public void setImageCaption(String imageCaption) {
        ImageCaption = imageCaption;
    }
}
