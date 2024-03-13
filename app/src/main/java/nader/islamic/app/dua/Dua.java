package nader.islamic.app.dua;

import java.util.List;

public class Dua {
    private int id;
    private String duaa_arabic;
    private String duaa_meaning;
    private List<String> tags;

    public Dua(int id, String duaa_arabic, String duaa_meaning, List<String> tags) {
        this.id = id;
        this.duaa_arabic = duaa_arabic;
        this.duaa_meaning = duaa_meaning;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuaa_arabic() {
        return duaa_arabic;
    }

    public void setDuaa_arabic(String duaa_arabic) {
        this.duaa_arabic = duaa_arabic;
    }

    public String getDuaa_meaning() {
        return duaa_meaning;
    }

    public void setDuaa_meaning(String duaa_meaning) {
        this.duaa_meaning = duaa_meaning;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
