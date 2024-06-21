package com.aoop.uber.service.passenger;

import com.aoop.uber.dao.passenger.PassengerDao;
import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.model.passenger.PassengerRegister;
import com.aoop.uber.model.passenger.factoryPassenger.PassengerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import main.Gender;
//import main.HardCodedValue;
//import main.Repository.PassengerRepository;
//import main.UserAuthenticationPassenger;
//import main.factoryPassenger.PassengerFactory;
//import main.handler.CustomException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class PassengerRegisterService {

    @Autowired
    PassengerDao passengerDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    PassengerRegisterServiceValidation passengerRegisterServiceValidation;

    public ResponseEntity<String> createAccount(PassengerRegister passengerRegister) {
        String responseStr="";
        try{
            responseStr = passengerRegisterServiceValidation.verifyAllTheDetails(passengerRegister);
            if(!responseStr.equals("success")) throw new Exception(responseStr);

            Passenger passenger;
            if(passengerRegister.isTeenAccount()){
                passenger = PassengerFactory.getPassengerFactory().makeTeenPassenger(passengerRegister);
            }else{
                passenger = PassengerFactory.getPassengerFactory().makeAdultPassenger(passengerRegister);
            }

            passenger.setPassword(passwordEncoder.encode(passenger.getPassword()));
            passenger.setRole("ROLE_USER");
            passengerDao.save(passenger);

            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseStr, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Passenger>> getAllThePassengers(){
        try{
            return new ResponseEntity<>(passengerDao.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

/*
    private String name;
    private String gender;
    private long phoneNumber = 0L;
    private String email;
    private String password;
    private UserAuthenticationPassenger userAuthentication;

    private Wallet wallet;

    private boolean adult;

    private Passenger passenger;
    public PassengerRegisterService(){

    }

    public void addWallet(){
        wallet = new Wallet();
        wallet.addMoney(HardCodedValue.MONEY);
    }
    public Gender selectGender(int ans){
        return ans == 1 ? Gender.MALE : Gender.FEMALE;
    }

    public Passenger displayRegistrationForm(){
        System.out.println("\n\n=============================================");
        System.out.println("Welcome to the Passenger Registration Form : ");
        System.out.println("=============================================\n\n");

        boolean check = false;
        do{
            try{
                check = false;
                if(name == null){
                    System.out.println("Enter name here : ");
                    name = HardCodedValue.SCANNER.nextLine();
                    if(!verifyName(name)){
                        name = null;
                        throw new CustomException("error3");

                    }


                }
                if(gender == null){
                    System.out.println("Select gender : ");
                    System.out.println("1. Male");
                    System.out.println("2. Female");
                    int ans = Integer.parseInt(HardCodedValue.SCANNER.nextLine());

                    if(ans < 1 || ans > 2) {
                        gender = null;
                        throw new CustomException("error1");

                    }
                    gender = ""+selectGender(ans);

                }
                if(phoneNumber == 0L){
                    System.out.println("Enter Phone number : ");
                    phoneNumber = Long.parseLong(HardCodedValue.SCANNER.nextLine());

                    if(!verifyPhoneNumber(phoneNumber+"")){
                        phoneNumber = 0L;
                        throw new CustomException("error4");

                    }
                    if(verifiedAccountByPhone(phoneNumber)){
                        phoneNumber = 0L;

                        System.out.println(HardCodedValue.ERROR8);
                        return null;
                    }
                }

                if(email == null){
                    System.out.println("Enter Email : ");
                    email = HardCodedValue.SCANNER.nextLine();
                    if(!verifyEmail(email)){
                        email = null;
                        throw new CustomException("error5");
                    }
                    if(verifiedAccountByEmail(email)) {
                        email = null;
                        System.out.println(HardCodedValue.ERROR7);
                        return null;
                    }
                }

                if(password == null){
                    System.out.println("Enter Password : ");
                    password = HardCodedValue.SCANNER.nextLine();
                    if(!verifyPassword(password)){
                        password = null;
                        throw new CustomException("error6");
                    }

                }

                System.out.println("select are you adult or not : ");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int ans = Integer.parseInt(HardCodedValue.SCANNER.nextLine());
                if(ans < 1 || ans > 2) throw new CustomException("error1");
                selectAdultOption(ans);


            }catch (CustomException ce){
                ce.processError(2);
                check = true;
            }catch (Exception e){
                System.out.println(HardCodedValue.ERROR2);
                check = true;
            }
        }while(check);

        addWallet();

        Passenger passenger = registerPassenger();
        if(passenger == null) {
            System.out.println("Sorry there are some issues in the database please try again to register!");
        }
        return passenger;


    }

    public void selectAdultOption(int ans){
        adult = (ans == 1);
    }
    public Passenger registerPassenger(){
        Passenger passenger1;
        if(adult){
            passenger1 = PassengerFactory.getPassengerFactory().makeAdultPassenger(this);
        }else{
            passenger1 = PassengerFactory.getPassengerFactory().makeTeenPassenger(this);
        }

        PassengerRepository.savePassenger(passenger1);
        return PassengerRepository.findRegisterPassengerByEmail(passenger1.getEmail());

    }

    public boolean verifyName(String name)
    {
        return name != null  ;
    }
    public boolean verifyGender(String gender)
    {
        return  gender.toLowerCase()=="m" || gender.toLowerCase()=="f";
    }

    public boolean verifyPhoneNumber(String phoneNumber)
    {
        return phoneNumber.matches("^[1-9]\\d{9}$");
    }
    public boolean verifyEmail(String email)
    {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Define a simple regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a matcher object
        Matcher matcher = pattern.matcher(email);


        // Return true if the email matches the pattern
        return matcher.matches() ;
    }
    public boolean verifyPassword(String password)
    {
        if (password == null || password.length() < 8 || password.length() > 15) {
            return false;
        }

        // Check if the first character is uppercase
        if (!Character.isUpperCase(password.charAt(0))) {
            return false;
        }

        // Check if there is at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Check if there is at least one special character (you can customize the set of special characters)
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return false;
        }

        // All checks passed
        return true;
    }
    //no idea what to do here
    public boolean verifiedAccountByEmail(String email)
    {
        return UserAuthenticationPassenger.getInstance().verifyPassengerAccountByEmail(email);
//        return false;
    }
    public boolean verifiedAccountByPhone(long phone){
        return UserAuthenticationPassenger.getInstance().verifyPassengerAccountByPhone(phone);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAuthenticationPassenger getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(UserAuthenticationPassenger userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public boolean isAdult() {
        return adult;
    }


    public void setAdult(boolean adult) {
        this.adult = adult;
    }
*/
}
