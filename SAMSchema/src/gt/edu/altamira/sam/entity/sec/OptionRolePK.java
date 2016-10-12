package gt.edu.altamira.sam.entity.sec;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the NC_SEC_OPTION_ROLE database table.
 * 
 */
@Embeddable
public class OptionRolePK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -932332219410294781L;

	// default serial version id, required for serializable classes.
	

	@Column(name = "ID_OPTION", insertable = false, updatable = false)
	private long idOption;

	@Column(name = "ID_ROLE", insertable = false, updatable = false)
	private long idRole;

	public OptionRolePK() {
	}

	public long getIdOption() {
		return this.idOption;
	}

	public void setIdOption(long idOption) {
		this.idOption = idOption;
	}

	public long getIdRole() {
		return this.idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OptionRolePK)) {
			return false;
		}
		OptionRolePK castOther = (OptionRolePK) other;
		return (this.idOption == castOther.idOption)
				&& (this.idRole == castOther.idRole);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idOption ^ (this.idOption >>> 32)));
		hash = hash * prime + ((int) (this.idRole ^ (this.idRole >>> 32)));

		return hash;
	}
}