package sg.vinova.dom.myapplication.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import sg.vinova.dom.myapplication.model.Account;

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
                Account account;
                for (int i = 0; i < 10; i++) {
                    account = new Account("g" + i, "g" + i);
                    realm.insertOrUpdate(account);
                }
            }
        });
    }
}