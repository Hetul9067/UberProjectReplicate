package com.aoop.uber.dao.passenger;

import com.aoop.uber.model.passenger.Passenger;
import com.aoop.uber.model.passenger.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletDao extends JpaRepository<Wallet, Integer> {

}
