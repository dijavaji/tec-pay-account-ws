package ec.com.technoloqie.pay.accoun.apires.ws.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ec.com.technoloqie.pay.accoun.apires.ws.dao.IAccountDao;
import ec.com.technoloqie.pay.accoun.apires.ws.entities.Account;
import ec.com.technoloqie.pay.accoun.apires.ws.services.IAccountService;

public class AccountServiceImpl implements IAccountService{
	
	@Autowired
	private IAccountDao accountDao;

	@Override
	public Account createAccount(Account account) {
		return accountDao.save(account);
	}

	@Override
	public Account getAccountId(Integer code) {
		return accountDao.findById(code).orElse(null);
	}
	

}
