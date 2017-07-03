package sg.vinova.dom.internship3_foodi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import sg.vinova.dom.internship3_foodi.R;
import sg.vinova.dom.internship3_foodi.model.Food;

public class MainActivity extends AppCompatActivity {

    ArrayList<Food> listFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent splash = new Intent(MainActivity.this, SplashScreenActivity.class);
        startActivity(splash);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listFood = new ArrayList<>();
        for (int i = 0; i < 10000; i++)
            if (i%2 == 0)
                listFood.add(new Food("https://www.gimmesomeoven.com/wp-content/uploads/2009/10/sesame-noodles.jpg", "Name " + i));
            else
                listFood.add(new Food("https://www.toppers.com/Portals/0/house-pizza-bacon-cheeseburger.jpg", "Name " + i));

        navigation.setSelectedItemId(R.id.navigation_store);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_select:
                    return true;
                case R.id.navigation_store:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flMain, StoreFragment.newInstance(listFood))
                            .commitAllowingStateLoss();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorGrey));
                    return true;
                case R.id.navigation_magazine:
                    return true;
                case R.id.navigation_cart:
                    return true;
                case R.id.navigation_me:
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flMain, MeFragment.newInstance())
                            .commitAllowingStateLoss();
                    return true;
            }
            return false;
        }

    };
}