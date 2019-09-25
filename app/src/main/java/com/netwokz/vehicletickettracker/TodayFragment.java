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

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by netwokz on 7/25/2017.
 */

public class TodayFragment extends Fragment implements RecyclerViewAdapter.OnCardLongClickListener {

    RecyclerViewAdapter mAdapter;
    RecyclerView mRecyclerView;
    private int TODAY = 0;
    List<Vehicle> mVehicles;
    boolean ascendingOrder;
    Calendar mDateBeg;
    Calendar mDateEnd;
    private OnCardLongClickListener mListener;

    public interface OnCardLongClickListener {
        // These methods are the different events and
        // need to pass relevant arguments related to the event triggered
        void onCardLongClick(Vehicle vehicle);
    }

    public void setOnCardClickListener(OnCardLongClickListener listener) {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.today_fragment_layout, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        mDateBeg = Calendar.getInstance(TimeZone.getDefault());
        mDateEnd = Calendar.getInstance(TimeZone.getDefault());
        mDateBeg.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 0, 0, 0);
        mDateEnd.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 23, 59, 59);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        ascendingOrder = sharedPrefs.getBoolean("preference_ascending_checkbox", true);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_today);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(500);
        mRecyclerView.setItemAnimator(itemAnimator);
        mVehicles = getVehicleList();
        mAdapter = new RecyclerViewAdapter(getContext(), mVehicles);
        mAdapter.setOnCardClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
//        refreshList();
    }

    public List<Vehicle> getVehicleList() {
        List<Vehicle> mList = Vehicle.findWithQuery(Vehicle.class, "SELECT * FROM VEHICLE WHERE M_DATE_ADDED BETWEEN ? AND ?", mDateBeg.getTime().getTime() + "", mDateEnd.getTime().getTime() + "");
        Collections.sort(mList, new VehicleComparator(TODAY, ascendingOrder));
        return mList;
    }

    public void refreshList() {
        mAdapter.clear();
        mVehicles = getVehicleList();
        mAdapter.addAll(mVehicles);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onCardLongClick(Vehicle vehicle) {
        mListener.onCardLongClick(vehicle);
    }
}
