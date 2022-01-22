package metier;

public class Bookmark {
    private int id;
    private String name;
    private String url;

    public Bookmark(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
