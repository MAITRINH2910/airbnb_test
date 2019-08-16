package com.airbnb.demo.services;

import com.airbnb.demo.entities.House;

import java.util.List;

public interface HouseService {
    List<House> getAllHouses();

    House getOneHouse(long id);

    List<House> findByBedRoomsNumber(int bedroomNumber);

    void remove(long id);

    House saveHouse(House houseEntity);
}
