package com.superultrameh.roombooking.data;

import com.superultrameh.roombooking.model.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * Worked on by Becky and Francis
 *
 * Used to store all the room bookings done by the user.
 */
public class BookingsList {
    List<Booking> bookings;

    public BookingsList(){
        bookings = new ArrayList<Booking>();
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public List<Booking> deleteBooking(Booking booking){
        bookings.remove(booking);
        return bookings;
    }

    public List<Booking> getBookings(){
        return bookings;
    }
}
