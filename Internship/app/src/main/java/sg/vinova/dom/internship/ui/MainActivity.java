package sg.vinova.dom.internship.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckedTextView;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

import sg.vinova.dom.internship.R;
import sg.vinova.dom.internship.ui.DeliveriesFragment;
import sg.vinova.dom.internship.ui.ExploreFragment;
import sg.vinova.dom.internship.Model.Food;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    ArrayList<ArrayList<Food>> listData;
    int position = 0;

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

        listData = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            ArrayList<Food> temp = new ArrayList<>();
            for (int k = 0; k <= 10; k++) {
                if (k%2 == 1)
                    temp.add(new Food(i, "https://www.gimmesomeoven.com/wp-content/uploads/2009/10/sesame-noodles.jpg", "Food " + i + k, "3.5", "Nation " + i + k));
                else
                    temp.add(new Food(i, "https://www.toppers.com/Portals/0/house-pizza-bacon-cheeseburger.jpg", "Food " + i + k, "3.5", "Nation " + i + k));
            }
            listData.add(temp);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_deliveries);
        navigationView.getMenu().performIdentifierAction(R.id.nav_deliveries, 0);
        toolbar.setTitle(R.string.drawer_deliveries);

        FlowLayout flNation = (FlowLayout) findViewById(R.id.flNation);

        final String[] nation = {"Italian", "America", "French", "Pizza", "Noodle", "Japan", "Breakfast", "Danish", "Potugese"};
        final ArrayList<CheckedTextView> listTag = new ArrayList<>();
        final Button btnAll = (Button) findViewById(R.id.btnAll);
        final Button btnNone = (Button) findViewById(R.id.btnNone);
        btnNone.setBackgroundResource(R.color.colorPrimary);
        final int[] countTag = {0};

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
                        countTag[0]--;
                    } else {
                        checkedTextView.setChecked(true);
                        checkedTextView.setBackgroundResource(R.drawable.flow_layout_element_checked);
                        checkedTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                        countTag[0]++;
                    }
                    if (countTag[0] == 0) {
                        btnAll.setBackgroundResource(R.color.colorGrey);
                        btnNone.setBackgroundResource(R.color.colorPrimary);
                    }
                    else if (countTag[0] == nation.length){
                        btnAll.setBackgroundResource(R.color.colorPrimary);
                        btnNone.setBackgroundResource(R.color.colorGrey);
                    }
                    else {
                        btnAll.setBackgroundResource(R.color.colorGrey);
                        btnNone.setBackgroundResource(R.color.colorGrey);
                    }
                }
            });
            flNation.addView(checkedTextView);
            listTag.add(checkedTextView);
        }

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countTag[0] = nation.length;
                btnAll.setBackgroundResource(R.color.colorPrimary);
                btnNone.setBackgroundResource(R.color.colorGrey);
                for (CheckedTextView checkedTextView : listTag) {
                    checkedTextView.setChecked(true);
                    checkedTextView.setBackgroundResource(R.drawable.flow_layout_element_checked);
                    checkedTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });

        btnNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countTag[0] = 0;
                btnAll.setBackgroundResource(R.color.colorGrey);
                btnNone.setBackgroundResource(R.color.colorPrimary);
                for (CheckedTextView checkedTextView : listTag) {
                    checkedTextView.setChecked(false);
                    checkedTextView.setBackgroundResource(R.drawable.flow_layout_element_uncheck);
                    checkedTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });
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
        int id = item.getItemId();

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

        switch (item.getItemId()){
            case R.id.nav_deliveries:
                toolbar.setTitle(R.string.drawer_deliveries);
                getFragmentManager().beginTransaction()
                        .replace(R.id.mainFrame, DeliveriesFragment.newInstance(listData.get(position)))
                        .commitAllowingStateLoss();
                break;
            case R.id.nav_explore:
                toolbar.setTitle(R.string.drawer_explore);
                getFragmentManager().beginTransaction()
                        .replace(R.id.mainFrame, ExploreFragment.newInstance(listData))
                        .commitAllowingStateLoss();
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static int statusBarHeight(android.content.res.Resources res) {
        return (int) (24 * res.getDisplayMetrics().density);
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
