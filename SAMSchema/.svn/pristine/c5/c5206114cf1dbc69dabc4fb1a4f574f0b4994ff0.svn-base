package gt.edu.altamira.sam.entity.sec;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the NC_SEC_OPTION_ROLE database table.
 * 
 */
@Entity
@Table(name = "SAM_SEC_OPTION_ROLE")
@NamedQuery(name = "OptionRole.findAll", query = "SELECT o FROM OptionRole o")
public class OptionRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 864197587849882380L;

	@EmbeddedId
	private OptionRolePK id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFICATION")
	private Date dateModification;

	@Column(name = "HAS_DELETE")
	private boolean hasDelete;

	@Column(name = "HAS_EDIT")
	private boolean hasEdit;

	@Column(name = "HAS_NEW")
	private boolean hasNew;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	// bi-directional many-to-one association to Option
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_OPTION")
	private Option option;

	// bi-directional many-to-one association to Role
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_ROLE")
	private Role role;

	public OptionRole() {
	}

	public OptionRolePK getId() {
		return this.id;
	}

	public void setId(OptionRolePK id) {
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

	public boolean getHasDelete() {
		return this.hasDelete;
	}

	public void setHasDelete(boolean hasDelete) {
		this.hasDelete = hasDelete;
	}

	public boolean getHasEdit() {
		return this.hasEdit;
	}

	public void setHasEdit(boolean hasEdit) {
		this.hasEdit = hasEdit;
	}

	public boolean getHasNew() {
		return this.hasNew;
	}

	public void setHasNew(boolean hasNew) {
		this.hasNew = hasNew;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Option getOption() {
		return this.option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}