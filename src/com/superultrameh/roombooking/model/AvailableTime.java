package com.superultrameh.roombooking.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Worked on by Becky and Francis
 *
 * Manages a start and end time range for which a room is available to be booked.
 * Has a setter function for simpler usage using integers for the time values.
 */
public class AvailableTime {
    private Date startDateTime;
    private Date endDateTime;


    public AvailableTime() {
        this.startDateTime = new Date();
        this.endDateTime = new Date();
    }

    public AvailableTime(Date startDateTime, Date endDateTime) {

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setStartTime(Integer year, Integer month, Integer day, Integer hour, Integer min) {
        String dateStr = Integer.toString(year)+ "-" +Integer.toString(month)+ "-" +Integer.toString(day)+
                 "-" +Integer.toString(hour)+ "-" +Integer.toString(min);
        DateFormat readFormat = new SimpleDateFormat( "yyyy-mm-dd-hh-mm");
        try {
            this.startDateTime = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setEndTime(Integer year, Integer month, Integer day, Integer hour, Integer min) {
        String dateStr = Integer.toString(year)+ "-" +Integer.toString(month)+ "-" +Integer.toString(day)+
                "-" +Integer.toString(hour)+ "-" +Integer.toString(min);
        DateFormat readFormat = new SimpleDateFormat( "yyyy-mm-dd-hh-mm");
        try {
            this.endDateTime = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
