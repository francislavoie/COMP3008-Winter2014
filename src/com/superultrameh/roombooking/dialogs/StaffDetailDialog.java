package com.superultrameh.roombooking.dialogs;

import com.superultrameh.roombooking.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

public class StaffDetailDialog extends Dialog {

	public StaffDetailDialog(Context context) {
		super(context);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.booking_dialog);
	}
}
