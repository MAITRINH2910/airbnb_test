package com.airbnb.demo.services;

import com.airbnb.demo.entities.Booking;


import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    Booking saveBooking(Booking booking);
}
