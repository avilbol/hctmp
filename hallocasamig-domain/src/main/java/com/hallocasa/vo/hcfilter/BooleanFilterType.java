package com.hallocasa.vo.hcfilter;

/**
 * VO representing a boolean filter type
 * @author avillamil
 */
public class BooleanFilterType extends HcFilterType {

	private static final long serialVersionUID = 2578633934796452856L;
	
	private boolean useYesNoDropdown;
	private boolean useCheckbox;
	private boolean useRadio;
	private boolean useText;
	
	public boolean isUseYesNoDropdown() {
		return useYesNoDropdown;
	}
	public void setUseYesNoDropdown(boolean useYesNoDropdown) {
		this.useYesNoDropdown = useYesNoDropdown;
	}
	public boolean isUseCheckbox() {
		return useCheckbox;
	}
	public void setUseCheckbox(boolean useCheckbox) {
		this.useCheckbox = useCheckbox;
	}
	public boolean isUseRadio() {
		return useRadio;
	}
	public void setUseRadio(boolean useRadio) {
		this.useRadio = useRadio;
	}
	public boolean isUseText() {
		return useText;
	}
	public void setUseText(boolean useText) {
		this.useText = useText;
	}
}
