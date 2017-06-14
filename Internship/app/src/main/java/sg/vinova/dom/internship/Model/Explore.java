package sg.vinova.dom.internship.Model;

/**
 * Created by HNS on 14/06/2017.
 */

public class Explore {
    private int id;
    private String image;
    private String name;
    private String rate;
    private String nation;

    public Explore(int id, String image, String name, String rate, String nation) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.rate = rate;
        this.nation = nation;
    }

    // GET
    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getRate() {
        return rate;
    }

    public String getNation() {
        return nation;
    }

    // SET
    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
