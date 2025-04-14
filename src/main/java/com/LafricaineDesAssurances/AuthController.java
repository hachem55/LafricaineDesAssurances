package com.LafricaineDesAssurances;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;  // JwtService that generates tokens

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        // Check the username and password (this is just an example; you might want to authenticate properly)
        if ("demo".equals(authRequest.getUsername()) && "password".equals(authRequest.getPassword())) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));  // Send the token back in the response
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
