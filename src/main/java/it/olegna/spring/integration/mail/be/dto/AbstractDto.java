package it.olegna.spring.integration.mail.be.dto;

import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractDto implements Serializable {

	private static final long serialVersionUID = -7187223842839446343L;
	private UUID pk;
	public UUID getPk() {
		return pk;
	}
	public void setPk(UUID pk) {
		this.pk = pk;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractDto other = (AbstractDto) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AbstractDto [pk=" + pk + "]";
	}
}