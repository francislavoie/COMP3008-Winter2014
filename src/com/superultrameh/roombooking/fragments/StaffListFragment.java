package com.superultrameh.roombooking.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.superultrameh.roombooking.R;
import com.superultrameh.roombooking.dialogs.StaffDetailDialog;

import java.util.ArrayList;
import java.util.List;

public class StaffListFragment extends ListFragment {

	final static int LIST_TYPE_NOTSET = -1;
	final static int LIST_TYPE_HOME = 0;
	final static int LIST_TYPE_MAP = 1;
	final static int LIST_TYPE_SEARCH = 2;
    //final static int LIST_TYPE_BOOKING = 3;

	public int mListType;
	private StaffListAdapter mAdapter;

    public static StaffListFragment newInstance(int page) {
        StaffListFragment frag = new StaffListFragment();
        Bundle args = new Bundle();
        args.putInt("listType", page);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListType = getArguments().getInt("listType", -1);
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_staff, container, false);

		if (mListType == LIST_TYPE_NOTSET) {
			Toast.makeText(getActivity(), "derp", Toast.LENGTH_SHORT).show();
			return rootView;
		}

		// setup the adapter
		mAdapter = new StaffListAdapter(getActivity(), mListType);
		setListAdapter(mAdapter);

		return rootView;
	}

	private class StaffListAdapter extends BaseAdapter {

		private Activity mActivity;
		private LayoutInflater mInflater;
		private int mType;
		private List<Staff> staffList;

		public StaffListAdapter(Activity activity, int listType) {
			mActivity = activity;
			mInflater = LayoutInflater.from(activity);
			mType = listType;
			staffList = getStaffList();
		}

		@Override
		public int getCount() {
			return staffList.size();
		}

		@Override
		public Object getItem(int position) {
			return staffList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {

			// create the view
			if (view == null)
				view = mInflater.inflate(R.layout.staff_list_item, null);

			// find the text views
			TextView name = (TextView) view.findViewById(R.id.staffItemName);
			TextView scheduledPosition = (TextView) view
					.findViewById(R.id.staffItemPosition);
			TextView scheduledTime = (TextView) view
					.findViewById(R.id.staffItemScheduledTime);
			TextView addInfoLabel = (TextView) view
					.findViewById(R.id.staffItemAdditionalInfoLabel);
			TextView addInfo = (TextView) view
					.findViewById(R.id.staffItemAdditionalInfo);

			// set the text views
			name.setText(staffList.get(position).getName());
			scheduledPosition.setText(staffList.get(position).getPosition());
			scheduledTime.setText(staffList.get(position).getStartTime()
					+ " - " + staffList.get(position).getEndTime());
			switch (mType) {
			case StaffListFragment.LIST_TYPE_HOME:
				addInfoLabel.setText("Time In");
				addInfo.setText(staffList.get(position).getTimeIn());
				addInfo.setTextColor(StaffListFragment.this.getActivity()
						.getResources().getColor(R.color.text_soft));
				break;
			case StaffListFragment.LIST_TYPE_MAP:
				addInfoLabel.setText("Status");
				addInfo.setText("Not in");
				addInfo.setTextColor(StaffListFragment.this.getActivity()
						.getResources().getColor(R.color.text_soft));
				break;
			case StaffListFragment.LIST_TYPE_SEARCH:
				addInfoLabel.setText("Reason");
				addInfo.setText(staffList.get(position).getReason());
				addInfo.setTextColor(StaffListFragment.this.getActivity()
						.getResources().getColor(R.color.text_soft));
				break;
			}

			// add on click listener
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					StaffDetailDialog dialog = new StaffDetailDialog(StaffListFragment.this.getActivity());
					dialog.show();
				}
			});

			return view;
		}

		private List<Staff> getStaffList() {
			ArrayList<Staff> staff = new ArrayList<Staff>();
			switch (mType) {
			case LIST_TYPE_HOME:
				staff.add(new Staff("Penny J", "9:00", "5:00", "8:55", "Server", ""));
				staff.add(new Staff("John J", "9:30", "5:30", "9:34", "Dish", ""));
				staff.add(new Staff("Carol L", "9:50", "6:00", "9:40", "Server", ""));
				break;
			case LIST_TYPE_MAP:
				staff.add(new Staff("Benois H", "11:30", "8:00", "", "Server", ""));
				staff.add(new Staff("John G", "12:00", "8:30", "", "Dish", ""));
				staff.add(new Staff("Manual L", "4:00", "10:00", "", "Host", ""));
				break;
			case LIST_TYPE_SEARCH:
				staff.add(new Staff("Bob J", "3:00", "7:00", "", "Dish", "Sick"));
				staff.add(new Staff("John H", "4:00", "10:00", "", "Cook", "Family Emerg."));
				break;
			case LIST_TYPE_NOTSET:
				break;
			}
			return staff;
		}

		private class Staff {

			private String mName;
			private String mStartTime;
			private String mEndTime;
			private String mTimeIn;
			private String mPosition;
			private String mReason;

			public Staff(String name, String startTime, String endTime,
					String timeIn, String position, String reason) {
				mName = name;
				mStartTime = startTime;
				mEndTime = endTime;
				mTimeIn = timeIn;
				mPosition = position;
				mReason = reason;
			}

			public String getName() {
				return mName;
			}

			public String getStartTime() {
				return mStartTime;
			}

			public String getEndTime() {
				return mEndTime;
			}

			public String getTimeIn() {
				return mTimeIn;
			}

			public String getPosition() {
				return mPosition;
			}

			public String getReason() {
				return mReason;
			}
		}
	}
}
