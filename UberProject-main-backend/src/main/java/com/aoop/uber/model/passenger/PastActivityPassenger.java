package com.aoop.uber.model.passenger;

import com.aoop.uber.model.passenger.BookRide.BookRide;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PastActivityPassenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pastActivityPassengerId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bookRideId")
    @JsonManagedReference
    private BookRide pastActivity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    @JsonBackReference
    private Passenger passenger;
}
