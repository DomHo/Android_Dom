package sg.vinova.dom.internship.Model;

/**
 * Created by HNS on 15/06/2017.
 */

public class Food {
    private int type;
    private String image;
    private String name;
    private String rate;
    private String nation;

    public Food(int type, String image, String name, String rate, String nation) {
        this.type = type;
        this.image = image;
        this.name = name;
        this.rate = rate;
        this.nation = nation;
    }

    // GET
    public int getType() {

        return type;
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
    public void setType(int type) {
        this.type = type;
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