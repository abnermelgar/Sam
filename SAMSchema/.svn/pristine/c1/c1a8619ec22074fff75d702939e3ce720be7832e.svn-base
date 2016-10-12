package gt.edu.altamira.sam.entity.sec;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the NC_SEC_USER_LOGIN database table.
 * 
 */
@Entity
@Table(name = "SAM_SEC_USER_LOGIN")
@NamedQuery(name = "UserLogin.findAll", query = "SELECT u FROM UserLogin u")
public class UserLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6898314342477942483L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_USER_LOGIN", sequenceName = "SAM_SQ_SEC_USER_LOGIN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_USER_LOGIN")
	@Column(name = "ID_LOGIN")
	private long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Column(name = "USER_ACTION")
	private String userAction;

	@Column(name = "USER_AGENT")
	private String userAgent;

	@Column(name = "USER_BROWSER")
	private String userBrowser;

	@Column(name = "USER_DIVICE")
	private String userDivice;

	@Column(name = "USER_IP")
	private String userIp;

	@Column(name = "USER_OS")
	private String userOs;

	// bi-directional many-to-one association to User

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User user;

	public UserLogin() {
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

	public String getUserAction() {
		return this.userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserBrowser() {
		return this.userBrowser;
	}

	public void setUserBrowser(String userBrowser) {
		this.userBrowser = userBrowser;
	}

	public String getUserDivice() {
		return this.userDivice;
	}

	public void setUserDivice(String userDivice) {
		this.userDivice = userDivice;
	}

	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserOs() {
		return this.userOs;
	}

	public void setUserOs(String userOs) {
		this.userOs = userOs;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}