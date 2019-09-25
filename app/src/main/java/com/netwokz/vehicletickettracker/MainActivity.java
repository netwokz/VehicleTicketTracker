package com.netwokz.vehicletickettracker;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

/**
 * Created by netwokz on 7/19/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Vehicle> mVehicles;
    RecyclerViewAdapter mAdapter;
    private CoordinatorLayout mCoordinatorLayout;

    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;

    public static final String SortAscending = "preference_ascending_checkbox";
    public static final String SortPref = "preference_sort_order";
    SharedPreferences sharedPrefs;

    boolean ascendingOrder;
    int sortOrder;

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_pager);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        FloatingActionButton mFab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        mFab.setOnClickListener(this);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        ascendingOrder = sharedPrefs.getBoolean(SortAscending, true);
        sortOrder = Integer.valueOf(sharedPrefs.getString(SortPref, "0"));
        mVehicles = getVehicleList();
        tabLayout = (TabLayout) findViewById(R.id.tab_bar);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public void makeSnackbar(final Vehicle vehicle, final int position) {
        final Vehicle mOldVehicle = vehicle;
//        Log.d("SnackBar UNDO", "mVehicles Size: " + mVehicles.size());
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, "Vehicle " + position + " is removed", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new Vehicle(mOldVehicle).save();
                        mVehicles.add(mOldVehicle);
//                        Log.d("SnackBar UNDO", "mVehicles Size: " + mVehicles.size());
                        Snackbar snackbar1 = Snackbar.make(mCoordinatorLayout, "Vehicle is restored!", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
//                        refreshList();
                    }
                });
        snackbar.show();
    }

    private List<Vehicle> getVehicleList() {
//        Log.d("MainActivity", "getVehicleList()");
        List<Vehicle> mList = Vehicle.listAll(Vehicle.class);
        return mList;
    }

    public void initializeTempCars(int amount) {
        for (int i = 0; i < amount; i++) {
            new Vehicle(GeneratedVehicle.generate()).save();
        }
        mViewPager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewPager.getAdapter().notifyDataSetChanged();
//        refreshList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                mAdapter.notifyDataSetChanged();
                break;
//            case R.id.action_refresh:
//                mAdapter.notifyDataSetChanged();
//                break;
            case R.id.action_settings:
                Intent i = new Intent(this, Preferences.class);
                startActivity(i);
                break;
            case R.id.action_add_generated_car:
                Vehicle mVehicle = GeneratedVehicle.generate();
                mVehicle.save();
                mViewPager.getAdapter().notifyDataSetChanged();
//                mVehicles.add(mVehicle);
//                refreshList();
                break;
            case R.id.action_clear_db:
                Vehicle.deleteAll(Vehicle.class);
                mViewPager.getAdapter().notifyDataSetChanged();
//                mVehicles.clear();
//                refreshList();
            default:
                break;
        }
        return true;
    }

//    public void refreshList() {
//        sortOrder = Integer.valueOf(sharedPrefs.getString(SortPref, "0"));
//        ascendingOrder = sharedPrefs.getBoolean(SortAscending, true);
//        mAdapter.clear();
//        mVehicles = getVehicleList();
//        Collections.sort(mVehicles, new VehicleComparator(sortOrder, ascendingOrder));
//        mAdapter.addAll(mVehicles);
//        mAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onClick(View v) {
        // FAB click
        showNewVehicleDialog();
    }

    private void showNewVehicleDialog() {
        FragmentManager fm = getFragmentManager();
        NewVehicleDialog editNameDialogFragment = NewVehicleDialog.newInstance();
        editNameDialogFragment.show(fm, "fragment_new_vehicle");
    }
}
