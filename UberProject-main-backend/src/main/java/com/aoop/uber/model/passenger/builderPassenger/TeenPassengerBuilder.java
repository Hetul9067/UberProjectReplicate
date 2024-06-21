package com.aoop.uber.model.passenger.builderPassenger;

import com.aoop.uber.model.passenger.BookRide.BookRide;
import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.model.passenger.PassengerRegister;
import com.aoop.uber.model.passenger.Wallet;


public class TeenPassengerBuilder implements IPassengerBuilder{

    private Passenger passenger;

    public TeenPassengerBuilder(){
        passenger = new Passenger();
    }

    @Override
    public void buildNameAndGender(PassengerRegister passengerRegister) {
        passenger.setName(passengerRegister.getName());
        passenger.setGender(passengerRegister.getGender());

    }

    @Override
    public void buildPhoneNumberAndEmail(PassengerRegister passengerRegister) {
        passenger.setPhoneNumber(passengerRegister.getPhoneNumber());
        passenger.setEmail(passengerRegister.getEmail());
    }

    @Override
    public void buildPassword(PassengerRegister passengerRegister) {
        passenger.setPassword(passengerRegister.getPassword());
    }

    @Override
    public void buildWalletAndAdult(PassengerRegister passengerRegister) {
        passenger.setWallet(new Wallet());
        passenger.setTeenAccount(passengerRegister.isTeenAccount());
        passenger.setBookRide(new BookRide());
    }

    @Override
    public Passenger getPassenger() {
        return this.passenger;
    }
}
