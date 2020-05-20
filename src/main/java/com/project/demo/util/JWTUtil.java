package com.project.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to handel all the JWT related functions
 */
@Service
public class JWTUtil {

    private String SECRET_KEY = "shubham tiwari";

    public String extractUserName(String token){
        final Claims claims = extractAllClaims(token);
        if (claims==null)
            return null;
        return claims.getSubject();
    }

    public Date extractExpiration(String token){
        final Claims claims = extractAllClaims(token);
        if (claims==null)
            return null;
        return claims.getExpiration();
    }

    public Date extractIssuedDate(String token){
        final Claims claims = extractAllClaims(token);
        if (claims==null)
            return null;
        return claims.getIssuedAt();
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        }catch (Exception  e){
            System.out.println("EEEEE:"+e);
            return null;
        }
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userName);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *60*10))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    public Boolean validateToken(String token){
        final String userName = extractUserName(token);
        final Date issueDate = extractIssuedDate(token);
        final Date expiryDate = extractExpiration(token);
        Map<String,Object> claims = new HashMap<>();
        String createToken = Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(issueDate)
                .setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();

        return token.equals(createToken) && !isTokenExpired(token);

    }
}

