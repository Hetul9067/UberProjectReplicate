package com.aoop.uber.model.passenger;

import com.aoop.uber.model.passenger.BookRide.BookRide;
import com.fasterxml.jackson.annotation.*;
//import jakarta.persistence.*;
import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int passenger_id;
    private String name;
    private String gender;
    private long phoneNumber;
    private String email;
    private String password;
    private boolean isTeenAccount;
    private boolean on_ride = false;
    private String role;
    //private Payment payment;

//    private String notification;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
    @JsonManagedReference
    private Wallet wallet;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name= "bookRideId")
    @JsonManagedReference
    private BookRide bookRide;


    @OneToMany(mappedBy = "passenger", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    @JsonManagedReference
    @JsonIgnore
    private List<PastActivityPassenger> pastActivity = new ArrayList<PastActivityPassenger>();









}

