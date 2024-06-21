package com.aoop.uber.service.passenger;

import com.aoop.uber.dao.passenger.PassengerDao;
import com.aoop.uber.model.passenger.PassengerRegister;
import com.aoop.uber.service.passenger.PassengerRegisterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PassengerRegisterServiceTest {

    @Mock
    private PassengerDao passengerDao;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private PassengerRegisterServiceValidation passengerRegisterServiceValidation; // Mock this dependency

    @InjectMocks
    private PassengerRegisterService passengerRegisterService;

    @Test
    public void testCreateAccount_Success() {
        when(passwordEncoder.encode("John@123")).thenReturn("encodedPassword");
        when(passengerDao.save(any())).thenReturn(null); // Adjust this if necessary
        when(passengerRegisterServiceValidation.verifyAllTheDetails(any())).thenReturn("success");

        PassengerRegister passengerRegister = new PassengerRegister();
        passengerRegister.setName("John");
        passengerRegister.setGender("m");
        passengerRegister.setPhoneNumber(1214567890L);
        passengerRegister.setEmail("john@gmail.com");
        passengerRegister.setPassword("John@123");
        passengerRegister.setTeenAccount(false); // Corrected the method call

        ResponseEntity<String> response = passengerRegisterService.createAccount(passengerRegister);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
