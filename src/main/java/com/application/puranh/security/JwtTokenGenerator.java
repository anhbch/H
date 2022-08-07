package com.application.puranh.security;

import com.application.puranh.model.MyUserDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenGenerator {

    private final Logger logger = LoggerFactory.getLogger(JwtTokenGenerator.class);

    private final String JWT_SECRET = "DemoApplicationSecretKey123456789123456789123456";

    private final long JWT_EXPIRATION = 604800000L;

    public String tokenGenerator (MyUserDetails userDetails) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }


    public String getUserNameFromJwtToken (String token) {
        String username = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();

        return username;
    }

    public boolean validateToken (String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        }  catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}
