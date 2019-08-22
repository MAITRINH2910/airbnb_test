package com.airbnb.demo.services.Impl;

import com.airbnb.demo.entities.House;
import com.airbnb.demo.repositories.HouseRepository;
import com.airbnb.demo.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    HouseRepository houseRepository;

    @Override
    public List<House> getAllHouses(){
        return houseRepository.findAll();
    }

    @Override
    public List<House> getAllHousesByUserId(long id) {
        return houseRepository.findHousesByUserId(id);
    }


    @Override
    public House getOneHouse(long id){
        return houseRepository.findAllById(id);
    }

    @Override
    public List<House> findByBedRoomsNumber(int bedRoomNumber){
        return houseRepository.findAllByBedRoomNumber(bedRoomNumber);
    }

    @Override
    public void remove(long id){
        houseRepository.deleteById(id);
    }

    @Override
    public House saveHouse(House houseEntity){
        return houseRepository.save(houseEntity);
    }
}
