package com.aoop.uber.service.passenger;

import com.aoop.uber.model.passenger.PassengerRegister;
import lombok.ToString;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PassengerRegisterServiceValidation {

    @Autowired
    UserAuthenticationPassengerService userAuthenticationPassengerService;

    public String verifyAllTheDetails(PassengerRegister passengerRegister){
        if(!verifyName(passengerRegister.getName())) return "incorrect name";
        if(!verifyGender(passengerRegister.getGender())) {

            return "incorrect gender";
        }
        if(!verifyPhoneNumber(passengerRegister.getPhoneNumber()+"")) return "incorrect phone number";
        if(!verifyEmail(passengerRegister.getEmail())) return "incorrect email";
        if(!verifyPassword(passengerRegister.getPassword())) return "incorrect password";
        if(!verifiedAccountByEmail(passengerRegister.getEmail())) return "Email address already exist";
        if(!verifiedAccountByPhone(passengerRegister.getPhoneNumber())) return "Phone number alread exist in the database";

        return "success";

    }





    public boolean verifyName(String name)
    {
        return name != null  ;
    }
    public boolean verifyGender(String gender)
    {
        return  gender.toLowerCase().equals("male") || gender.toLowerCase().equals("female") || gender.toLowerCase().equals("other");
    }

    public boolean verifyPhoneNumber(String phoneNumber)
    {
        return phoneNumber.matches("^[1-9]\\d{9}$");
    }
    public boolean verifyEmail(String email)
    {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Define a simple regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a matcher object
        Matcher matcher = pattern.matcher(email);


        // Return true if the email matches the pattern
        return matcher.matches() ;
    }
    public boolean verifyPassword(String password)
    {
        if (password == null || password.length() < 8 || password.length() > 15) {
            return false;
        }

        // Check if the first character is uppercase
        if (!Character.isUpperCase(password.charAt(0))) {
            return false;
        }

        // Check if there is at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Check if there is at least one special character (you can customize the set of special characters)
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return false;
        }

        // All checks passed
        return true;
    }
    //no idea what to do here
    public boolean verifiedAccountByEmail(String email)
    {
        return userAuthenticationPassengerService.verifyPassengerAccountByEmail(email);
//        return false;
    }
    public boolean verifiedAccountByPhone(long phone){
        return userAuthenticationPassengerService.verifyPassengerAccountByPhone(phone);

    }
}
