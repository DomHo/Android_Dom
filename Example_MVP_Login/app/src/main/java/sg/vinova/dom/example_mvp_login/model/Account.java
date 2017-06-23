package sg.vinova.dom.example_mvp_login.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by HNS on 19/06/2017.
 */

public class Account extends RealmObject implements Parcelable {

    private String username;
    private String password;
    private String image;

    public Account() {
    }

    public Account(String username, String password, String image) {
        this.username = username;
        this.password = password;
        this.image = image;
    }

    protected Account(Parcel in) {
        username = in.readString();
        password = in.readString();
        image = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(image);
    }
}