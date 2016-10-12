package gt.edu.altamira.sam.entity.sec;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the NC_SEC_ROLE database table.
 * 
 */
@Entity
@Table(name = "SAM_SEC_ROLE")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1475549110004350132L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_ROLE", sequenceName = "SAM_SQ_SEC_ROLE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_ROLE")
	@Column(name = "ID_ROLE")
	private long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFICATION")
	private Date dateModification;

	@Column(name = "DES_ROLE")
	private String desRole;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "PUBLIC_ROLE")
	private boolean publicRole;

	// bi-directional many-to-one association to OptionRole
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<OptionRole> optionRoles;

	// bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<UserRole> userRoles;

	public Role() {
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

	public String getDesRole() {
		return this.desRole;
	}

	public void setDesRole(String desRole) {
		this.desRole = desRole;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean getPublicRole() {
		return this.publicRole;
	}

	public void setPublicRole(boolean publicRole) {
		this.publicRole = publicRole;
	}

	public List<OptionRole> getOptionRoles() {
		return this.optionRoles;
	}

	public void setOptionRoles(List<OptionRole> optionRoles) {
		this.optionRoles = optionRoles;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}