package com.aoop.uber;


import java.util.Scanner;

public class HardCodedValue {
    public static String ADDRESS1 = "1280, St. Marc, Montreal, Ca";
    public static String ADDRESS2 = "750, Cote De la Place de Arms, Montreal, Ca";

    //distance matrix api url
    public static String DISTURL = "https://maps.googleapis.com/maps/api/distancematrix/json" ;
    public static String DESTINATION = "&destinations=";
    public static String ORIGINS = "?origins=";
    public static String UNITS = "&units=imperial";
    //geocoding api url
    public static String URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    //api key
    public static String API_KEY = "&key=AIzaSyAtVaN8nk82CEY1dlhTtDlxVlzWJv_WZ9Y";
    //initial money
    public static double MONEY = 10000;

    //singleton scanner object to use it in all the classes.
    public static Scanner SCANNER = new Scanner(System.in);

    //error message for type 1
    public static String ERROR1 = "Please enter value in between 1 to " ;


    //error message for type 2
    public static String ERROR2 = "##########################\n" +
            "Please enter valid input!\n" +
            "##########################";
    public static String ERROR3 = "Please enter name in correct format!";
    public static String ERROR4 = "Please enter correct mobile number!";
    public static String ERROR5 = "Please enter valid Email address!";
    public static String ERROR6 = "Please enter strong password (First character must be capital, at least contain one digit and one special character)!";

    public static String ERROR7 = "This email is already registered with us, please login using this email or use another email to register!";

    public static String ERROR8 = "This Phone number is already registered with us, please login using this phone number or use another phone number to register!";

    public static String ERROR9 = "Wrong username or password ! ";

    public static String ERROR10 = "Please enter valid pickup location!";
    public static String ERROR11 = "Please enter valid destination location!";
//
// return "CREATE TABLE IF NOT EXISTS DRIVER( " +
//         " DRIVER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
//         " NAME TEXT, AGE INTEGER, PHONENUMBER INTEGER UNIQUE," +
//         " EMAIL TEXT UNIQUE, RATING REAL, WALLET_ID INTEGER NOT NULL, NOTIFICATION_ID INTEGER NOT NULL, " +
//         " TOTAL_EARNING REAL, LICENSE TEXT, " +
//         " VERIFIED_ACCOUNT BOOLEAN, DELETE_ACCOUNT, " +
//         " STATUS_AVAILABLE BOOLEAN, PASSENGER_ID INTEGER, " +
//         " FOREIGN KEY (WALLET_ID) REFERENCES WALLETS(ID), " +
//         " FOREIGN KEY (NOTIFICATION_ID) REFERENCES NOTIFICATION_PASSENGERS(NOTIFICATION_ID), " +
//         " FOREIGN KEY (PASSENGER_ID) REFERENCES PASSENGERS;";

}
