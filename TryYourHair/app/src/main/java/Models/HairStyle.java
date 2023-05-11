package Models;

public class HairStyle {
    private String url;
    private String description;

    public HairStyle(String url, String des) {
        this.url = url;
        this.description = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
