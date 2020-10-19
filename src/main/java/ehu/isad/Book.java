package ehu.isad;

public class Book {
    public String isbn;
    public String title;

    String info_url;
    String bib_key;
    String preview_url;
    String thumbnail_url;
    Details details;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }


    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", info_url='" + info_url + '\'' +
                ", bib_key='" + bib_key + '\'' +
                ", preview_url='" + preview_url + '\'' +
                ", thumbnail_url='" + thumbnail_url + '\'' +
                ", details=" + details +
                '}';
    }
    public String getInfo_url() {
        return info_url;
    }

    public String getBib_key() {
        return bib_key;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public Details getDetails() {
        return details;
    }

    public void setInfo_url(String info_url) {
        this.info_url = info_url;
    }

    public void setBib_key(String bib_key) {
        this.bib_key = bib_key;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}