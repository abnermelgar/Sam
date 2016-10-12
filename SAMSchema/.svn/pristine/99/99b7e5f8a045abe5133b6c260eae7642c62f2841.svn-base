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

@Entity
@Table(name = "SAM_SEC_OPTION_USER")
@NamedQuery(name = "OptionUser.findAll", query = "SELECT o FROM OptionUser o")
public class OptionUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 190066178904710245L;

	@EmbeddedId
	private OptionUserPK id;

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
	@JoinColumn(name = "ID_USER")
	private User user;

	public OptionUserPK getId() {
		return id;
	}

	public void setId(OptionUserPK id) {
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

	public boolean isHasDelete() {
		return hasDelete;
	}

	public void setHasDelete(boolean hasDelete) {
		this.hasDelete = hasDelete;
	}

	public boolean isHasEdit() {
		return hasEdit;
	}

	public void setHasEdit(boolean hasEdit) {
		this.hasEdit = hasEdit;
	}

	public boolean isHasNew() {
		return hasNew;
	}

	public void setHasNew(boolean hasNew) {
		this.hasNew = hasNew;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
