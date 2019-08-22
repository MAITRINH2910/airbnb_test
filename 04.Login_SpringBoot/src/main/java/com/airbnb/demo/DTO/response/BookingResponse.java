package com.airbnb.demo.DTO.response;

import com.airbnb.demo.entities.House;

import java.util.Date;

public class BookingResponse {

    private boolean setStatus;
    private Date checkInDate;
    private Date checkOutDate;
    private float price;
//    private House house;


    public BookingResponse(Date checkInDate, Date checkOutDate, float price) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.price = price;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
