package com.aoop.uber.model.driver;

import com.aoop.uber.model.passenger.Wallet;
import com.aoop.uber.model.passenger.Passenger;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int driver_id;

    private String name;
    private int age;
    private String gender ;

    private long phoneNumber;
    private String email;

    private float rating;

    @OneToOne
    private Wallet wallet;



//    private NotificationDriver notification;

    private float currentRideFare;

    private double totalEarning;
    private String license;
    private boolean verifiedAccount;

    private boolean deleteAccount;

    private boolean statusAvailable;
    private String password;

    @OneToOne
    private Passenger passenger;


    private boolean onRide;
}
