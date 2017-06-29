package sg.vinova.dom.myapplication.ExampleAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sg.vinova.dom.myapplication.model.Image;

/**
 * Created by HNS on 29/06/2017.
 */

public interface PlaceholderService {

    @GET("photos")
    Call<List<Image>> getImages();
}