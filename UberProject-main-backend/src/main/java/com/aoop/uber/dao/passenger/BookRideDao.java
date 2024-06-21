package com.aoop.uber.dao.passenger;

import com.aoop.uber.model.passenger.BookRide.BookRide;
import com.aoop.uber.model.passenger.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRideDao extends JpaRepository<BookRide, Integer> {

}
