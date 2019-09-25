package com.netwokz.vehicletickettracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by netwokz on 7/23/2017.
 */

public class Preferences extends AppCompatActivity {

    public static final String SortAscendingCheckPref = "preference_ascending_checkbox";
    public static final String SortPref = "preference_sort_order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefernce_activity);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        mToolbar.setTitle("Settings");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getFragmentManager().beginTransaction().replace(R.id.frame_container, new MyPreferenceFragment()).commit();

    }

    public static class MyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        SharedPreferences sharedPrefs;

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.app_preferences);
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            updatePreference(findPreference(SortAscendingCheckPref));
            updatePreference(findPreference(SortPref));
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            updatePreference(findPreference(key));
        }

        private void updatePreference(Preference preference) {
            if (preference instanceof CheckBoxPreference) {
                CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
                if (checkBoxPreference.isChecked()) {
                    checkBoxPreference.setSummary("Sort Vehicles in ascending order");
                } else {
                    checkBoxPreference.setSummary("Sort Vehicles in descending order");
                }
            }

            if (preference instanceof ListPreference) {
                int listPrefValue = Integer.valueOf(sharedPrefs.getString(SortPref, "0"));
                ListPreference sortPreference = (ListPreference) preference;
                switch (listPrefValue) {
                    case 0:
                        sortPreference.setSummary("Sorting by Stock Number");
                        break;
                    case 1:
                        sortPreference.setSummary("Sorting by Year");
                        break;
                    case 2:
                        sortPreference.setSummary("Sorting by Make");
                        break;
                    case 3:
                        sortPreference.setSummary("Sorting by Model");
                        break;
                    case 4:
                        sortPreference.setSummary("Sorting by Color");
                        break;
                    case 5:
                        sortPreference.setSummary("Sorting by Hours");
                        break;
                    case 6:
                        sortPreference.setSummary("Sorting by Date");
                        break;
                }
            }
        }
    }
}
