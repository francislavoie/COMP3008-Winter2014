package com.superultrameh.roombooking.fragments;

import com.superultrameh.roombooking.R;
import com.superultrameh.roombooking.adapters.AlertListAdapter;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AlertsFragment extends ListFragment {

    private AlertListAdapter mAdapter;
    private View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_alerts, container, false);

        mAdapter = new AlertListAdapter(getActivity());
        setListAdapter(mAdapter);

        mAdapter.update();

		return rootView;
	}
}
