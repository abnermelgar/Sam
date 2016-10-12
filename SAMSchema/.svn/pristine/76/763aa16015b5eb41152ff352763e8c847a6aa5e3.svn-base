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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the NC_SEC_OPTIONS database table.
 * 
 */
@Entity
@Table(name = "SAM_SEC_OPTIONS")
@NamedQuery(name = "Option.findAll", query = "SELECT o FROM Option o")
public class Option implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3563404525198095336L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_OPTIONS", sequenceName = "SAM_SQ_SEC_OPTIONS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_OPTIONS")
	@Column(name = "ID_OPTION")
	private long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFICATION")
	private Date dateModification;

	private boolean menu;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "OPTION_DESC")
	private String optionDesc;

	@Column(name = "OPTION_NAME")
	private String optionName;

	@Column(name = "OPTION_ORDER")
	private int optionOrder;

	@Column(name = "OPTION_VIEW")
	private String optionView;

	@Column(name = "STYLE_CLASS")
	private String styleClass;

	@Column(name = "IS_PUBLIC_VIEW")
	private boolean publicView;

	// bi-directional many-to-one association to Option
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_OPTIONS")
	private Option option;

	// bi-directional many-to-one association to Option
	@OneToMany(mappedBy = "option", fetch = FetchType.LAZY)
	private List<Option> options;

	// bi-directional many-to-one association to OptionRole
	@OneToMany(mappedBy = "option", fetch = FetchType.LAZY)
	private List<OptionRole> optionRoles;

	public Option() {
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

	public boolean getMenu() {
		return this.menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getOptionDesc() {
		return this.optionDesc;
	}

	public void setOptionDesc(String optionDesc) {
		this.optionDesc = optionDesc;
	}

	public String getOptionName() {
		return this.optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public int getOptionOrder() {
		return this.optionOrder;
	}

	public void setOptionOrder(int optionOrder) {
		this.optionOrder = optionOrder;
	}

	public String getOptionView() {
		return this.optionView;
	}

	public void setOptionView(String optionView) {
		this.optionView = optionView;
	}

	public String getStyleClass() {
		return this.styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public Option getOption() {
		return this.option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public List<Option> getOptions() {
		return this.options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	

	public List<OptionRole> getOptionRoles() {
		return this.optionRoles;
	}

	public void setOptionRoles(List<OptionRole> optionRoles) {
		this.optionRoles = optionRoles;
	}

	

	public boolean isPublicView() {
		return publicView;
	}

	public void setPublicView(boolean publicView) {
		this.publicView = publicView;
	}

}