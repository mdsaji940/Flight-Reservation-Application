package com.SkTravelingBookingService.repository;



import com.SkTravelingBookingService.entity.UsersMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersMasterRepo extends JpaRepository<UsersMaster, Integer> {
    Optional<UsersMaster> findByEmail(String email);

}
