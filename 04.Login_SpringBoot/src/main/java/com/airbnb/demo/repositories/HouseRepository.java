package com.airbnb.demo.repositories;

import com.airbnb.demo.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    List<House> findAll();

    House findAllById(long id);

    List<House> findHousesByUserId(long id);

    House save(House houseEntity);

    void deleteById(long id);

    List<House> findAllByBedRoomNumber(int bedRoomNumber);

    List<House> findAllByBathRoomNumber(int bathRoomNumber);

    List<House> findAllByAddress(String address);

}
