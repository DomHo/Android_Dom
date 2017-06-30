package sg.vinova.dom.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("albumId")
    private int albumId;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String content;
    @SerializedName("url")
    private String link;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    public Image() {
    }

    public Image(int albumId, int id, String content, String link, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.content = content;
        this.link = link;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}