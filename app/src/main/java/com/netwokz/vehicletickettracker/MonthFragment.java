package com.netwokz.vehicletickettracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by netwokz on 7/25/2017.
 */

public class MonthFragment extends Fragment {

    RecyclerViewAdapter mAdapter;
    RecyclerView mRecyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private int MONTH = 1;
    List<Vehicle> mVehicles;
    boolean ascendingOrder;

    Calendar mDateBeg;
    Calendar mDateEnd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.month_fragment_layout, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        ascendingOrder = sharedPrefs.getBoolean("preference_ascending_checkbox", false);

        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        mDateBeg = Calendar.getInstance(TimeZone.getDefault());
        mDateEnd = Calendar.getInstance(TimeZone.getDefault());
        mDateBeg.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1, 0, 0, 0);
        mDateEnd.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 31, 23, 59, 59);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_month);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(500);
        mRecyclerView.setItemAnimator(itemAnimator);
        mVehicles = getVehicleList();
        mAdapter = new RecyclerViewAdapter(getContext(), mVehicles);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
//        refreshList();
    }

    public List<Vehicle> getVehicleList() {
        List<Vehicle> mList = Vehicle.findWithQuery(Vehicle.class, "SELECT * FROM VEHICLE WHERE M_DATE_ADDED BETWEEN ? AND ?", mDateBeg.getTime().getTime() + "", mDateEnd.getTime().getTime() + "");
        Collections.sort(mList, new VehicleComparator(MONTH, ascendingOrder));
        return mList;
    }

    public void refreshList() {
        mAdapter.clear();
        mVehicles = getVehicleList();
        mAdapter.addAll(mVehicles);
        mAdapter.notifyDataSetChanged();
    }
}
