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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SAM_SEC_USER_DOCUMENT")
@NamedQuery(name = "DocumentUser.findAll", query = "SELECT d FROM DocumentUser d")
public class DocumentUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7182708982898142952L;

	public DocumentUser() {

	}

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "SAM_SEC_USER_DOCUMENT", sequenceName = "SAM_SQ_SEC_USER_DOCUMENT ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAM_SEC_USER_DOCUMENT")
	@Column(name = "ID_FILE")
	private long id;

	@Column(name = "FILE_NAME")
	private String name;

	@Column(name = "EXT_FILE")
	private String fileExtension;

	@Column(name = "MIME_TYPE")
	private String mimeType;

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

	@Lob
	@Column(name = "DATA_FILE")
	private byte[] dataFile;

	// bi-directional many-to-one association to User
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User user;

	// bi-directional many-to-one association to StatusType
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_DOCUMENT_TYPE")
	private DocumentType documentType;

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

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
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

	public byte[] getDataFile() {
		return dataFile;
	}

	public void setDataFile(byte[] dataFile) {
		this.dataFile = dataFile;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

}
