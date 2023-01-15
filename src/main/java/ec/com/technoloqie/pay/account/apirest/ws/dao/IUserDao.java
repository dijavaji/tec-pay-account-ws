package ec.com.technoloqie.pay.account.apirest.ws.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.technoloqie.pay.account.apirest.ws.entities.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{
	
	Optional<User> findOneByEmail(String email);

}
