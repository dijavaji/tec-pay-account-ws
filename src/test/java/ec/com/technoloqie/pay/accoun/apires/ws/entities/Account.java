package ec.com.technoloqie.pay.accoun.apires.ws.entities;

import java.io.Serializable;
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
@Table(name="ACCOUNT")
public class Account implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="ACCOUNTID",nullable=false, unique=true)
	private Integer id;
	
	@Column(name="ACCNUMBER",nullable=false, unique=true)
    private Integer accNumber; 
	
	@Column(name="BALANCE",nullable=false)
	private Double balance;
	
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
	
    //@OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)//, cascade = CascadeType.ALL
	//@JsonManagedReference
	/*@OneToMany(fetch = FetchType.LAZY	)
	@JoinColumn(name = "ENTERPRISE_ID")
	private List<Department> departmentCol;
    
	public Enterprise() {
		this.departmentCol = new ArrayList<>() ;
	}*/
	
    @PrePersist 
	public void prePersist() {
		createdDate = new Date();
		status = Boolean.TRUE;
	}


	private static final long serialVersionUID = -979071489122757786L;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getAccNumber() {
		return accNumber;
	}


	public void setAccNumber(Integer accNumber) {
		this.accNumber = accNumber;
	}


	public Double getBalance() {
		return balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	
}
