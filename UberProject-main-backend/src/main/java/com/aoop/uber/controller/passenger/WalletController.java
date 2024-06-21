package com.aoop.uber.controller.passenger;

import com.aoop.uber.dao.passenger.PassengerDao;
import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.model.passenger.Wallet;
import com.aoop.uber.service.passenger.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/passenger/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private PassengerDao passengerDao;

    @PutMapping("/add")
    public ResponseEntity<Passenger> addMoney(@RequestBody  Wallet wallet, Principal principal){
        try{
            walletService.addMoney(principal.getName(), wallet.getMoney());
            return new ResponseEntity<>(passengerDao.findByEmail(principal.getName()), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
