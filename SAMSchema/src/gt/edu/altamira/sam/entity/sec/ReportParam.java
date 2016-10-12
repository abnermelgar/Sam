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

@Entity
@Table(name = "SAM_SEC_REPORT_PARAM")
@NamedQuery(name = "ReportParam.findAll", query = "SELECT r FROM ReportParam r")
public class ReportParam implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7438038009067527625L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_REPORT_PARAM", sequenceName = "SAM_SQ_REPORT_PARAM")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_REPORT_PARAM")
	@Column(name = "ID_REP_PARAM")
	private long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFICATION")
	private Date dateModification;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "NAME_PARAM")
	private String nameParam;

	// bi-directional many-to-one association to Report
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_REPORT")
	private Report peport;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getNameParam() {
		return nameParam;
	}

	public void setNameParam(String nameParam) {
		this.nameParam = nameParam;
	}

	public Report getPeport() {
		return peport;
	}

	public void setPeport(Report peport) {
		this.peport = peport;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
