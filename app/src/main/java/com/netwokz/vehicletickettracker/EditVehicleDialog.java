package com.netwokz.vehicletickettracker;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by netwokz on 7/23/2017.
 */

public class EditVehicleDialog extends DialogFragment implements View.OnClickListener {

    private EditText mStockEdit;
    private EditText mYearEdit;
    private EditText mMakeEdit;
    private EditText mModelEdit;
    private EditText mColorEdit;
    private EditText mHoursEdit;

    private Button mSaveButton;
    private Button mCancelButton;

    public EditVehicleDialog() {

    }

    public static EditVehicleDialog newInstance() {
        EditVehicleDialog mFragment = new EditVehicleDialog();
        return mFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.new_veficle_view, container);
        mCancelButton = (Button) mView.findViewById(R.id.btn_cancel);
        mSaveButton = (Button) mView.findViewById(R.id.btn_save);
        mCancelButton.setOnClickListener(this);
        mSaveButton.setOnClickListener(this);

        mStockEdit = (EditText) mView.findViewById(R.id.et_stock);
        mYearEdit = (EditText) mView.findViewById(R.id.et_year);
        mMakeEdit = (EditText) mView.findViewById(R.id.et_make);
        mModelEdit = (EditText) mView.findViewById(R.id.et_model);
        mColorEdit = (EditText) mView.findViewById(R.id.et_color);
        mHoursEdit = (EditText) mView.findViewById(R.id.et_hours);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_save:
                saveVehicle();
                break;
        }
    }

    private void saveVehicle() {
        int mStock = Integer.valueOf(mStockEdit.getText().toString());
        String mYear = mYearEdit.getText().toString();
        String mMake = mMakeEdit.getText().toString();
        String mModel = mModelEdit.getText().toString();
        String mColor = mColorEdit.getText().toString();
        String mHours = mHoursEdit.getText().toString();
        long mDate = Calendar.getInstance().getTimeInMillis();

        Vehicle newVehicle = new Vehicle(mStock, mYear, mMake, mModel, mColor, mHours, mDate);
        newVehicle.save();
        dismiss();
    }
}
