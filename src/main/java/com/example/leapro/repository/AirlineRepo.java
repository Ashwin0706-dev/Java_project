package com.example.leapro.repository;

import com.example.leapro.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepo extends JpaRepository<Airline, Integer> {

}