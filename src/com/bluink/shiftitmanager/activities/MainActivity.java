package com.bluink.shiftitmanager.activities;

import com.bluink.shiftitmanager.R;
import com.bluink.shiftitmanager.fragments.AlertsFragment;
import com.bluink.shiftitmanager.fragments.ApproveTradesFragment;
import com.bluink.shiftitmanager.fragments.MessageStaffFragment;
import com.bluink.shiftitmanager.fragments.SettingsFragment;
import com.bluink.shiftitmanager.fragments.StaffFragment;
import com.bluink.shiftitmanager.fragments.TradeZoneFragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	// current list of drawer item constants
	private static final int FRAGMENT_NONE = -1;
	private static final int FRAGMENT_STAFF = 0;
	private static final int FRAGMENT_TRADE = 1;
	private static final int FRAGMENT_APPROVE = 2;
	private static final int FRAGMENT_MESSAGE = 3;
	private static final int FRAGMENT_ALERT = 4;
	private static final int FRAGMENT_SETTINGS = 5;
	
	// layout objects
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
	// drawer variables
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mSectionTitles;
	private int mPosition;
	
	// activity fragments
	private Fragment[] mSections;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initSections();
		initDrawer(savedInstanceState);
	}
	
	private void initSections() {
		// initialize fragments
		mSections = new Fragment[6];
		mSections[0] = new StaffFragment();
		mSections[1] = new TradeZoneFragment();
		mSections[2] = new ApproveTradesFragment();
		mSections[3] = new MessageStaffFragment();
		mSections[4] = new AlertsFragment();
		mSections[5] = new SettingsFragment();
	}
	
	private void initDrawer(Bundle savedInstanceState) {
		
		// setup titles
		mTitle = mDrawerTitle = getTitle();
		mSectionTitles = new String[6];
		mSectionTitles[0] = getResources().getString(R.string.title_staff);
		mSectionTitles[1] = getResources().getString(R.string.title_tradeZone);
		mSectionTitles[2] = getResources().getString(R.string.title_approveTrades);
		mSectionTitles[3] = getResources().getString(R.string.title_messageStaff);
		mSectionTitles[4] = getResources().getString(R.string.title_alerts);
		mSectionTitles[5] = getResources().getString(R.string.title_settings);

		// find layout objects in xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set drawer shadow
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mSectionTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setIcon(R.drawable.shiftit_icon_white);

		mDrawerToggle = new ActionBarDrawerToggle(
				this,
				mDrawerLayout,
				R.drawable.ic_drawer_white,
				R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		if (savedInstanceState == null) {
			mPosition = FRAGMENT_NONE;
			selectItem(FRAGMENT_STAFF, false);
		}
		
		// TODO: update app data
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        
        // TODO: Handle action buttons
        
        return super.onOptionsItemSelected(item);
    }
	
	@Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggels
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
	public void onBackPressed() {
		super.onBackPressed();
		// if about to exit app
		if (mPosition == FRAGMENT_STAFF) {
			finish();
		} else {
			// correct the current position and redraw the menu
			mPosition = findCurrentPosition();
			mDrawerList.setItemChecked(mPosition, true);
	        setTitle(mSectionTitles[mPosition]);
			invalidateOptionsMenu();
		}
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position, true);
		}
	}
	
	private void selectItem(int position, boolean animate) {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		if (animate) {
			ft.setCustomAnimations(
					R.animator.fragment_slide_left_enter,
	                R.animator.fragment_slide_left_exit,
	                R.animator.fragment_slide_right_enter,
	                R.animator.fragment_slide_right_exit);
		}
		if (mPosition != position) {
			ft.addToBackStack(null);
			mPosition = position;
			ft.replace(R.id.content_frame, mSections[position]);
		}
		ft.commit();
		
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mSectionTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
	
	private int findCurrentPosition() {
		for (int i=0; i<mSections.length; i++) {
			if (mSections[i].isAdded())
				return i;
		}
		return FRAGMENT_NONE;
	}
}
