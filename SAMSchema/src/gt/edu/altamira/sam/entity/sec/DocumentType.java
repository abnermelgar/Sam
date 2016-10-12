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
@Table(name = "SAM_DOCUMENT_TYPE")
@NamedQuery(name = "DocumentType.findAll", query = "SELECT d FROM DocumentType d")
public class DocumentType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2963575963695552441L;

	public DocumentType() {

	}

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_DOCUMENT_TYPE", sequenceName = "SAM_SQ_DOCUMENT_TYPE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_DOCUMENT_TYPE")
	@Column(name = "ID_DOCUMENT_TYPE")
	private long id;

	@Column(name = "DESCRIPTION")
	private String name;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFICACTION")
	private Date dateModification;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	// bi-directional many-to-one association to Status
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_STATUS")
	private Status status;

	@Column(name = "APPLY_ALL")
	private boolean appliesAll;

	@Column(name = "APPLY_USER")
	private boolean appliesUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isAppliesAll() {
		return appliesAll;
	}

	public void setAppliesAll(boolean appliesAll) {
		this.appliesAll = appliesAll;
	}

	public boolean isAppliesUser() {
		return appliesUser;
	}

	public void setAppliesUser(boolean appliesUser) {
		this.appliesUser = appliesUser;
	}

}
