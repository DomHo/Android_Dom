package sg.vinova.dom.myapplication.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import sg.vinova.dom.myapplication.model.Account;
import sg.vinova.dom.myapplication.model.Image;

/**
 * Created by HNS on 26/06/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Account account = null;
                Image image = null;
                for (int i = 0; i < 10; i++) {
                    account = new Account("g" + i, "g" + i);
                    realm.insertOrUpdate(account);
                }

                for (int i = 0; i < 100; i++) {
                    if (i % 3 == 0)
                        image = new Image(i, "Image " + i, "", "https://lh3.googleusercontent.com/-R0e4S4Gkq9M/VxekkHvg_CI/AAAAAAAAB1A/_RFmqmFztEo/s1600/natural%252520hd%252520wallpaper%252520background9155691.jpg");
                    if (i % 3 == 1)
                        image = new Image(i, "Image " + i, "", "http://doublemesh.com/wp-content/uploads/2016/06/Bora-Bora-Beach-desktop-background.jpg");
                    if (i % 3 == 2)
                        image = new Image(i, "Image " + i, "", "http://www.wallpaperbetter.com/wallpaper/120/221/97/mountains-landscapes-nature-lakes-free-background-1080P-wallpaper-middle-size.jpg");
                    realm.insertOrUpdate(image);
                }
            }
        });
    }
}