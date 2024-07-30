package com.in28minutes.springboot.student_services.utils;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {
	
	private static final String SECRET = "QSAyNC15ZWFyLW9sZCBmYXRoZXIgaXMgdHJhdW1hdGlzZWQgYnkgdGhlIGxvc3Mgb2YgaGlzIGNvbGxlYWd1ZSB3aGVuIGhlIHdhcyB0aGlydGVlbg==";
	private static final long EXPIRATION_TIME = 60000;
	
	
	public static String generateToken(String userName) {
		return Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	            .signWith(getSignInKey())
	            .compact();
		
	}
	
	
	public static String extractUsername(String token) {
		 return Jwts.parserBuilder()
		            .setSigningKey(getSignInKey())
		            .build()
		            .parseClaimsJws(token)
		            .getBody()
		            .getSubject();
	}
	
	public static String extract(String token) {
		 return Jwts.parserBuilder()
		            .setSigningKey(getSignInKey())
		            .build()
		            .parseClaimsJws(token)
		            .getBody()
		            .getSubject();
	}
	
	
	private static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
}
