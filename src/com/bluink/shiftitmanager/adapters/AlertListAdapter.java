package com.bluink.shiftitmanager.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bluink.shiftitmanager.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AlertListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<AlertHolder> alertHolders;
    private List<Alert> alerts;

    private static final Format timeFormatter = new SimpleDateFormat("hh:mmaa");
    private static final Format dateFormatter = new SimpleDateFormat("EEE, MMM dd, yyyy");

    public AlertListAdapter(Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
        alertHolders = new ArrayList<AlertHolder>();
    }

    @Override
    public int getCount() {
        return alertHolders.size();
    }

    @Override
    public Object getItem(int position) {
        return alertHolders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // create the view
        if (view == null)
            view = inflater.inflate(R.layout.alert_list_item, null);

        // get the views from xml
        TextView assigned = (TextView) view.findViewById(R.id.alertItemAssigned);
        TextView date = (TextView) view.findViewById(R.id.alertItemDate);
        TextView time = (TextView) view.findViewById(R.id.alertItemScheduledTime);
        TextView shiftPos = (TextView) view.findViewById(R.id.alertItemPosition);
        TextView reason = (TextView) view.findViewById(R.id.alertItemAdditionalInfo);

        // set the text of the views from the dataHolders
        assigned.setText(alertHolders.get(position).name);
        date.setText(alertHolders.get(position).date);
        time.setText(alertHolders.get(position).time);
        shiftPos.setText(alertHolders.get(position).position);
        reason.setText(alertHolders.get(position).reason);

        // add on click listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: create and show a dialog
                Toast.makeText(activity, "Action unavailable", Toast.LENGTH_SHORT).show();
            }
        });

        // highlight the section if it is the current day
        // TODO: highlight the list item for today...

        return view;
    }

    public void update() {
        // refresh the list by first removing everything within
        alertHolders.clear();
        // get an array of data from AppData
        alerts = getAlerts();
        // convert that array to an array of dataHolders
        for (Alert alert : alerts) {
            alertHolders.add(new AlertHolder(alert));
        }
    }

    private List<Alert> getAlerts() {
        ArrayList<Alert> newAlerts = new ArrayList<Alert>();

        Alert alert1 = new Alert("Luis Lopez", "Sun, Mar 3, 2014", "9:00am - 5:00pm", "Dish", "Sick");
        Alert alert2 = new Alert("Daniel MacNaughtan", "Mon, Mar 4, 2014", "10:30am - 4:30pm", "Server", "Family Emergency");

        newAlerts.add(alert1);
        newAlerts.add(alert2);

        return newAlerts;
    }

    private class AlertHolder {
        public String name;
        public String date;
        public String time;
        public String position;
        public String reason;

        public AlertHolder(Alert alert) {
            name = alert.name;
            date = alert.date;
            time = alert.time;
            position = alert.position;
            reason = alert.reason;
        }
    }

    public class Alert {
        public String name;
        public String date;
        public String time;
        public String position;
        public String reason;

        public Alert(String name, String date, String time,
                     String pos, String reason) {
            this.name = name;
            this.date = date;
            this.time = time;
            this.position = pos;
            this.reason = reason;
        }
    }
}
