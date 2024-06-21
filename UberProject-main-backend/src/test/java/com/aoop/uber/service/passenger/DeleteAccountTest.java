package com.aoop.uber.service.passenger;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteAccountTest {

    private static final String DELETE_ACCOUNT_URL = "http://localhost:8080/passenger/account/delete";
    private static final String JWT_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZWVtYW50QGdtYWlsLmNvbSIsImlhdCI6MTcxNDc5MTQ4NywiZXhwIjoxNzE0ODA5NDg3fQ.NJw4hkgNNT6mBD2iDoIpbn-1LLimli-24V8KMCV5nEqNLnkgyQuZ4jtIVH9X9oaXV2qTy1QfgbqyzqARQvLz9w";
    @Test
    void testDeleteAccount_Successful() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(JWT_TOKEN);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(DELETE_ACCOUNT_URL, HttpMethod.DELETE, requestEntity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Account deleted successfully", response.getBody());
    }
}
