package sg.vinova.dom.internship.Model;

/**
 * Created by HNS on 13/06/2017.
 */

public class Delivery {
    private int id;
    private String image;
    private String name;
    private String nation;

    public Delivery(int id, String image, String name, String nation) {
        this.id = id;
        this.image = image;
        this.name = name;
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

    public void setNation(String nation) {
        this.nation = nation;
    }
}
