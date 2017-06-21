package sg.vinova.dom.example_mvp_login.model;

/**
 * Created by HNS on 19/06/2017.
 */

public class Account {

    private String username;
    private String password;
    private String image;

    public Account(String username, String password, String image) {
        this.username = username;
        this.password = password;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}