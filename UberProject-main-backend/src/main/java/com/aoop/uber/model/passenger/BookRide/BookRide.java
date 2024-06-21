package com.aoop.uber.model.passenger.BookRide;

import com.aoop.uber.model.driver.Driver;
import com.aoop.uber.model.passenger.Passenger;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookRide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookRideId;
    private String pickUpLocation ;
    private String destinationLocation;
//    private List<DriverU> availableDriverU = new ArrayList<>();


    private String requestDriverU;

    private float fareIn$;
    private double distanceInKm;
    private String duration;
    private boolean acceptTheRide= false;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bookRide")
    @JsonBackReference
    private Passenger passenger;
}
