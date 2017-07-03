package sg.vinova.dom.internship2.model;

import java.io.Serializable;

/**
 * Created by HNS on 18/06/2017.
 */

public class Home implements Serializable {
    private String image;
    private String content;

    public Home(String image, String content) {
        this.image = image;
        this.content = content;
    }

    // GET
    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    // SET
    public void setImage(String image) {
        this.image = image;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
