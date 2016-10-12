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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the NC_SEC_USER database table.
 * 
 */
@Entity
@Table(name = "SAM_SEC_USER")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4680192733044790318L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_USER", sequenceName = "SAM_SQ_SEC_USER")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_USER")
	@Column(name = "ID_USER")
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFICATION")
	private Date dateModification;

	private String email;

	@Column(name = "LAST_NAMES")
	private String lastNames;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	private String password;

	@Column(name = "USER_ALIAS")
	private String userAlias;

	@Column(name = "USER_NAMES")
	private String userNames;

	@Transient
	private String fullName;

	// bi-directional many-to-one association to UserStatus
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_USER_STATUS")
	private UserStatus userStatus;

	// bi-directional many-to-one association to UserType
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_USER_TYPE")
	private UserType userType;

	// bi-directional many-to-one association to DocumentUser
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<DocumentUser> documentUser;

	// bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserRole> userRoles;

	public User() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastNames() {
		return this.lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserAlias() {
		return this.userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}

	public String getUserNames() {
		return this.userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public UserStatus getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<DocumentUser> getDocumentUser() {
		return documentUser;
	}

	public void setDocumentUser(List<DocumentUser> documentUser) {
		this.documentUser = documentUser;
	}

	public String getFullName() {

		if (getUserNames() != null && !getUserNames().equals(""))

			fullName = getUserNames() + " " + getLastNames();

		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}