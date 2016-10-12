package gt.edu.altamira.sam.entity.sec;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the NC_SEC_USER_TYPE database table.
 * 
 */
@Entity
@Table(name = "SAM_SEC_USER_TYPE")
@NamedQuery(name = "UserType.findAll", query = "SELECT u FROM UserType u")
public class UserType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4006100451542690840L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_USER_TYPE", sequenceName = "SAM_SQ_SEC_USER_TYPE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_USER_TYPE")
	@Column(name = "ID_USER_TYPE")
	private long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFICATION")
	private Date dateModification;

	@Column(name = "DES_USER_TYPE")
	private String description;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "NAME_USER_TYPE")
	private String name;

	public UserType() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModification() {
		return this.dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}