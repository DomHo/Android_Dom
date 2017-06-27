package sg.vinova.dom.myapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by HNS on 27/06/2017.
 */

public class Image extends RealmObject {

    @PrimaryKey
    private int id;
    private String content;
    private String directory;
    private String link;

    public Image() {
    }

    public Image(int id, String content, String directory, String link) {
        this.id = id;
        this.content = content;
        this.directory = directory;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}