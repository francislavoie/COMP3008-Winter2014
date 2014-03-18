package com.bluink.shiftitmanager.fragments;

import com.bluink.shiftitmanager.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TradeZoneFragment extends Fragment {

	public TradeZoneFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_trade_zone, container, false);
		return rootView;
	}

}
