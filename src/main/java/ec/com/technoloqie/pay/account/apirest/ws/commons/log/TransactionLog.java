package ec.com.technoloqie.pay.account.apirest.ws.commons.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionLog {
	private static final Logger logger = LogManager.getLogger(TransactionLog.class);
	private static final TransactionLog INSTANCIA = new TransactionLog();
	
	private TransactionLog(){
		//BasicConfigurator.configure();
	}
	
	public static void info(String parameter){
		logger.info(parameter);
	}
	
	public void debug(String parameter){
		logger.debug("This is debug : " + parameter);
	}
		
	public void warn(String parameter){
		logger.warn("This is warn : " + parameter);
	}
	
	public static void error(String parameter){
		logger.error(parameter);
	}
	
	public void fatal(String parameter){
		logger.fatal("This is fatal : " + parameter);
	}
	
	public static TransactionLog getLog(){
		return INSTANCIA;
	}
	public static void error(String string, Exception e) {
		error(string + e);
	}
	
}
