package com.aoop.uber.model.passenger.builderPassenger;

import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.model.passenger.PassengerRegister;


public interface IPassengerBuilder {
    public void buildNameAndGender(PassengerRegister passengerRegister);

    public void buildPhoneNumberAndEmail(PassengerRegister passengerRegister);

    public void buildPassword(PassengerRegister passengerRegister);



    void buildWalletAndAdult(PassengerRegister passengerRegister);

    public Passenger getPassenger();
}
