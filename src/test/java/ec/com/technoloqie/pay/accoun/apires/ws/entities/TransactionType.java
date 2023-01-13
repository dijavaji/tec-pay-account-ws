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
import javax.validation.constraints.Size;

@Entity
@Table(name="TRANSACTIONTYPE")
public class TransactionType implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="TRATYPID",nullable=false, unique=true)
	private Integer id;
	
	@NotEmpty(message ="no puede estar vacio")
	@Size(min=4, max=12, message = "debe tener una longitud de entre 4 y 12")
	@Column(name="NAME",nullable=false)
	private String nombre;
	
	@Column(name="DESCRIPTION",nullable=false)
    private String description;
	
	@Column(name="CREATED_BY",nullable=false)
	private String createdBy;
	
	//@NotNull(message= "no puede estar vacio")
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
	private static final long serialVersionUID = -3451540582484842505L;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
