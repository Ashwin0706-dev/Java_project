package com.example.leapro.service;

import com.example.leapro.model.Luggage;
import com.example.leapro.repository.LuggageRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LuggageService {

    @Autowired
    private LuggageRepo luggageRepo;

    // 1. Create / Register luggage
    public String createLuggage(Luggage luggage) {

        luggage.setStatus("REGISTERED");
        luggage.setLastScanTime(LocalDateTime.now());

        luggageRepo.save(luggage);

        return "Luggage registered successfully";
    }

    // 2. Get all luggage
    public List<Luggage> getAllLuggage() {

        return luggageRepo.findAll();
    }

    // 3. Get luggage by ID
    public Luggage getLuggage(int id) {

        return luggageRepo.findById(id).orElse(null);
    }

    // 4. Scan luggage
   public String scanLuggage(int id, String location) {

    if (luggageRepo.existsById(id)) {

        Luggage luggage = luggageRepo.findById(id).get();

        luggage.setCurrentLocation(location);
        luggage.setLastScanTime(LocalDateTime.now());
        luggage.setStatus("IN_TRANSIT");

        luggageRepo.save(luggage);

        return "Baggage location updated successfully";
    }

    return "Baggage not found";
}

    // 5. Get status using tag
    public Luggage getStatus(String tag) {

    Luggage luggage =
            luggageRepo.findFirstByTagNumberIgnoreCase(tag.trim())
                    .orElse(null);

    if (luggage == null) {
        return null;
    }

    checkMisplacedStatus(luggage);

    return luggage;
}

    // 6. Check 4-hour misplaced condition
    private void checkMisplacedStatus(Luggage luggage) {

        if ("IN_TRANSIT".equals(luggage.getStatus())
                && luggage.getLastScanTime() != null) {

            Duration duration = Duration.between(
                    luggage.getLastScanTime(),
                    LocalDateTime.now()
            );

            if (duration.toHours() > 4) {

                luggage.setStatus("MISPLACED");

                luggageRepo.save(luggage);
            }
        }
    }

    // 7. Delete luggage
    public String deleteLuggage(int id) {

        if (luggageRepo.existsById(id)) {

            luggageRepo.deleteById(id);

            return "Luggage deleted successfully";
        }

        return "Luggage not found";
    }
}