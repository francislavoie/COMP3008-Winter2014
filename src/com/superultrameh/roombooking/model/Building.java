package com.superultrameh.roombooking.model;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
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

import com.superultrameh.roombooking.R;
import com.superultrameh.roombooking.fragments.AlertsFragment;
import com.superultrameh.roombooking.fragments.ApproveTradesFragment;
import com.superultrameh.roombooking.fragments.MessageStaffFragment;
import com.superultrameh.roombooking.fragments.SettingsFragment;
import com.superultrameh.roombooking.fragments.StaffFragment;
import com.superultrameh.roombooking.fragments.TradeZoneFragment;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private String name;
    private List<Room> rooms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    public Building(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }


    public Building(){
        name = "";
        rooms = new ArrayList<Room>();
    }
}
