package com.airbnb.demo.controllers;


import com.airbnb.demo.entities.House;
import com.airbnb.demo.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HouseController {

    @Autowired
    HouseService houseService;

    @GetMapping(value = "/house")
    public ResponseEntity<List<House>> getAllHouses() {
        List<House> houseEntities = houseService.getAllHouses();
        if (houseEntities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ResponseEntity<List<House>> listResponseEntity = new ResponseEntity<List<House>>(houseEntities, HttpStatus.OK);
        return listResponseEntity;
    }


    @GetMapping(value ="/house/{id}")
    public ResponseEntity<House> getOneHouse(@PathVariable long id){
        House oneHouse = houseService.getOneHouse(id);
        if (oneHouse == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<House>(oneHouse, HttpStatus.OK);
    }

    @PostMapping(value = "/house")
    public ResponseEntity<House> newHouse(@RequestBody House houseRequest) {
        House houseResponse = houseService.saveHouse(houseRequest);

        return new ResponseEntity<House>(houseRequest, HttpStatus.OK);
    }

    @PutMapping(value="/house/{id}/edit")
    public ResponseEntity<House> editHosue(@PathVariable long id, @RequestBody House newHouseRequest){
        House currentHouse = houseService.getOneHouse(id);

        if (currentHouse == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentHouse.setUser(newHouseRequest.getUser());
        currentHouse.setTitle(newHouseRequest.getTitle());
        currentHouse.setDescription(newHouseRequest.getDescription());
        currentHouse.setAddress(newHouseRequest.getAddress());
        currentHouse.setBathRoomNumber(newHouseRequest.getBathRoomNumber());
        currentHouse.setBedRoomNumber(newHouseRequest.getBedRoomNumber());
        currentHouse.setPricePerNight(newHouseRequest.getPricePerNight());
        currentHouse.setPricePerMonth(newHouseRequest.getPricePerMonth());
        currentHouse.setTypeHouse(newHouseRequest.getTypeHouse());
        currentHouse.setTypeRoom(newHouseRequest.getTypeRoom());

        houseService.saveHouse(currentHouse);
        return new ResponseEntity<>(currentHouse, HttpStatus.OK);
    }

    @GetMapping(value ="/house/user/{id}")
    public ResponseEntity<List<House>> getHouseByUserId(@PathVariable long id){
        List<House> listHouse = houseService.getAllHousesByUserId(id);

        return new ResponseEntity<List<House>>(listHouse, HttpStatus.OK);
    }
}
