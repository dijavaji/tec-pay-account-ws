package ec.com.technoloqie.pay.account.apirest.ws.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ec.com.technoloqie.pay.account.apirest.ws.dao.IUserDao;
import ec.com.technoloqie.pay.account.apirest.ws.entities.User;
import ec.com.technoloqie.pay.account.apirest.ws.entities.UserDetailsImpl;

@Service
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private IUserDao usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User usuario = usuarioDao.findOneByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Error usuario con " + email+ " no existe") );
		return new UserDetailsImpl(usuario);
	}

}
