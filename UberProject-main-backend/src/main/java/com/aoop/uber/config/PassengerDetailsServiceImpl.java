package com.aoop.uber.config;

import com.aoop.uber.dao.passenger.PassengerDao;
import com.aoop.uber.model.passenger.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PassengerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PassengerDao passengerDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Passenger passenger = passengerDao.findByEmail(email);
        if(passenger != null){
            return new CustomPassengerDetails(passenger);
        }
        throw new UsernameNotFoundException("passenger is not available");

    }
}
