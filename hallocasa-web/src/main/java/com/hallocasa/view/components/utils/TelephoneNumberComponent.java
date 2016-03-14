package com.hallocasa.view.components.utils;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.hallocasa.commons.vo.CountryTelephonePrefixVO;
import com.hallocasa.commons.vo.TelephoneVO;
import com.hallocasa.view.components.base.InputBaseComponent;

/**
 * Backing bean for tlephone number component
 * 
 * @author Alexander Villamil
 */
@FacesComponent("telephoneNumberComponent")
@ViewScoped
public class TelephoneNumberComponent extends InputBaseComponent {

	private enum Attributes {
		value, readOnly
	}

	private UIInput telephonePrefix;

	private UIInput telephoneNumber;

	@Override
	@PostConstruct
	protected void initialize() {

	}

	@Override
	protected void saveComponent(FacesContext facesContext, HashMap<String, Object> map) {

	}

	@Override
	protected void restoreComponent(FacesContext facesContext, HashMap<String, Object> map) {

	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		TelephoneVO telephone = (TelephoneVO) getValue();

		if (telephone == null) {
			telephonePrefix.setSubmittedValue(null);
			telephoneNumber.setSubmittedValue(null);
		} else {
			telephonePrefix.setSubmittedValue(telephone.getCountryTelephonePrefix());
			telephoneNumber.setSubmittedValue(telephone.getNumber());
		}
		super.encodeBegin(context);
	}

	/**
	 * Returns the submitted value in dd-MM-yyyy format.
	 */
	@Override
	public Object getSubmittedValue() {
		TelephoneVO telVO = new TelephoneVO();
		telVO.setCountryTelephonePrefix((CountryTelephonePrefixVO) telephonePrefix.getSubmittedValue());
		telVO.setNumber((String) telephoneNumber.getSubmittedValue());
		return telVO;
	}

	@Override
	protected Object getConvertedValue(FacesContext context, Object submittedValue) {
		return submittedValue;
	}

	public UIInput getTelephonePrefix() {
		return telephonePrefix;
	}

	public void setTelephonePrefix(UIInput telephonePrefix) {
		this.telephonePrefix = telephonePrefix;
	}

	public UIInput getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(UIInput telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
}
