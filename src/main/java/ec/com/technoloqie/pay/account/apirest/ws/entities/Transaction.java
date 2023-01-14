package ec.com.technoloqie.pay.account.apirest.ws.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author root
 *
 */
@Entity
@Table(name="TRANSACTION")
public class Transaction implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="TRANSACTIONID",nullable=false, unique=true)
	private Integer id;
	
	@Column(name="AMOUNT",nullable=false)
    private Double amount; 
	
	@Column(name="SENDERACCOUNT",nullable=false)
	private Integer senderAccount;
	
	@Column(name="RECEIVERACCOUNT",nullable=false)
	private Integer receiverAccount;
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(name="CREATEDBY",nullable=false)
	private String createdBy;
	
	@Column(name="TRANSACTIONDATE",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
	@Column(name="MODIFIEDBY")
	private String modifiedBy;
	
	@Column(name="MODIFIEDDATE")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	@Column(name="STATUS")
	private Boolean status;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TRATYPID", nullable = false)
	private TransactionType transactionType;
	
	@Transient
	private Double receiverAccountBalance;
	
	@Transient
	private Double senderAccountBalance;
		
    @PrePersist 
	public void prePersist() {
    	transactionDate = new Date();
		status = Boolean.TRUE;
	}
    
    private static final long serialVersionUID = -9173571752922030736L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Integer senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Integer getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Integer receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Double getReceiverAccountBalance() {
		return receiverAccountBalance;
	}

	public void setReceiverAccountBalance(Double receiverAccountBalance) {
		this.receiverAccountBalance = receiverAccountBalance;
	}

	public Double getSenderAccountBalance() {
		return senderAccountBalance;
	}

	public void setSenderAccountBalance(Double senderAccountBalance) {
		this.senderAccountBalance = senderAccountBalance;
	}
   

}
