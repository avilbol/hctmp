package com.hallocasa.vo.dto;

/**
 * Data transfer object for locale entry, supporting locales managed bu hallocasa
 * @author avilbol
 */
public class LocaleEntryDTO {

	private String pnemonic;
	private String description;
	private String enUS;
	private String esES;
	private String deDE;
	
	public LocaleEntryDTO() {
		super();
	}

	public LocaleEntryDTO(String pnemonic) {
		super();
		this.pnemonic = pnemonic;
	}
	
	public String getPnemonic() {
		return pnemonic;
	}

	public void setPnemonic(String pnemonic) {
		this.pnemonic = pnemonic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnUS() {
		return enUS;
	}

	public void setEnUS(String enUS) {
		this.enUS = enUS;
	}

	public String getEsES() {
		return esES;
	}

	public void setEsES(String esES) {
		this.esES = esES;
	}

	public String getDeDE() {
		return deDE;
	}

	public void setDeDE(String deDE) {
		this.deDE = deDE;
	}

	@Override
	public String toString() {
		return "LocaleEntryDTO [pnemonic=" + pnemonic + ", description=" + description + ", enUS=" + enUS + ", esES="
				+ esES + ", deDE=" + deDE + "]";
	}
}
