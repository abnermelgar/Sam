package gt.edu.altamira.sam.entity.sec;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OptionUserPK  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3479000414771387701L;

	@Column(name = "ID_OPTION", insertable = false, updatable = false)
	private long idOption;

	@Column(name = "ID_USER", insertable = false, updatable = false)
	private long idUser;

	public OptionUserPK() {
	}

	public long getIdOption() {
		return idOption;
	}

	public void setIdOption(long idOption) {
		this.idOption = idOption;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OptionUserPK)) {
			return false;
		}
		OptionUserPK castOther = (OptionUserPK) other;
		return (this.idOption == castOther.idOption)
				&& (this.idUser == castOther.idUser);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idOption ^ (this.idOption >>> 32)));
		hash = hash * prime + ((int) (this.idUser ^ (this.idUser >>> 32)));

		return hash;
	}

}
