package ec.com.technoloqie.pay.account.apirest.ws.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
	
	UserDetails loadUserByUsername(String email);
}
