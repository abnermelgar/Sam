package gt.edu.altamira.sam.entity.sec;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "SAM_SEC_REPORT")
@NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
public class Report implements Serializable, Cloneable {

	private static final long serialVersionUID = 1284866923878098318L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_REPORT", sequenceName = "SAM_SQ_REPORT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_REPORT")
	@Column(name = "ID_REPORT")
	private long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFICATION")
	private Date dateModification;

	@Column(name = "DES_REPORT")
	private String desReport;

	@Column(name = "DOC_TYPE")
	private String docType;

	@Column(name = "GEN_NAME")
	private String genName;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "MIME_TYPE")
	private String mimeType;

	@Column(name = "NAME_REPORT")
	private String nameReport;

	@Column(name = "REPORT_SCHEME")
	private String reportScheme;

	@Column(name = "REPORT_HOST")
	private String reportHost;

	@Column(name = "REPORT_PORT")
	private int reportPort;

	@Column(name = "REPORT_PATH")
	private String reportPath;

	@Column(name = "ID_REF")
	private long idRef;

	@Column(name = "IS_ENCRYPT")
	private boolean encrypt;

	// bi-directional many-to-one association to ReportParam
	@OneToMany(mappedBy = "peport")
	private List<ReportParam> reportParams;

	// bi-directional many-to-one association to Status

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_STATUS")
	private Status status;

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

	public String getDesReport() {
		return desReport;
	}

	public void setDesReport(String desReport) {
		this.desReport = desReport;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getGenName() {
		return genName;
	}

	public void setGenName(String genName) {
		this.genName = genName;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getNameReport() {
		return nameReport;
	}

	public void setNameReport(String nameReport) {
		this.nameReport = nameReport;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ReportParam> getReportParams() {
		return reportParams;
	}

	public void setReportParams(List<ReportParam> reportParams) {
		this.reportParams = reportParams;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getReportScheme() {
		return reportScheme;
	}

	public void setReportScheme(String reportScheme) {
		this.reportScheme = reportScheme;
	}

	public String getReportHost() {
		return reportHost;
	}

	public void setReportHost(String reportHost) {
		this.reportHost = reportHost;
	}

	public int getReportPort() {
		return reportPort;
	}

	public void setReportPort(int reportPort) {
		this.reportPort = reportPort;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public long getIdRef() {
		return idRef;
	}

	public void setIdRef(long idRef) {
		this.idRef = idRef;
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}
}
