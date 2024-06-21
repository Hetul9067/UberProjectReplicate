package com.aoop.uber.model.passenger.BookRide;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

public class RideBookingTest {

    private static final String BOOK_RIDE_URL = "http://localhost:8080/passenger/account/bookride";
    private static final String JWT_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZWVtYW50QGdtYWlsLmNvbSIsImlhdCI6MTcxNDc5MTQ4NywiZXhwIjoxNzE0ODA5NDg3fQ.NJw4hkgNNT6mBD2iDoIpbn-1LLimli-24V8KMCV5nEqNLnkgyQuZ4jtIVH9X9oaXV2qTy1QfgbqyzqARQvLz9w"; // Replace with your JWT token

    @Test
    void testBookRide_Successful() {
        // Request body
        String pickUpLocation = "1280, St. Marc, Montreal, Ca";
        String destinationLocation = "750 cote de la place D'arms, Montreal, Ca";
        String requestBody = "{\"pickUpLocation\": \"" + pickUpLocation + "\", \"destinationLocation\": \"" + destinationLocation + "\"}";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(JWT_TOKEN);

        // Create request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send POST request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(BOOK_RIDE_URL, requestEntity, String.class);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Add more assertions based on the response body if needed
    }
}
