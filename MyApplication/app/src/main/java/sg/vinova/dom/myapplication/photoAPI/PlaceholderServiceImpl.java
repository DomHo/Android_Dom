package sg.vinova.dom.myapplication.photoAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sg.vinova.dom.myapplication.model.Photo;

public class PlaceholderServiceImpl implements PlaceholderService {
    @Override
    public Call<List<Photo>> getPhotos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceholderService service = retrofit.create(PlaceholderService.class);
        return service.getPhotos();
    }
}