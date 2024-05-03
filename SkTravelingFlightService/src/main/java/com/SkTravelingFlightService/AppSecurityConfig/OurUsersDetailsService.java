package com.SkTravelingFlightService.AppSecurityConfig;

import com.SkTravelingFlightService.repository.UsersMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OurUsersDetailsService implements UserDetailsService {

    @Autowired
    private UsersMasterRepo usersMasterRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersMasterRepo.findByEmail(username).orElseThrow();
    }
}
