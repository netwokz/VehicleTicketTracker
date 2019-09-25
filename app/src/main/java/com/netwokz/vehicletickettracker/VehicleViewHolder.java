package com.netwokz.vehicletickettracker;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by netwokz on 7/23/2017.
 */

public class VehicleViewHolder extends RecyclerView.ViewHolder {
    CardView mCardView;
    TextView mStockNumber;
    TextView mYear;
    TextView mMake;
    TextView mModel;
    TextView mColor;
    TextView mHours;
    TextView mDate;

    VehicleViewHolder(View itemView) {
        super(itemView);
        mCardView = (CardView) itemView.findViewById(R.id.card_view);
        mStockNumber = (TextView) itemView.findViewById(R.id.view_stock_number);
        mYear = (TextView) itemView.findViewById(R.id.view_year);
        mMake = (TextView) itemView.findViewById(R.id.view_make);
        mModel = (TextView) itemView.findViewById(R.id.view_model);
        mColor = (TextView) itemView.findViewById(R.id.view_color);
        mHours = (TextView) itemView.findViewById(R.id.view_total_hours);
        mDate = (TextView) itemView.findViewById(R.id.view_date_added);
        mCardView.setRadius(20);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Item " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
