package ec.com.technoloqie.pay.account.apirest.ws.commons.exception;

public class TransactionException extends RuntimeException{
	
	public TransactionException() {
        super();
    }
    
	public TransactionException(String msg, Throwable nested) {
        super(msg, nested);
    }
    
	public TransactionException(String message) {
        super(message);
    }
    
	public TransactionException(Throwable nested) {
        super(nested);
	}
	
	private static final long serialVersionUID = 1L;

}
