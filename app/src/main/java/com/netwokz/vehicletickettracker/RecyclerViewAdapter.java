package com.netwokz.vehicletickettracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by netwokz on 7/22/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<VehicleViewHolder> {

    List<Vehicle> mVehicles;
    private OnCardLongClickListener mListener;

    public interface OnCardLongClickListener {
        // These methods are the different events and
        // need to pass relevant arguments related to the event triggered
        void onCardLongClick(Vehicle vehicle);
    }

    public void setOnCardClickListener(OnCardLongClickListener listener) {
        mListener = listener;
    }


    public RecyclerViewAdapter(Context context, List<Vehicle> vehicles) {
        mVehicles = vehicles;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vehicle_card_layout, viewGroup, false);
        VehicleViewHolder vehicleViewHolder = new VehicleViewHolder(mView);
        return vehicleViewHolder;
    }

    @Override
    public void onBindViewHolder(final VehicleViewHolder vehicleViewHolder, final int i) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/YY hh:mm a");
        String mDate = df.format(mVehicles.get(i).mDateAdded);
        vehicleViewHolder.mStockNumber.setText(String.valueOf(mVehicles.get(i).mStockNumber));
        vehicleViewHolder.mYear.setText(mVehicles.get(i).mYear);
        vehicleViewHolder.mMake.setText(mVehicles.get(i).mMake);
        vehicleViewHolder.mModel.setText(mVehicles.get(i).mModel);
        vehicleViewHolder.mColor.setText(mVehicles.get(i).mColor);
        vehicleViewHolder.mHours.setText(mVehicles.get(i).mHours);
        vehicleViewHolder.mDate.setText(mDate);
        vehicleViewHolder.mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListener != null)
                    mListener.onCardLongClick(mVehicles.get(i));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVehicles.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Clean all elements of the recycler
    public void clear() {
        mVehicles.clear();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Vehicle> vehicles) {
        mVehicles.addAll(vehicles);
    }

}
