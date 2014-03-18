package com.bluink.shiftitmanager.fragments;

import com.bluink.shiftitmanager.R;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getActivity()));

        // add test preference
        addTestPreference();
    }

    private void addTestPreference() {

        // get the root preference
        PreferenceScreen root = getPreferenceScreen();

        // add test preference
        CheckBoxPreference test = new CheckBoxPreference(getActivity());
        test.setTitle("Test Preference");
        root.addPreference(test);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}
