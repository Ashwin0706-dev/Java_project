package com.example.leapro.repository;

import com.example.leapro.model.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LuggageRepo extends JpaRepository<Luggage, Integer> {

    Optional<Luggage> findFirstByTagNumberIgnoreCase(String tagNumber);

    boolean existsByTagNumber(String tagNumber);
}