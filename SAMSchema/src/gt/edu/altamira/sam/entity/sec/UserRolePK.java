package gt.edu.altamira.sam.entity.sec;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the NC_SEC_USER_ROLE database table.
 * 
 */
@Embeddable
public class UserRolePK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8562899216040789291L;

	// default serial version id, required for serializable classes.

	@Column(name = "ID_USER", insertable = false, updatable = false)
	private long idUser;

	@Column(name = "ID_ROLE", insertable = false, updatable = false)
	private long idRole;

	public UserRolePK() {
	}

	public long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
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
		if (!(other instanceof UserRolePK)) {
			return false;
		}
		UserRolePK castOther = (UserRolePK) other;
		return (this.idUser == castOther.idUser)
				&& (this.idRole == castOther.idRole);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idUser ^ (this.idUser >>> 32)));
		hash = hash * prime + ((int) (this.idRole ^ (this.idRole >>> 32)));

		return hash;
	}

	@Override
	public String toString() {

		return idUser + "-" + idRole;
	}
}