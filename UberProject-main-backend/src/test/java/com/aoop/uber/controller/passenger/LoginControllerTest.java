package com.aoop.uber.controller.passenger;

import com.aoop.uber.config.CustomPassengerDetails;
import com.aoop.uber.config.PassengerDetailsServiceImpl;
import com.aoop.uber.controller.passenger.LoginController;
import com.aoop.uber.model.JwtRequest;
import com.aoop.uber.model.JwtResponse;
import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.security.JwtHelper;
import com.aoop.uber.service.passenger.UserAuthenticationPassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private PassengerDetailsServiceImpl passengerDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtHelper jwtHelper;

    @Mock
    private UserAuthenticationPassengerService userAuthenticationPassengerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogin_Successful() {
        // Mocking
        JwtRequest request = new JwtRequest("seemant@gmail.com", "Seemant@123");
        Passenger passenger = new Passenger(); // Create an instance of Passenger
        passenger.setEmail("seemant@gmail.com");
        passenger.setPassword("Seemant@123");
        UserDetails userDetails = new CustomPassengerDetails(passenger);

        when(passengerDetailsService.loadUserByUsername(request.getEmail())).thenReturn(userDetails);
        when(jwtHelper.generateToken(userDetails)).thenReturn("mocked_token");

        // Testing
        ResponseEntity<JwtResponse> response = loginController.checkCredential(request);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("mocked_token", response.getBody().getJwtToken());
    }


    @Test
    void testLogin_AuthenticationError() {
        // Mocking
        JwtRequest request = new JwtRequest("error@gmail.com", "error_password");
        Passenger errorPassenger = new Passenger();
        errorPassenger.setEmail("error@gmail.com");
        errorPassenger.setPassword("error_password");
        when(passengerDetailsService.loadUserByUsername(request.getEmail())).thenReturn(new CustomPassengerDetails(errorPassenger));

        doThrow(BadCredentialsException.class).when(authenticationManager).authenticate(any());

        assertThrows(BadCredentialsException.class, () -> loginController.checkCredential(request));
    }

}
