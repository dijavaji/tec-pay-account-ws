package ec.com.technoloqie.pay.account.apirest.ws.services;

import ec.com.technoloqie.pay.account.apirest.ws.entities.AccountTransaction;

public interface IAccountTransactionService {
	
	AccountTransaction createTransaction(AccountTransaction acctran);

}
