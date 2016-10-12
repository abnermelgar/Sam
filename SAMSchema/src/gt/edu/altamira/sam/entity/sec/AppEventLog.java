package gt.edu.altamira.sam.entity.sec;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the GEN_EVENTS_LOG database table.
 * 
 */
@Entity
@Table(name = "SAM_SEC_EVENT_LOGS")
@NamedQuery(name = "EventsLog.findAll", query = "SELECT e FROM AppEventLog e")
public class AppEventLog implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2392640745651846889L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_EVENT_LOGS", sequenceName = "SAM_SQ_SEC_EVENT_LOGS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_EVENT_LOGS")
	@Column(name = "ID_LOG")
	private long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Lob
	@Column(name = "NEW_OBJECT")
	private String newObject;

	@Lob
	@Column(name = "OLD_OBJECT")
	private String oldObject;

	@Column(name = "ENTITY_EVENT")
	private String entityEvent;

	@Column(name = "ENTITY_EVENT_ID")
	private String entityEventId;

	// bi-directional many-to-one association to Event
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "ID_EVENT")
	private AppEvent event;

	// bi-directional many-to-one association to UserLogin
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "ID_LOGIN")
	private UserLogin userLogin;

	public AppEventLog() {
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

	public String getNewObject() {
		return this.newObject;
	}

	public void setNewObject(String newObject) {
		this.newObject = newObject;
	}

	public String getOldObject() {
		return this.oldObject;
	}

	public void setOldObject(String oldObject) {
		this.oldObject = oldObject;
	}

	@XmlTransient
	public AppEvent getEvent() {
		return this.event;
	}

	public void setEvent(AppEvent event) {
		this.event = event;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getEntityEvent() {
		return entityEvent;
	}

	public void setEntityEvent(String entityEvent) {
		this.entityEvent = entityEvent;
	}

	public String getEntityEventId() {
		return entityEventId;
	}

	public void setEntityEventId(String entityEventId) {
		this.entityEventId = entityEventId;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

}