package io.javabrains.authentication.service;

import io.javabrains.authentication.model.AuthenticatedRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    public final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String getToken(String userName){
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, userName);
    }

    public String generateToken(Map<String, Object> claims, String userName){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
        return Jwts.builder().claims(claims)
                .subject(userName).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(key)
                .compact();
    }

}
