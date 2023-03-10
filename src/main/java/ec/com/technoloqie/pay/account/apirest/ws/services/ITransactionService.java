package ec.com.technoloqie.pay.account.apirest.ws.services;

import java.util.List;

import ec.com.technoloqie.pay.account.apirest.ws.entities.Transaction;

public interface ITransactionService {
	
	List<Transaction> getListTransaction();
	Transaction createTransaction(Transaction transaction);
	Transaction getTransactionId(Integer code);
    void deleteTransaction(Integer code);
    Transaction updateTransaction(Transaction transaction, int id);
    Transaction payTransaction(Transaction transaction);

}
