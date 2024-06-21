package com.aoop.uber.model;


import com.aoop.uber.model.passenger.Passenger;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {
    private String jwtToken;

    private Passenger passenger;
}
