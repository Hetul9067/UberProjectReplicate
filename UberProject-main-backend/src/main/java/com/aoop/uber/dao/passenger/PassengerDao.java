package com.aoop.uber.dao.passenger;

import com.aoop.uber.model.passenger.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerDao extends JpaRepository<Passenger, Integer> {

    Passenger findByEmail(String email);

//    public boolean existsByEmail(String email);
    Passenger findByPhoneNumber(long phoneNumber);

    @Query(value = "SELECT * FROM Passenger P WHERE P.email=:email and P.password= :password", nativeQuery = true)
    Passenger findAccount(String email, String password);
}
