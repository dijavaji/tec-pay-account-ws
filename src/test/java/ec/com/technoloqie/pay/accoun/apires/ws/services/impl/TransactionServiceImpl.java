package ec.com.technoloqie.pay.accoun.apires.ws.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.technoloqie.pay.accoun.apires.ws.dao.ITransactionDao;
import ec.com.technoloqie.pay.accoun.apires.ws.entities.Transaction;
import ec.com.technoloqie.pay.accoun.apires.ws.services.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService{
	
	@Autowired
	private ITransactionDao transDao;

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
}
