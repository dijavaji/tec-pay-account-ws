package ec.com.technoloqie.pay.accoun.apires.ws.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.pay.accoun.apires.ws.entities.AccountTransaction;

public interface IAccountTransactionDao  extends JpaRepository<AccountTransaction, Integer>{

}
