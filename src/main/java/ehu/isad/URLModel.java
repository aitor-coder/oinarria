package ehu.isad;

public class URLModel {
    private String url;
    private String md5;
    private String bertsioa;
    private String path;

    public URLModel(String url, String md5, String bertsioa, String path) {
        this.url = url;
        this.md5 = md5;
        this.bertsioa = bertsioa;
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getBertsioa() {
        return bertsioa;
    }

    public void setBertsioa(String bertsioa) {
        this.bertsioa = bertsioa;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "URLModel{" +
                "url='" + url + '\'' +
                ", md5='" + md5 + '\'' +
                ", bertsioa='" + bertsioa + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
