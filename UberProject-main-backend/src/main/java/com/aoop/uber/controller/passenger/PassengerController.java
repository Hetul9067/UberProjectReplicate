package com.aoop.uber.controller.passenger;

import com.aoop.uber.config.CustomPassengerDetails;
import com.aoop.uber.dao.passenger.PassengerDao;
import com.aoop.uber.model.passenger.BookRide.AcceptRide;
import com.aoop.uber.model.passenger.BookRide.BookRide;
import com.aoop.uber.model.passenger.BookRide.BookRideRequest;
import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.security.JwtHelper;
import com.aoop.uber.service.passenger.BookRideService;
import com.aoop.uber.service.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/passenger/account")
public class PassengerController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PassengerDao passengerDao;



    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private BookRideService bookRideService;

    @GetMapping("/")
    public ResponseEntity<Passenger> home(Principal principal){

        System.out.println(principal.getName());
        return new ResponseEntity<>(passengerDao.findByEmail(principal.getName()),HttpStatus.OK);

    }

    @PutMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> updateAccount(@RequestBody Passenger passenger){
        return passengerService.updatePassenger(passenger);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(Principal principal){
        return passengerService.deletePassenger(principal);
    }

    @PostMapping("/bookride")
    public BookRide bookride(@RequestBody BookRideRequest bookRideRequest, Principal principal){

        return  bookRideService.distanceFinder(bookRideRequest.getPickUpLocation(), bookRideRequest.getDestinationLocation(), principal);
//        return "total distance is "+ distance;
    }

    @PostMapping("/bookride/accept")
    public String bookRide(@RequestBody AcceptRide acceptRide, Principal principal){
        return bookRideService.acceptRide(acceptRide.isAcceptTheRide(),principal);

    }







}
