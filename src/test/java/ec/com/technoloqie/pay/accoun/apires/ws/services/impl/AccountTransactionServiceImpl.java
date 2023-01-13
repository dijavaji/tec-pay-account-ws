package ec.com.technoloqie.pay.accoun.apires.ws.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.technoloqie.pay.accoun.apires.ws.dao.IAccountTransactionDao;
import ec.com.technoloqie.pay.accoun.apires.ws.entities.AccountTransaction;
import ec.com.technoloqie.pay.accoun.apires.ws.services.IAccountTransactionService;

@Service
public class AccountTransactionServiceImpl implements IAccountTransactionService{
	
	@Autowired
	private IAccountTransactionDao accTranDao;
	
	@Override
	@Transactional
	public AccountTransaction createTransaction(AccountTransaction acctran) {
		return accTranDao.save(acctran);
	}

}
