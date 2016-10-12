package gt.edu.altamira.sam.entity.sec;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author amelgar
 * 
 */
@Entity
@Table(name = "SAM_SEC_PARAMETERS")
@NamedQuery(name = "Parameter.findAll", query = "SELECT r FROM Parameter r")
public class Parameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5964369387375078465L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_PARAMETERS", sequenceName = "SAM_SQ_SEC_PARAMETERS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_PARAMETERS")
	@Column(name = "ID_PARAMETER")
	private long id;

	@Column(name = "NAME_PARAMETER")
	private String name;

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

	// bi-directional many-to-one association to Status
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_ESTADO")
	private Status status;

	// bi-directional many-to-one association to OptionRole
	@OneToOne(mappedBy = "parameter", fetch = FetchType.LAZY)
	private ParameterDet parameters;

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

	public ParameterDet getParameters() {
		return parameters;
	}

	public void setParameters(ParameterDet parameters) {
		this.parameters = parameters;
	}

}
