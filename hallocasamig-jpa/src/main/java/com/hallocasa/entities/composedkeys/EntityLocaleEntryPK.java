package com.hallocasa.entities.composedkeys;

/**
 * Composite primary key for locale entry element
 * @author avilbol
 */
public class EntityLocaleEntryPK {

	private String pnemonic;
	private String locale;
	
	public String getPnemonic() {
		return pnemonic;
	}
	
	public void setPnemonic(String pnemonic) {
		this.pnemonic = pnemonic;
	}
	
	public String getLocale() {
		return locale;
	}
	
	public void setLocale(String locale) {
		this.locale = locale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((pnemonic == null) ? 0 : pnemonic.hashCode());
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
		EntityLocaleEntryPK other = (EntityLocaleEntryPK) obj;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (pnemonic == null) {
			if (other.pnemonic != null)
				return false;
		} else if (!pnemonic.equals(other.pnemonic))
			return false;
		return true;
	}
}
