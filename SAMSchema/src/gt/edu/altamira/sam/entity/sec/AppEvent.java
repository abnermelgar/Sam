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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the GEN_EVENTS database table.
 * 
 */
@Entity
@Table(name = "SAM_SEC_EVENTS")
@NamedQuery(name = "AppEvent.findAll", query = "SELECT e FROM AppEvent e")
public class AppEvent implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6665583719614038838L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_EVENTS", sequenceName = "SAM_SQ_EVENT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_EVENTS")
	@Column(name = "ID_EVENT")
	private long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFICATION")
	private Date dateModification;

	@Column(name = "EVENT_DES")
	private String eventDes;

	@Column(name = "EVENT_NAME")
	private String eventName;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	// bi-directional many-to-one association to Status
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_STATUS")
	private Status status;

	public AppEvent() {
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

	public String getEventDes() {
		return this.eventDes;
	}

	public void setEventDes(String eventDes) {
		this.eventDes = eventDes;
	}

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}