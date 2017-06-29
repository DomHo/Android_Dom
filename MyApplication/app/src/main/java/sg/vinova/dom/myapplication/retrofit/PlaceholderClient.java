package sg.vinova.dom.myapplication.retrofit;

import java.util.List;

import retrofit2.http.GET;

/**
 * Created by HNS on 29/06/2017.
 */

public interface PlaceholderClient {

    @GET("/photos")
    List<PlaceholderRepo> getAll();
}
