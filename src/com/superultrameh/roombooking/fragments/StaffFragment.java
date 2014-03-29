package com.superultrameh.roombooking.fragments;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superultrameh.roombooking.R;
import com.superultrameh.util.PagerSlidingTabStrip;

public class StaffFragment extends Fragment implements ActionBar.TabListener {

	ViewPager mPager;
	StaffPagerAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_staff, container,
				false);

		// setup date
//		TextView dateText = (TextView) rootView.findViewById(R.id.staffDate);
//		long date = System.currentTimeMillis();
//		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy ", Locale.getDefault());
//		String dateString = sdf.format(date);
//		dateText.setText(dateString);
		
		// setup pager and adapter
		mAdapter = new StaffPagerAdapter(getFragmentManager());
		mPager = (ViewPager) rootView.findViewById(R.id.staffPager);
		mPager.setAdapter(mAdapter);

		// bind the tabs to the pager
		PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) rootView
				.findViewById(R.id.staffPagerTabs);
		tabs.setIndicatorColor(getActivity().getResources().getColor(R.color.HEADER_BLUE));
		tabs.setViewPager(mPager);

		return rootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mAdapter.clear(mPager);
	}

	@SuppressWarnings("unused")
	private void addTabs() {
		final ActionBar actionBar = getActivity().getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (int i = 0; i < mAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(mAdapter.getPageTitle(i)).setTabListener(this));
		}
	}

	@SuppressWarnings("unused")
	private void removeTabs() {
		final ActionBar actionBar = getActivity().getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.removeAllTabs();
	}

	private class StaffPagerAdapter extends FragmentPagerAdapter {

		private StaffListFragment[] mPages;
		private FragmentManager fm;

		public StaffPagerAdapter(FragmentManager fm) {
			super(fm);
			Log.d(null, "StaffPagerAdapter()");
			mPages = new StaffListFragment[getCount()];
			for (int i = 0; i < getCount(); i++)
				mPages[i] = null;
			this.fm = fm;
		}

		@Override
		public Fragment getItem(int i) {
			Log.d(null, "getItem()");
			mPages[i] = new StaffListFragment();
			mPages[i].setListType(i);
			return mPages[i];
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getActivity().getResources().getString(
						R.string.label_home);
			case 1:
				return getActivity().getResources().getString(
						R.string.label_map);
			case 2:
				return getActivity().getResources().getString(
						R.string.label_search);
            case 3:
                return getActivity().getResources().getString(
                        R.string.label_bookings);
			default:
				return "";
			}
		}

		public void clear(ViewGroup container) {

			Log.d(null, "CLEARING PAGES");

			FragmentTransaction ft = fm.beginTransaction();

			for (int i = 0; i < getCount(); i++) {

				final long itemId = getItemId(i);

				// Do we already have this fragment?
				String name = makeFragmentName(container.getId(), itemId);
				Fragment fragment = fm.findFragmentByTag(name);

				if (fragment != null) {
					Log.d(null, "FRAGMENT DETACHED");
					ft.detach(fragment);
				}
			}
			ft.commitAllowingStateLoss();
			ft = null;
		}

		private String makeFragmentName(int viewId, long index) {
			return "android:switcher:" + viewId + ":" + index;
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
}
