package ec.com.technoloqie.pay.account.apirest.ws.login.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import ec.com.technoloqie.pay.account.apirest.ws.commons.log.TransactionLog;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
	private final static String ACCES_TOKEN_SECRET = "$2a$10$gF8JddsF6E5rksxm/ujtueUx08qOAc0mESTsH2wu1A1OyBsqs1s/.";
	
	private final static long ACCES_TOKEN_VALIDITY_SECONDS= 76300000;
	
	public static String createToken(String nombre, String email) {
		long expirationTime = ACCES_TOKEN_VALIDITY_SECONDS * 1000;
		
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		
		Map< String, Object> extra = new HashMap<>();
		extra.put("nombre", nombre);
		
		return Jwts.builder().setSubject(email).setExpiration(expirationDate)
				.addClaims(extra).signWith(Keys.hmacShaKeyFor(ACCES_TOKEN_SECRET.getBytes())).compact();
		
	}
	
	@SuppressWarnings("static-access")
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(ACCES_TOKEN_SECRET.getBytes()).build().parseClaimsJws(token).getBody();
			
			String email = claims.getSubject();
			
			return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
		}catch(JwtException e) {
			TransactionLog.getLog().error("Error al realizar autenticacion token incorrecto invalido o expirado.",e);
			return null;
		}
	}
}
