package com.netwokz.vehicletickettracker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by netwokz on 7/25/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final SparseArray<Fragment> mPageReferences = new SparseArray<Fragment>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new TodayFragment();
        } else if (position == 1) {
            fragment = new MonthFragment();
        } else if (position == 2) {
            fragment = new AllFragment();
        }
        mPageReferences.put(position, fragment);
        return fragment;
    }

    public void destroyItem(View container, int position, Object object) {
        super.destroyItem(container, position, object);
        mPageReferences.remove(position);
    }

    public Fragment getFragment(int key) {
        return mPageReferences.get(key);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Today";
        } else if (position == 1) {
            title = "Month";
        } else if (position == 2) {
            title = "All";
        }
        return title;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
