package com.aoop.uber.service.passenger;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FareCalculatorService {
    private final float BASE_FARE = 2.0f; // Base fare in dollars per mile
    private final float RATE_PER_KM = 1.5f; // Rate per mile in dollars
    private final float RATE_PER_MINUTE = 0.5f; // Rate per minute in dollars
    private final float SURGE_MULTIPLIER = 1.5f; // Surge pricing multiplier

    public float calculateFare(float distanceInKm, String duration) {
        int durationInMinutes = convertToMinutes(duration);
        boolean isSurge = generateRandomSurge(); // Determine surge randomly
        float fare = BASE_FARE + (RATE_PER_KM * distanceInKm) + (RATE_PER_MINUTE * durationInMinutes);
        if (isSurge) {
            fare *= SURGE_MULTIPLIER;
        }
        return fare;
    }

    public int convertToMinutes(String timeString) {
        String[] parts = timeString.split(" ");
        int totalMinutes = 0;

        if (parts.length == 4) { // Format: X hours Y mins
            int hours = Integer.parseInt(parts[0]); // Extract hours
            int minutes = Integer.parseInt(parts[2]); // Extract minutes
            totalMinutes = hours * 60 + minutes; // Convert hours to minutes and add minutes
        } else if (parts.length == 2 && parts[1].equals("mins")) { // Format: X mins
            totalMinutes = Integer.parseInt(parts[0]); // Extract minutes
        } else {
            System.out.println("Invalid time format: " + timeString);
        }

        return totalMinutes;
    }
    private boolean generateRandomSurge() {
        Random random = new Random();
        return random.nextBoolean(); // Randomly return true or false
    }

}
