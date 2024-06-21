package com.aoop.uber.service.passenger;

import com.aoop.uber.HardCodedValue;
import com.aoop.uber.dao.passenger.PassengerDao;
import com.aoop.uber.model.driver.Driver;
import com.aoop.uber.model.passenger.BookRide.*;
import com.aoop.uber.model.passenger.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;

@Service
public class BookRideService {


    @Autowired
    private FareCalculatorService fareCalculatorService;

    @Autowired
    private PassengerDao passengerDao;

    @Autowired
    private WalletService walletService;
    public BookRide distanceFinder(String pickUpLocation, String destinationLocation, Principal principal) {
        Passenger passenger = passengerDao.findByEmail(principal.getName());
        passenger.getBookRide().setPickUpLocation(pickUpLocation);
        passenger.getBookRide().setDestinationLocation(destinationLocation);

        String duration = "";
        double distanceInKm = 0.0;
        RestTemplate restTemplate = new RestTemplate();

        try {
            String urlStr = HardCodedValue.DISTURL + HardCodedValue.ORIGINS + pickUpLocation + HardCodedValue.DESTINATION + destinationLocation + HardCodedValue.API_KEY;
            DistanceResponse response = restTemplate.getForObject(urlStr, DistanceResponse.class);
            System.err.println(response);
            if (response != null && response.getRows().size() > 0) {
                Element element = response.getRows().get(0).getElements().get(0);
                String distanceStr = element.getDistance().getText();
                duration = element.getDuration().getText();
                passenger.getBookRide().setDuration(duration);
                distanceInKm = Double.parseDouble(distanceStr.replaceAll("[^\\d.]", ""));
                passenger.getBookRide().setDistanceInKm(distanceInKm);

                passenger.getBookRide().setFareIn$(fareCalculatorService.calculateFare((float) distanceInKm, duration));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        passengerDao.save(passenger);
        return passenger.getBookRide();
    }

    public String acceptRide(boolean accept, Principal principal){
        if(accept){
            Passenger passenger = passengerDao.findByEmail(principal.getName());
            boolean deducted = walletService.deductMoney(principal.getName(), passenger.getBookRide().getFareIn$());
            if(deducted){
//                passenger.getBookRide().setAcceptTheRide(accept);
//                passengerDao.save(passenger);
                return "Sent request to the Driver!! now wait !! Thank you";
            }else{
                return "sorry! insufficient balance";
            }



        }
        return  "Please accept the ride";
    }


//    public double distanceFinder(String pickUpLocation, String destinationLocation) {
//        double distanceInMeters = 0.0;
//        HttpURLConnection conn = null;
//        BufferedReader reader = null;
//
//        try {
//            String urlStr = HardCodedValue.DISTURL + HardCodedValue.ORIGINS + pickUpLocation + HardCodedValue.DESTINATION + destinationLocation + HardCodedValue.API_KEY;
//            URL url = new URL(urlStr);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                String line;
//
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//
////                System.out.println(response.toString());
//
//                // Parse JSON response
//                try (JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
//                    JsonObject jsonResponse = jsonReader.readObject();
//
//                    // Extract distance from JSON response
//                    String distanceStr = jsonResponse.getJsonArray("rows")
//                            .getJsonObject(0)
//                            .getJsonArray("elements")
//                            .getJsonObject(0)
//                            .getJsonObject("distance")
//                            .getString("text");
//
//                    // Parse distance string to get the value in meters
//                    distanceInMeters = Double.parseDouble(distanceStr.replaceAll("[^\\d.]", ""));
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            } else {
//                System.out.println("HTTP error: " + conn.getResponseCode());
//            }
//        }catch (NullPointerException n){
//            System.out.println("Please enter correct location!");
//        }
//        catch (IOException io) {
//            io.printStackTrace();
//        } catch (JsonParsingException jsonParsingException) {
//            jsonParsingException.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // Close the connection and reader
//                if (conn != null) {
//                    conn.disconnect();
//                }
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return distanceInMeters;
//    }
}
