package com.aoop.uber.service.passenger;

import com.aoop.uber.dao.passenger.PassengerDao;
import com.aoop.uber.model.passenger.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import main.Repository.PassengerRepository;
//import main.passenger.Passenger;
//import main.passenger.PassengerRegister;

import java.util.List;

@Service
public class UserAuthenticationPassengerService {

//    private List<Passenger> passengers;
//    private String username;
//    private String password;

    @Autowired
    PassengerDao passengerDao;



    // Constructors, getters, and setters
//
//    public UserAuthenticationPassengerService(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

//    public UserAuthenticationPassengerService(){
//
//    }

    public ResponseEntity<Passenger> verifyPassengerAccount(String email, String password){

        try{

            Passenger passenger = passengerDao.findAccount(email, password);
            if(passenger != null) return new ResponseEntity<>(passenger, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
    public boolean verifyPassengerAccountByEmail(String email) {


        if(passengerDao.findByEmail(email) != null)  return !passengerDao.findByEmail(email).getEmail().equals(email) ;
        return true;
    }

    public boolean verifyPassengerAccountByPhone(long phone){

        if(passengerDao.findByPhoneNumber(phone) != null) return passengerDao.findByPhoneNumber(phone).getPhoneNumber() != phone ;
        return true;
    }
//    public void createPassenger(PassengerRegisterService register){
//
//
//
//    }


}
