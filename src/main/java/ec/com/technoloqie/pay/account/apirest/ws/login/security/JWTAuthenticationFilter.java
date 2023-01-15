package ec.com.technoloqie.pay.account.apirest.ws.login.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import ec.com.technoloqie.pay.account.apirest.ws.commons.log.TransactionLog;
import ec.com.technoloqie.pay.account.apirest.ws.entities.UserDetailsImpl;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@SuppressWarnings("static-access")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		AuthCredential authCredential = new AuthCredential();
		
		try {
			authCredential = new ObjectMapper().readValue(request.getReader(), AuthCredential.class);
		}catch(Exception e) {
			TransactionLog.getLog().error("Error credenciales no enviadas.",e);
		}
		
		UsernamePasswordAuthenticationToken usernamePat = new UsernamePasswordAuthenticationToken(
				authCredential.getEmail(), authCredential.getPassword(), Collections.emptyList());
		
		return getAuthenticationManager().authenticate(usernamePat);
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDetails.getUsername(), userDetails.getPassword());
		
		response.addHeader("Authorization", "Bearer " + token);
		
		response.getWriter().flush();
	}
}
