package com.LafricaineDesAssurances;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from your database (you may need to replace this)
        // User user = userRepository.findByUsername(username);
        // If user is not found, throw UsernameNotFoundException

        // For demo, let's return a mock user (replace with real database logic)
        if (username.equals("demo")) {
            return new User("demo", "password", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
