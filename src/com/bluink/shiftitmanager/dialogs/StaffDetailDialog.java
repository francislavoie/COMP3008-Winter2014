package com.bluink.shiftitmanager.dialogs;

import com.bluink.shiftitmanager.R;

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
		setContentView(R.layout.dialog_staff_detail);
	}
}
