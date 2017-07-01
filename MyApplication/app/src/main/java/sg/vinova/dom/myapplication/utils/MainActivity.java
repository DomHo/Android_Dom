package sg.vinova.dom.myapplication.utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import sg.vinova.dom.myapplication.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, getIntent().getStringExtra("Welcome"), Toast.LENGTH_LONG).show();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.nav_gallery);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_gallery);
        navigationView.getMenu().performIdentifierAction(R.id.nav_gallery, 0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View view = findViewById(R.id.star);
        switch (item.getItemId()) {
            case R.id.blink:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink));
                break;
            case R.id.clockwise:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.clockwise));
                break;
            case R.id.fade:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade));
                break;
            case R.id.move:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move));
                break;
            case R.id.myanimation:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.myanimation));
                break;
            case R.id.slide:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide));
                break;
            case R.id.down:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_down));
                break;
            case R.id.left:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_left));
                break;
            case R.id.right:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_right));
                break;
            case R.id.up:
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_up));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_gallery:
                getSupportFragmentManager().beginTransaction().replace(R.id.flMain, GalleryFragment.newInstance()).commitAllowingStateLoss();
                toolbar.setTitle(R.string.nav_gallery);
                break;
            case R.id.nav_weather:
                Intent intent = new Intent(this, WeatherActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.switch_main_weather_left_in, R.anim.switch_main_weather_left_out);
                break;
            case R.id.nav_share:
                //
                break;
            case R.id.nav_send:
                //
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}