package ec.com.technoloqie.pay.accoun.apires.ws.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

/**
 * 
 * @author root
 *
 */
@Entity
@Table(name="ACCOUNTTRANSACTION")
public class AccountTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="ACCTRANID",nullable=false, unique=true)
	private Integer id;
	
	@Column(name="TRANSACTIONID",nullable=false)
    private Integer transactionId; 
	
	@Column(name="ACCOUNTID",nullable=false)
    private Integer accountId; 
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(name="CREATED_BY",nullable=false)
	private String createdBy;
	
	@Column(name="CREATEDDATE",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name="MODIFIEDBY")
	private String modifiedBy;
	
	@Column(name="MODIFIEDDATE")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	@Column(name="STATUS")
	private Boolean status;
	
    @PrePersist 
	public void prePersist() {
		createdDate = new Date();
		status = Boolean.TRUE;
	}

}
