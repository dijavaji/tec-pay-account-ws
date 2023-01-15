package ec.com.technoloqie.pay.account.apirest.ws.login.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Cuando el cliente desee utilizar un token respondido y poder utilizar endpoinds de nuestra api
 * @author root
 *
 */
@Component	//cargar roles o datos permisos de verificacion antes de consultar los endpoints
public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String bearerToken = request.getHeader("Authorization");
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.replace("Bearer ", "");
			UsernamePasswordAuthenticationToken usernamePat = TokenUtils.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(usernamePat); 
		}
		filterChain.doFilter(request, response);
	}

}
