package com.aoop.uber.service.passenger;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

public class AddAmountToWalletTest {

    private static final String ADD_AMOUNT_URL = "http://localhost:8080/passenger/wallet/add";
    private static final String JWT_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZWVtYW50QGdtYWlsLmNvbSIsImlhdCI6MTcxNDc5MTQ4NywiZXhwIjoxNzE0ODA5NDg3fQ.NJw4hkgNNT6mBD2iDoIpbn-1LLimli-24V8KMCV5nEqNLnkgyQuZ4jtIVH9X9oaXV2qTy1QfgbqyzqARQvLz9w";
    @Test
    void testAddAmountToWallet_Successful() {
        // Request body
        String requestBody = "{ \"money\": \"50\" }";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(JWT_TOKEN);

        // Create request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send POST request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(ADD_AMOUNT_URL, HttpMethod.POST, requestEntity, String.class);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Amount added to wallet successfully", response.getBody());
    }
}
