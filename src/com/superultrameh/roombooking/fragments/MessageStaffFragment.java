package com.superultrameh.roombooking.fragments;

import com.superultrameh.roombooking.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessageStaffFragment extends Fragment {

	public MessageStaffFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_message_staff, container, false);
		return rootView;
	}
}
