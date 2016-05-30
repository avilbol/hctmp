package com.hallocasa.view.components.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.commons.vo.CurrencyVOAmmount;
import com.hallocasa.view.utils.FormatUtils;

/**
 * Backing bean for currency component
 * 
 * @author Alexander Villamil
 */
@FacesComponent("currencyEditorComponent")
@ViewScoped
public class CurrencyEditorComponent extends UIInput implements NamingContainer {

	private enum Attributes {
		currencies, converter, language
	}

	List<CurrencyVO> currencyList;

	CurrencyVOAmmount currencyObj;

	UIInput currency;

	UIInput currencyAmmount;

	@Override
	public String getFamily() {
		return UINamingContainer.COMPONENT_FAMILY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		if (currencyList == null) {
			currencyList = (List<CurrencyVO>) getAttributes().get(Attributes.currencies.name());
		}
		CurrencyVOAmmount currencyVoAmmount = (CurrencyVOAmmount) getValue();
		if (currencyVoAmmount != null) {
			currency.setValue(currencyVoAmmount.getCurrency());
			currencyAmmount.setValue(currencyVoAmmount.getValue());
		}
		super.encodeBegin(context);
	}

	@Override
	public Object saveState(FacesContext facesContext) {
		Map<String, Object> stateMap = new HashMap<String, Object>();
		stateMap.put("currencyList", currencyList);
		return stateMap;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void restoreState(FacesContext facesContext, Object object) {
		Map<String, Object> stateMap = (Map<String, Object>) object;
		currencyList = (List<CurrencyVO>) stateMap.get("currencyList");
	}

	@Override
	public Object getSubmittedValue() {
		String crncy = (String) currency.getSubmittedValue();
		String crncyAmmount = (String) currencyAmmount.getSubmittedValue();
		CurrencyVOAmmount cvoAmmount = new CurrencyVOAmmount();
		if ((crncy != null && !crncy.trim().isEmpty() && FormatUtils.isNumeric(crncy))
				|| !FormatUtils.isEmptyValue(crncyAmmount)) {
			CurrencyVO crcyVOObj = new CurrencyVO();
			
			if(FormatUtils.isNumeric(crncy)){
				crcyVOObj.setId(Integer.parseInt(crncy));
				cvoAmmount.setCurrency(crcyVOObj);
			}
			if(FormatUtils.isNumeric(crncyAmmount)){
				cvoAmmount.setValue(new BigDecimal(crncyAmmount));
			}
		}
		return cvoAmmount;
	}

	@Override
	public Object getConvertedValue(FacesContext context, Object object) {
		return (CurrencyVOAmmount) object;
	}

	public String currencyValue(CurrencyVO currency) {
		return currency.getAbbreviation() + " - "
				+ currency.getName().getLangValue((Language) this.getAttributes().get(Attributes.language.name()));
	}

	public List<CurrencyVO> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<CurrencyVO> currencyList) {
		this.currencyList = currencyList;
	}

	public CurrencyVOAmmount getCurrencyObj() {
		return currencyObj;
	}

	public void setCurrencyObj(CurrencyVOAmmount currencyObj) {
		this.currencyObj = currencyObj;
	}

	public UIInput getCurrency() {
		return currency;
	}

	public void setCurrency(UIInput currency) {
		this.currency = currency;
	}

	public UIInput getCurrencyAmmount() {
		return currencyAmmount;
	}

	public void setCurrencyAmmount(UIInput currencyAmmount) {
		this.currencyAmmount = currencyAmmount;
	}
}
