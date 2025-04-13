package com.LafricaineDesAssurances;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey; // Make sure to put this in your application.properties

    @Value("${jwt.expiration}")
    private long expirationTime; // Also in application.properties

    // Generate a JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
}
// Extract username from JWT
public String extractUsername(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
}

// Validate the JWT token
public boolean isTokenValid(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
}

// Check if the JWT token is expired
private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
}

// Extract expiration date from JWT token
private Date extractExpiration(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
}
}
