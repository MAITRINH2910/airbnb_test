package com.airbnb.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "typeHouse")
    @NotBlank
    private String typeHouse;

    @Column(name = "typeRoom")
    @NotBlank
    private String typeRoom;

    @Column(name = "bedRoomNumber")
    @NotNull
    private int bedRoomNumber;

    @Column(name = "bathRoomNumber")
    @NotNull
    private int bathRoomNumber;

    @Column(name = "pricePerNight")
    @NotNull
    private float pricePerNight;

    @Column(name = "pricePerMonth")
    @NotNull
    private float pricePerMonth;

    @Column(name = "address")
    @NotBlank
    private String address;

    @Column(name = "maxGuest")
    private int maxGuest;

    @Column(name = "status")
    private boolean status = false;

    public House() {
    }

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "house_utilities",
//            joinColumns = @JoinColumn(name = "house_id"),
//            inverseJoinColumns = @JoinColumn(name = "utility_id"))
//    private Set<Utilities> utilities = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = true)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//    @Column(insertable = false, nullable = true)
//    private long userId;
//
//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeHouse() {
        return typeHouse;
    }

    public void setTypeHouse(String typeHouse) {
        this.typeHouse = typeHouse;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    public int getBedRoomNumber() {
        return bedRoomNumber;
    }

    public void setBedRoomNumber(int bedRoomNumber) {
        this.bedRoomNumber = bedRoomNumber;
    }

    public int getBathRoomNumber() {
        return bathRoomNumber;
    }

    public void setBathRoomNumber(int bathRoomNumber) {
        this.bathRoomNumber = bathRoomNumber;
    }

    public float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(float pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

//    public Set<Utilities> getUtilities() {
//        return utilities;
//    }
//
//    public void setUtilities(Set<Utilities> utilities) {
//        this.utilities = utilities;
//    }
}
