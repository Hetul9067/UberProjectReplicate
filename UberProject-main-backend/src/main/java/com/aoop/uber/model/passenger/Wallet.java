package com.aoop.uber.model.passenger;

import com.aoop.uber.model.passenger.Passenger;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wallet_id;
    //wallet money
    private double money = 100;

    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY)
    @JsonBackReference
    private Passenger passenger;

}
