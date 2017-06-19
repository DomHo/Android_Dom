package sg.vinova.dom.internship3_foodi.model;

import java.io.Serializable;

/**
 * Created by HNS on 18/06/2017.
 */

public class Food implements Serializable {

    private String image;
    private String name;

    public Food(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
