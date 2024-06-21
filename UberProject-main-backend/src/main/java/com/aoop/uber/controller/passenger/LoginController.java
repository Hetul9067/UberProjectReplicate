package com.aoop.uber.controller.passenger;

import com.aoop.uber.config.CustomPassengerDetails;
import com.aoop.uber.config.PassengerDetailsServiceImpl;
import com.aoop.uber.dao.passenger.PassengerDao;
import com.aoop.uber.model.JwtRequest;
import com.aoop.uber.model.JwtResponse;
import com.aoop.uber.model.passenger.Login;
import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.security.JwtHelper;
import com.aoop.uber.service.passenger.UserAuthenticationPassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/passenger")
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PassengerDetailsServiceImpl passengerDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    UserAuthenticationPassengerService userAuthenticationPassengerService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> checkCredential(@RequestBody JwtRequest request){
        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = passengerDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .passenger(((CustomPassengerDetails)userDetails).getPassenger()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
//        return userAuthenticationPassengerService.verifyPassengerAccount(login.getEmail(), login.getPassword());

    }

    private void doAuthenticate(String email, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try{
            manager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(){
        return "Credentials Invalid !!";
    }

}
