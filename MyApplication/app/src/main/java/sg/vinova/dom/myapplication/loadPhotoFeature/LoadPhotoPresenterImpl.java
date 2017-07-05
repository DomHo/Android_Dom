package sg.vinova.dom.myapplication.loadPhotoFeature;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import sg.vinova.dom.myapplication.API.PhotoServiceImpl;
import sg.vinova.dom.myapplication.R;
import sg.vinova.dom.myapplication.model.Photo;

public class LoadPhotoPresenterImpl implements LoadPhoto.Presenter {

    private Context context;
    private LoadPhoto.View loadPhotoView;
    private List<Photo> photoList;
    private Observable<List<Photo>> myObservable = null;
    private Observer<List<Photo>> myObserver = null;
    int i = 0;

    public LoadPhotoPresenterImpl(Context context, LoadPhoto.View loadPhotoView) {
        this.context = context;
        this.loadPhotoView = loadPhotoView;
        init();
    }

    private void init() {
        if (myObservable == null)
            myObservable = new PhotoServiceImpl().getPhotos();

        if (myObserver == null)
            myObserver = new Observer<List<Photo>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    Log.d("photo", "onSubscribe");
                }

                @Override
                public void onNext(@NonNull List<Photo> photos) {
                    Log.d("photo", "onNext");
                    photoList = photos;
                    loadPhotoView.loadNewData(photoList);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d("photo", "onError");
                    loadPhotoView.error(e.toString());
                }

                @Override
                public void onComplete() {
                    Log.d("photo", "onComplete");
                }
            };
    }

    @Override
    public void getNewData() {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo == null) {
            loadPhotoView.error("Network unavailable");
            return;
        }
        if (!netInfo.isAvailable()) {
            loadPhotoView.error("Network error");
            return;
        }

        myObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver);
    }
}