package com.reservation.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private static final String SECRETKEY = "SecuritySecret";

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims =  new HashMap<>();
        claims.put("Authorities", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY)
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return extractExpirationDate(token).before(new Date());
    }

    public boolean isValidToken(String token, UserDetails userDetails){
        String userName = extractUsername(token);
        return userDetails.getUsername().equals(userName) && !isTokenExpired(token);
    }

    public Collection<? extends GrantedAuthority> extractAuthorities(String token){
        return extractClaim(token, claims -> claims.get("authorities",Collection.class));
    }
}
