package ec.com.technoloqie.pay.account.apirest.ws.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.pay.account.apirest.ws.entities.Account;

public interface IAccountDao extends JpaRepository<Account, Integer>{

}
