package ec.com.technoloqie.pay.accoun.apires.ws.services;

import ec.com.technoloqie.pay.accoun.apires.ws.entities.Account;

public interface IAccountService {
	
	Account createAccount(Account account);
	Account getAccountId(Integer code);
	Account updateAccount(Account account, int id);

}
