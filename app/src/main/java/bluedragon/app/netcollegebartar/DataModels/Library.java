package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 6/28/2019.
 */
public class Library {

    private int Book_id;
    private int Book_category;
    private String BookName;
    private String BookImage;
    private String BookDownloadLink;
    private String BooKDescription;

    public int getBook_id() {
        return Book_id;
    }

    public void setBook_id(int book_id) {
        Book_id = book_id;
    }

    public int getBook_category() {
        return Book_category;
    }

    public void setBook_category(int book_category) {
        Book_category = book_category;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }

    public String getBookDownloadLink() {
        return BookDownloadLink;
    }

    public void setBookDownloadLink(String bookDownloadLink) {
        BookDownloadLink = bookDownloadLink;
    }

    public String getBooKDescription() {
        return BooKDescription;
    }

    public void setBooKDescription(String booKDescription) {
        BooKDescription = booKDescription;
    }
}
