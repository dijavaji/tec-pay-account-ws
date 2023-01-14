package ec.com.technoloqie.pay.account.apirest.ws.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.technoloqie.pay.account.apirest.ws.commons.exception.TransactionException;
import ec.com.technoloqie.pay.account.apirest.ws.commons.log.TransactionLog;
import ec.com.technoloqie.pay.account.apirest.ws.dao.ITransactionDao;
import ec.com.technoloqie.pay.account.apirest.ws.entities.Account;
import ec.com.technoloqie.pay.account.apirest.ws.entities.AccountTransaction;
import ec.com.technoloqie.pay.account.apirest.ws.entities.Transaction;
import ec.com.technoloqie.pay.account.apirest.ws.services.IAccountService;
import ec.com.technoloqie.pay.account.apirest.ws.services.IAccountTransactionService;
import ec.com.technoloqie.pay.account.apirest.ws.services.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService{
	
	@Autowired
	private ITransactionDao transDao;
	@Autowired
	private IAccountService serviceAccount;
	@Autowired
	private IAccountTransactionService serviceAccTran;

	@Override
	@Transactional(readOnly = true)
	public List<Transaction> getListTransaction() {
		return (List<Transaction>) transDao.findAll();
	}

	@Override
	@Transactional
	public Transaction createTransaction(Transaction transaction) {
		return transDao.save(transaction);
	}

	@Override
	@Transactional(readOnly = true)
	public Transaction getTransactionId(Integer code) {
		return transDao.findById(code).orElse(null);
	}

	@Override
	@Transactional
	public void deleteTransaction(Integer code) {
		transDao.deleteById(code);
	}

	@Override
	@Transactional
	public Transaction updateTransaction(Transaction transaction, int id) {
		Transaction existTrans = getTransactionId(id); //tenemos que comprobar si con la identificación dada existe en la db o no
		existTrans.setAmount(transaction.getAmount());
		existTrans.setReceiverAccount(transaction.getReceiverAccount());
		existTrans.setSenderAccount(transaction.getSenderAccount());
		existTrans.setTransactionDate(transaction.getTransactionDate());
		existTrans.setModifiedBy(transaction.getModifiedBy());
		existTrans.setModifiedDate(new Date());
		transDao.save(existTrans);
		return existTrans;	
	}

	@SuppressWarnings("static-access")
	@Override
	@Transactional
	public Transaction payTransaction(Transaction transaction) {
		Transaction operationNew = transaction;
		
		Account senderAccountUpdate = null;
		Account receiverAccountUpdate = null;
		
		AccountTransaction accountTransaction = null;
		
		try {
			Account senderAccount = serviceAccount.getAccountId(operationNew.getSenderAccount());
			
			Account receiverAccount = serviceAccount.getAccountId(operationNew.getReceiverAccount());
			
			if (senderAccount == null || receiverAccount==null) {
				TransactionLog.getLog().error("Error al realizar operacion de pago la cuenta no existe");
				throw new TransactionException("Error al realizar operacion de pago la cuenta no existe");
			}
			
			senderAccount.setBalance(senderAccount.getBalance()-operationNew.getAmount());
			senderAccount.setModifiedDate(new Date());
			senderAccount.setModifiedBy(operationNew.getCreatedBy());
			
			receiverAccount.setBalance(receiverAccount.getBalance() + operationNew.getAmount());
			receiverAccount.setModifiedDate(new Date());
			receiverAccount.setModifiedBy(operationNew.getCreatedBy());
			senderAccountUpdate = serviceAccount.createAccount(senderAccount);
			receiverAccountUpdate = serviceAccount.createAccount(receiverAccount);
			
			operationNew = this.createTransaction(transaction);
			
			accountTransaction = new AccountTransaction();
			accountTransaction.setTransactionId(operationNew.getId());
			accountTransaction.setAccountId(senderAccountUpdate.getId());
			accountTransaction.setCreatedBy(transaction.getCreatedBy());
			this.serviceAccTran.createTransaction(accountTransaction);
			
			AccountTransaction accTransCredit = new  AccountTransaction();
			accTransCredit.setTransactionId(operationNew.getId());
			accTransCredit.setAccountId(receiverAccount.getId());
			accTransCredit.setCreatedBy(transaction.getCreatedBy());
			this.serviceAccTran.createTransaction(accTransCredit);
			
			
		}catch(Exception e) {
			TransactionLog.getLog().error("Error al realizar operacion de pago.",e);
			throw new TransactionException("Error al realizar operacion de pago.", e);
		}
		return operationNew;
	}
}
