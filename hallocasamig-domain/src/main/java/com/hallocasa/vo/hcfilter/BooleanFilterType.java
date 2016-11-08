package com.hallocasa.vo.hcfilter;

/**
 * VO representing a Boolean filter type
 * @author avillamil
 */
public class BooleanFilterType extends HcFilterType {

	private static final long serialVersionUID = 2578633934796452856L;
	
	private Boolean useYesNoDropdown;
	private Boolean useCheckbox;
	private Boolean useRadio;
	private Boolean useText;
	private Boolean toggle;
	private Boolean sortSign;
	
	public Boolean isUseYesNoDropdown() {
		return useYesNoDropdown;
	}
	public void setUseYesNoDropdown(Boolean useYesNoDropdown) {
		this.useYesNoDropdown = useYesNoDropdown;
	}
	public Boolean isUseCheckbox() {
		return useCheckbox;
	}
	public void setUseCheckbox(Boolean useCheckbox) {
		this.useCheckbox = useCheckbox;
	}
	public Boolean isUseRadio() {
		return useRadio;
	}
	public void setUseRadio(Boolean useRadio) {
		this.useRadio = useRadio;
	}
	public Boolean isUseText() {
		return useText;
	}
	public void setUseText(Boolean useText) {
		this.useText = useText;
	}
	public Boolean isToggle() {
		return toggle;
	}
	public void setToggle(Boolean toggle) {
		this.toggle = toggle;
	}
	public Boolean isSortSign() {
		return sortSign;
	}
	public void setSortSign(Boolean sortSign) {
		this.sortSign = sortSign;
	}
}
