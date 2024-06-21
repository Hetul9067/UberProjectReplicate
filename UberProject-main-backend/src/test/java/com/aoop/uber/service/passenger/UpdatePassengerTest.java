package com.aoop.uber.service.passenger;

import com.aoop.uber.model.passenger.BookRide.BookRide;
import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.model.passenger.Wallet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

public class UpdatePassengerTest {

    private static final String UPDATE_ACCOUNT_URL = "http://localhost:8080/passenger/account/update";
    private static final String JWT_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZWVtYW50QGdtYWlsLmNvbSIsImlhdCI6MTcxNDc5MTQ4NywiZXhwIjoxNzE0ODA5NDg3fQ.NJw4hkgNNT6mBD2iDoIpbn-1LLimli-24V8KMCV5nEqNLnkgyQuZ4jtIVH9X9oaXV2qTy1QfgbqyzqARQvLz9w"; // Replace with your JWT token

    @Test
    void testUpdatePassenger_Successful() throws Exception {
        // Request body
        Passenger passenger = new Passenger();
        passenger.setPassenger_id(1);
        passenger.setName("Hetull");
        passenger.setGender("m");
        passenger.setPhoneNumber(12345678900L);
        passenger.setEmail("hetul@gmail.com");
        passenger.setPassword("$2a$10$3SQTuczuPLGf.xIJ9n1UZ.123CcPROR1IWzUGX5Wmln8N7KRW4h76");
        passenger.setOn_ride(false);
        passenger.setRole("ROLE_USER");
        passenger.setTeenAccount(false);

        Wallet wallet = new Wallet();
        wallet.setWallet_id(1);
        wallet.setMoney(200.0);
        passenger.setWallet(wallet);

        BookRide bookRide = new BookRide();
        bookRide.setBookRideId(1);
        bookRide.setAcceptTheRide(false);
        passenger.setBookRide(bookRide);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(JWT_TOKEN);

        // Create request entity
        HttpEntity<Passenger> requestEntity = new HttpEntity<>(passenger, headers);

        // Send PUT request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(UPDATE_ACCOUNT_URL, HttpMethod.PUT, requestEntity, String.class);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("passenger updated successfully", response.getBody());
    }
}
