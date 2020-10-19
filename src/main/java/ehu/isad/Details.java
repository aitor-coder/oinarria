package ehu.isad;

import java.util.Arrays;

public class Details {
    String[] publishers;
    Integer number_of_pages;
    String title;

    @Override
    public String toString() {
        return "Details{" +
                "publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                ", title='" + title + '\'' +
                '}';
    }
    public String[] getPublishers() {
        return publishers;
    }

    public Integer getNumber_of_pages() {
        return number_of_pages;
    }

    public String getTitle() {
        return title;
    }

    public void setPublishers(String[] publishers) {
        this.publishers = publishers;
    }

    public void setNumber_of_pages(Integer number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
