package com.aoop.uber.model.passenger.factoryPassenger;


import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.model.passenger.PassengerRegister;
import com.aoop.uber.model.passenger.builderPassenger.AdultPassengerBuilder;
import com.aoop.uber.model.passenger.builderPassenger.TeenPassengerBuilder;

public class PassengerFactory extends AbstractFactory{
    @Override
    public Passenger makeTeenPassenger(PassengerRegister passengerRegister) {
        TeenPassengerBuilder builder = new TeenPassengerBuilder();
        builder.buildNameAndGender(passengerRegister);
        builder.buildPhoneNumberAndEmail(passengerRegister);
        builder.buildPassword(passengerRegister);
        builder.buildWalletAndAdult(passengerRegister);
        return builder.getPassenger();
    }

    @Override
    public Passenger makeAdultPassenger(PassengerRegister passengerRegister) {
        AdultPassengerBuilder builder = new AdultPassengerBuilder();
        builder.buildNameAndGender(passengerRegister);
        builder.buildPhoneNumberAndEmail(passengerRegister);
        builder.buildPassword(passengerRegister);
        builder.buildWalletAndAdult(passengerRegister);
        return builder.getPassenger();


    }
}
