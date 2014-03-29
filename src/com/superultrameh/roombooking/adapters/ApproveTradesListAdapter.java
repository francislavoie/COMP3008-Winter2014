package com.superultrameh.roombooking.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.superultrameh.roombooking.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ApproveTradesListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<TradeHolder> tradeHolders;
    private List<Trade> trades;

    private static final Format timeFormatter = new SimpleDateFormat("hh:mmaa");
    private static final Format dateFormatter = new SimpleDateFormat("EEE, MMM dd, yyyy");

    public ApproveTradesListAdapter(Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
        tradeHolders = new ArrayList<TradeHolder>();
    }

    @Override
    public int getCount() {
        return tradeHolders.size();
    }

    @Override
    public Object getItem(int position) {
        return tradeHolders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        // create the view
        if (view == null)
            view = inflater.inflate(R.layout.trade_list_item, null);

        // get the views from xml
        TextView assigned = (TextView) view.findViewById(R.id.tradeItemAssigned);
        TextView claiming = (TextView) view.findViewById(R.id.tradeItemClaiming);
        TextView date = (TextView) view.findViewById(R.id.tradeItemDate);
        TextView time = (TextView) view.findViewById(R.id.tradeItemScheduledTime);
        TextView shiftPos = (TextView) view.findViewById(R.id.tradeItemPosition);

        // set the text of the views from the dataHolders
        assigned.setText(tradeHolders.get(position).assignedName);
        claiming.setText(tradeHolders.get(position).claimingName);
        date.setText(tradeHolders.get(position).date);
        time.setText(tradeHolders.get(position).time);
        shiftPos.setText(tradeHolders.get(position).position);

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

    private class TradeHolder {
        public String assignedName;
        public String claimingName;
        public String date;
        public String time;
        public String position;

        public TradeHolder(Trade trade) {
            assignedName = trade.mAssigned.mName;
            claimingName = trade.mClaimer.mName;
            // get date as string
            date = dateFormatter.format(trade.mDate.getTime());
            time = trade.mTime;
            position = trade.mPosition;
        }
    }

    public void update() {
        // refresh the list by first removing everything within
        tradeHolders.clear();
        // get an array of data from AppData
        trades = getTrades();
        // convert that array to an array of dataHolders
        for (Trade trade : trades) {
            tradeHolders.add(new TradeHolder(trade));
        }
    }

    private List<Trade> getTrades() {
        ArrayList<Trade> newTrades = new ArrayList<Trade>();

        // TODO: create/add some trades...
        Trade trade1 = new Trade(
                new Staff("Daniel MacNaughtan"),
                new Staff("Francis Lavoie"),
                (Calendar)Calendar.getInstance().clone(),
                "11:30am - 8:00pm",
                "Dish");
        
        newTrades.add(trade1);

        return newTrades;
    }

    /**
     * takes a list of data and returns the reversed list
     */
    private List<Trade> reverseData(List<Trade> data) {
        List<Trade> reversedData
                = new ArrayList<Trade>();

        for (int i = trades.size() - 1; i > -1; i--)
            reversedData.add(trades.get(i));

        return reversedData;
    }

    public class Staff {
        public String mName;

        public Staff(String name) {
            mName = name;
        }
    }

    public class Trade {
        public Staff mClaimer;
        public Staff mAssigned;
        public Calendar mDate;
        public String mTime;
        public String mPosition;

        public Trade(Staff claimer, Staff owner, Calendar date, String time, String position) {
            mClaimer = claimer;
            mAssigned = owner;
            mDate = date;
            mTime = time;
            mPosition = position;
        }
    }
}
