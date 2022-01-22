package metier;

public class History {
    private int id;
    private String date;
    private String url;

    public History(int id, String date, String url) {
        this.id = id;
        this.date = date;
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
