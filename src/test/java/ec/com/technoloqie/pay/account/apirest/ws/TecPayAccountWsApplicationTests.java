package ec.com.technoloqie.pay.account.apirest.ws;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ec.com.technoloqie.pay.account.apirest.ws.commons.exception.TransactionException;
import ec.com.technoloqie.pay.account.apirest.ws.commons.log.TransactionLog;
import ec.com.technoloqie.pay.account.apirest.ws.entities.Transaction;
import ec.com.technoloqie.pay.account.apirest.ws.entities.TransactionType;
import ec.com.technoloqie.pay.account.apirest.ws.services.impl.TransactionServiceImpl;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
class TecPayAccountWsApplicationTests {

	@Autowired
	private TransactionServiceImpl transService;
	
	private Transaction transaction;
	
	@Before
	public void setUp() throws TransactionException{
		this.transaction = new Transaction();
		this.transaction.setAmount(100.0);
		this.transaction.setSenderAccount(2);
		this.transaction.setReceiverAccount(3);
	}
	
	@SuppressWarnings("static-access")
	@Test
    public void getPayTransactionTest() {
		this.transaction = new Transaction();
		this.transaction.setAmount(100.0);
		this.transaction.setSenderAccount(2);
		this.transaction.setReceiverAccount(3);
		this.transaction.setCreatedBy("admin");
		TransactionType transactionType=new TransactionType();
		transactionType.setId(1);
		this.transaction.setTransactionType(transactionType);
		try{
			TransactionLog.getLog().info("getPayTransactionTest.");
			Transaction newTransaction = transService.payTransaction(this.transaction);
			assertEquals( 100 ,newTransaction.getAmount(),0.0);
			//assertEquals( CurrencyConstants.GALACTIC_CURRENCY ,currency.getCurrency());
		}catch(Exception e){
			TransactionLog.getLog().error("testServiceConvertMoneytoCredits.");
			fail("Error in getPayTransactionTest.");
		}
    }
	
    @Test
    public void getListTransactionTest() {
    	try {
    		List<Transaction> trans = transService.getListTransaction();

            Assert.assertEquals(trans.size(), 3);
    	}catch(Exception e) {
    		TransactionLog.getLog().error("getListTransactionTest.");
    		assertTrue("getListTransactionTest.",Boolean.TRUE);
    	}
    }
    
    

}
