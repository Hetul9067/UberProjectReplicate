package com.aoop.uber.service.passenger;

import com.aoop.uber.controller.passenger.LogoutController;
import com.aoop.uber.dao.passenger.BookRideDao;
import com.aoop.uber.dao.passenger.PassengerDao;
import com.aoop.uber.dao.passenger.WalletDao;
import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.model.passenger.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class PassengerService {

    @Autowired
    private LogoutController logoutController;

    @Autowired
    private PassengerDao passengerDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private BookRideDao bookRideDao;



    public ResponseEntity<String> updatePassenger(Passenger updatedPassenger ){
        try{
//            Passenger passenger = passengerDao.findByEmail(updatedPassenger.getEmail());
//            pa
            passengerDao.save(updatedPassenger);
            walletDao.save(updatedPassenger.getWallet());
            bookRideDao.save(updatedPassenger.getBookRide());

//            return new ResponseEntity<>(passengerDao.findByEmail(updatedPassenger.getEmail()), HttpStatus.OK);
            return new ResponseEntity<>("passenger updated successfully", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("passenger is not updated", HttpStatus.BAD_REQUEST);


    }


    public ResponseEntity<String> deletePassenger(Principal principal) {
        try{
            passengerDao.delete(passengerDao.findByEmail(principal.getName()));

            ResponseEntity<?> logoutResponse = logoutController.logout("Bearer "+extractJwtToken(principal));
            return ResponseEntity.ok("Account Successfully Deleted and Logged Out");
        }catch (Exception e){
            e.printStackTrace();

        }
        return ResponseEntity.notFound().build();
    }

    private String extractJwtToken(Principal principal) {
        // Extract JWT token from Authorization header
        String token = principal.getName();
        return token.substring(7);
    }
}
