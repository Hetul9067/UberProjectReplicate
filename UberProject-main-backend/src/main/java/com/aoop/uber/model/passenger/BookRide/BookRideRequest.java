package com.aoop.uber.model.passenger.BookRide;

import lombok.Data;

@Data
public class BookRideRequest {
    private String pickUpLocation ;
    private String destinationLocation;
}
