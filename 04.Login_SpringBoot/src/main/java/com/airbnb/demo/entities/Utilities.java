//package com.airbnb.demo.entities;
//
//import org.hibernate.annotations.NaturalId;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="utilities")
//public class Utilities {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    @NaturalId
//    private UtilityName name;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public UtilityName getName() {
//        return name;
//    }
//
//    public void setName(UtilityName name) {
//        this.name = name;
//    }
//}