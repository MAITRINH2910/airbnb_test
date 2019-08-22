package com.airbnb.demo.controllers;

import com.airbnb.demo.DTO.response.BookingResponse;
import com.airbnb.demo.entities.Booking;
import com.airbnb.demo.entities.House;
import com.airbnb.demo.services.HouseService;
import com.airbnb.demo.services.Impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;

    @Autowired
    HouseService houseService;

    @PostMapping(value = "/room/{id}")
    public ResponseEntity newBooking(@PathVariable long id, @RequestBody Booking bookingRequest) throws ParseException {
        float day = bookingRequest.getCheckOutDate().getTime() - bookingRequest.getCheckInDate().getTime();
        float days = (day / (1000 * 60 * 60 * 24));
        System.out.println(days);
        House house = houseService.getOneHouse(id);
        float price = days * house.getPricePerNight();

        house.setStatus(true);

//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDateTime now = LocalDateTime.now();
//        long dateNow = Long.parseLong(dtf.format(now));
//        if (bookingRequest.getCheckOutDate().getTime() < dateNow) {
//            house.setStatus(false);
//        }

        bookingRequest.setPrice(price);
        bookingRequest.setHouse(house);

//       return bookingService.saveBooking(bookingRequest);
//        return new ResponseEntity<Booking>(bookingService.saveBooking(bookingRequest), HttpStatus.OK);
        return ResponseEntity.ok(new BookingResponse(bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate(), price));
    }

    @GetMapping(value = "/room")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
    }
}
