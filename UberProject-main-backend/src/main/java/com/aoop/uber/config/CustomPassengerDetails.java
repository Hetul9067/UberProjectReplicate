package com.aoop.uber.config;

import com.aoop.uber.model.passenger.Passenger;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Data
public class CustomPassengerDetails implements UserDetails {
    private Passenger passenger;

    public CustomPassengerDetails(Passenger passenger) {
        super();
        this.passenger = passenger;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(passenger.getRole());

        return Arrays.asList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return passenger.getPassword();
    }

    @Override
    public String getUsername() {
        return passenger.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
