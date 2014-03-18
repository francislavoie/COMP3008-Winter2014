package com.bluink.shiftitmanager.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluink.shiftitmanager.R;
import com.bluink.shiftitmanager.adapters.ApproveTradesListAdapter;

public class ApproveTradesFragment extends ListFragment {

    private ApproveTradesListAdapter mAdapter;
    private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_approve_trades, container, false);

        mAdapter = new ApproveTradesListAdapter(getActivity());
        setListAdapter(mAdapter);

        mAdapter.update();

		return rootView;
	}
}
