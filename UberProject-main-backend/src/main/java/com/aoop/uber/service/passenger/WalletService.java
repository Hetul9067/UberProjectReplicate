package com.aoop.uber.service.passenger;

import com.aoop.uber.dao.passenger.PassengerDao;
import com.aoop.uber.model.passenger.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private PassengerDao passengerDao;

    public void addMoney(String email, double amount) {
        Passenger passenger = passengerDao.findByEmail(email);
        double balance = passenger.getWallet().getMoney();
        balance += amount;
        passenger.getWallet().setMoney(balance);
        passengerDao.save(passenger);
    }

    public boolean hasEnoughMoney(double balance, double amount) {

        return balance >= amount;
    }

    public boolean deductMoney(String email, double amount) {
        Passenger passenger = passengerDao.findByEmail(email);
        double balance = passenger.getWallet().getMoney();

        if (hasEnoughMoney(balance, amount)) {
            balance -= amount;
            System.out.println("balance : " +balance);
            passenger.getWallet().setMoney(balance);
            passenger.getBookRide().setAcceptTheRide(true);
            passengerDao.save(passenger);
            return true;
        } else {
            return false;
        }
    }



}
