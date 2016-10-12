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
 * The persistent class for the NC_SEC_USER_STATUS database table.
 * 
 */
@Entity
@Table(name = "SAM_SEC_USER_STATUS")
@NamedQuery(name = "UserStatus.findAll", query = "SELECT u FROM UserStatus u")
public class UserStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1031243259471561489L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_USER_STATUS", sequenceName = "SAM_SQ_SEC_USER_STATUS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_USER_STATUS")
	@Column(name = "ID_USER_STATUS")
	private long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFICATION")
	private Date dateModification;

	@Column(name = "DES_USER_STATUS")
	private String desUserStatus;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "NAME_USER_STATUS")
	private String nameUserStatus;

	public UserStatus() {
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

	public String getDesUserStatus() {
		return this.desUserStatus;
	}

	public void setDesUserStatus(String desUserStatus) {
		this.desUserStatus = desUserStatus;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getNameUserStatus() {
		return this.nameUserStatus;
	}

	public void setNameUserStatus(String nameUserStatus) {
		this.nameUserStatus = nameUserStatus;
	}

}