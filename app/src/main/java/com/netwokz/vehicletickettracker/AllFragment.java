package com.netwokz.vehicletickettracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by netwokz on 7/25/2017.
 */

public class AllFragment extends Fragment {

    RecyclerViewAdapter mAdapter;
    RecyclerView mRecyclerView;
    private int TODAY = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.all_fragment_layout, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean ascendingOrder = sharedPrefs.getBoolean("preference_ascending_checkbox", true);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_all);
//        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(500);
        mRecyclerView.setItemAnimator(itemAnimator);
        List<Vehicle> mVehicles = getVehicleList();
        Collections.sort(mVehicles, new VehicleComparator(TODAY, ascendingOrder));
        mAdapter = new RecyclerViewAdapter(getContext(), mVehicles);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Vehicle> getVehicleList() {
//        Log.d("MainActivity", "getVehicleList()");
        List<Vehicle> mList = Vehicle.listAll(Vehicle.class);
        return mList;
    }
}
