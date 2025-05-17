package org.example.details;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Component
public class JWTTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private long validityInMilliseconds = 3600000;

    public String generateToken(String username){
        Date currTime = new Date();
        Date validity = new Date(currTime.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currTime)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsername(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jws<Claims> claimsJws = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }
}
