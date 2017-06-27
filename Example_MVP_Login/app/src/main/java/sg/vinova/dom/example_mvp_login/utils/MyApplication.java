package sg.vinova.dom.example_mvp_login.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.io.File;

import io.realm.Realm;
import sg.vinova.dom.example_mvp_login.model.Account;
import sg.vinova.dom.example_mvp_login.module.LoginPresenterImpl;

/**
 * Created by HNS on 23/06/2017.
 */

public class MyApplication extends Application {

    public static LoginPresenterImpl presenter;

    @Override
    public void onCreate() {
        super.onCreate();

        presenter = new LoginPresenterImpl();

        Realm.init(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        File file = new File(getFilesDir(), "default.realm");
        if(!file.exists()) {
            Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    for (int i = 0; i < 10; i++) {
                        Account account = realm.createObject(Account.class);
                        account.setUsername("g" + i);
                        account.setPassword("g" + i);
                        account.setImage("http://www.intrawallpaper.com/static/images/Screen-shot-2012-04-02-at-3.46.15-PM.png");
                    }
                }
            });
        }
    }
}