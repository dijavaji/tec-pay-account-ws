package ec.com.technoloqie.pay.account.apirest.ws.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.com.technoloqie.pay.account.apirest.ws.entities.Transaction;
import ec.com.technoloqie.pay.account.apirest.ws.services.ITransactionService;

@CrossOrigin(origins = {"http://127.0.0.1:4200"})
@RestController
@RequestMapping("/api")
public class TransactionRestController {
	
	@Autowired
	private ITransactionService transService;
	
	private final Logger log = LoggerFactory.getLogger(TransactionRestController.class);
	
	@PostMapping("/payTransaction")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> payTransaction(@Valid @RequestBody Transaction transaction, BindingResult result) {
		Transaction transactionNew = null;
		Map <String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo " + err.getField() +" "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			transactionNew = transService.payTransaction(transaction);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al momento de crear operacion de pago");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Operacion de pago creada correctamente");
		response.put("cliente", transactionNew);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
}
