package sg.vinova.dom.internship;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import org.apmem.tools.layouts.FlowLayout;

import sg.vinova.dom.internship.Fragment.DeliveriesFragment;
import sg.vinova.dom.internship.Fragment.ExploreFragment;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FlowLayout flNation = (FlowLayout) findViewById(R.id.flNation);

        String[] nation = {"Italian", "America", "French", "Pizza", "Noodle", "Japan", "Breakfast", "Danish", "Potugese"};

        for (String aNation : nation) {
            final CheckedTextView checkedTextView = new CheckedTextView(this);
            checkedTextView.setText(aNation);
            checkedTextView.setTextSize(20);
            checkedTextView.setPadding(20, 10, 20, 10);
            FlowLayout.LayoutParams llp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(0, 15, 15, 0);
            checkedTextView.setLayoutParams(llp);
            checkedTextView.setBackgroundResource(R.drawable.flow_layout_element_uncheck);
            checkedTextView.setTextColor(getResources().getColor(R.color.colorPrimary));

            checkedTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkedTextView.isChecked()) {
                        checkedTextView.setChecked(false);
                        checkedTextView.setBackgroundResource(R.drawable.flow_layout_element_uncheck);
                        checkedTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    } else {
                        checkedTextView.setChecked(true);
                        checkedTextView.setBackgroundResource(R.drawable.flow_layout_element_checked);
                        checkedTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                }
            });
            flNation.addView(checkedTextView);
        }

        toolbar.setTitle(R.string.drawer_deliveries);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, DeliveriesFragment.getInstance());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter_list) {

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_viewR);
            navigationView.setPadding(0, statusBarHeight(getResources()), 0, 0);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.END);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        switch (item.getItemId()){
            case R.id.nav_deliveries:
                toolbar.setTitle(R.string.drawer_deliveries);
                fragmentTransaction.replace(R.id.mainFrame, DeliveriesFragment.getInstance());
                break;
            case R.id.nav_explore:
                toolbar.setTitle(R.string.drawer_explore);
                fragmentTransaction.replace(R.id.mainFrame, ExploreFragment.getInstance());
                break;
            case R.id.nav_olders_history:
                toolbar.setTitle(R.string.drawer_olders_history);
                break;
            case R.id.nav_contributions:
                toolbar.setTitle(R.string.drawer_contributions);
                break;
            case R.id.nav_help_feedback:
                toolbar.setTitle(R.string.drawer_help_amp_feedback);
                break;
        }
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static int statusBarHeight(android.content.res.Resources res) {
        return (int) (24 * res.getDisplayMetrics().density);
    }
}
