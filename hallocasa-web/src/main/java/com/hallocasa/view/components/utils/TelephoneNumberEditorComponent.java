package com.hallocasa.view.components.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import com.hallocasa.commons.vo.CountryTelephonePrefix;
import com.hallocasa.commons.vo.TelephoneVO;
import com.hallocasa.services.location.local.TelephoneServices;
import com.hallocasa.view.utils.FormatUtils;

/**
 * Backing bean for telephone number component
 * 
 * @author Alexander Villamil
 */
@FacesComponent("telephoneNumberEditorComponent")
@ViewScoped
public class TelephoneNumberEditorComponent extends UIInput implements NamingContainer{

	private enum Attributes {
		value, readOnly
	}

	@EJB
	TelephoneServices telephoneServices;

	List<CountryTelephonePrefix> prefixList;

	List<String> testList;

	TelephoneVO telephoneObj;

	UIInput countryTelephonePrefix;

	UIInput number;

	@Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }
	
	public String getCid(){
		return "fguweyhfwehd";
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		TelephoneVO tvo = (TelephoneVO) getValue();
		if (tvo != null) {
			countryTelephonePrefix.setValue(tvo.getCountryTelephonePrefix());
			number.setValue(tvo.getNumber());
		}
		super.encodeBegin(context);
	}

	@Override
	public Object saveState(FacesContext facesContext) {
		Map<String, Object> stateMap = new HashMap<String, Object>();
		stateMap.put("prefixList", prefixList);
		return stateMap;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void restoreState(FacesContext facesContext, Object object) {
		Map<String, Object> stateMap = (Map<String, Object>) object;
		prefixList = (List<CountryTelephonePrefix>) stateMap.get("prefixList");
	}

	@Override
	public Object getSubmittedValue() {
		String ctpVO = (String) countryTelephonePrefix.getSubmittedValue();
		String nmb = (String) number.getSubmittedValue();
		if (ctpVO != null && !ctpVO.trim().isEmpty() && !FormatUtils.isEmptyValue(nmb)) {
			CountryTelephonePrefix ctpVOObj = new CountryTelephonePrefix();
			ctpVOObj.setId(Long.parseLong(ctpVO));
			TelephoneVO telephoneVO = new TelephoneVO();
			telephoneVO.setCountryTelephonePrefix(ctpVOObj);
			telephoneVO.setNumber(nmb);
			return telephoneVO;
		}
		else{
			TelephoneVO telephoneVO = new TelephoneVO();
			return telephoneVO;
		}
	}

	@Override
	public Object getConvertedValue(FacesContext context, Object object) {
		TelephoneVO tvo = (TelephoneVO) object;
		if(tvo.getNumber() == null){
			return null;
		}
		return object;
	}
	
	public List<CountryTelephonePrefix> completeText(String query) {
		List<CountryTelephonePrefix> results = new ArrayList<>();
		for (CountryTelephonePrefix ctpVO : getPrefixList()) {
			if (String.valueOf(ctpVO.getPrefix()).contains(query)) {
				results.add(ctpVO);
			}
		}
		return results;
	}

	public String loadItemLabel(CountryTelephonePrefix ctpVO) {
		if (ctpVO == null) {
			return null;
		}
		return ctpVO.getPrefix() + " - " + ctpVO.getName();
	}

	public List<CountryTelephonePrefix> getPrefixList() {
		if (prefixList == null) {
			prefixList = telephoneServices.getCountryPrefixList();
		}
		return prefixList;
	}

	public void setPrefixList(List<CountryTelephonePrefix> prefixList) {
		this.prefixList = prefixList;
	}

	public TelephoneVO getTelephoneObj() {
		return telephoneObj;
	}

	public void setTelephoneObj(TelephoneVO telephoneObj) {
		this.telephoneObj = telephoneObj;
	}

	public List<String> completeMethodAbc() {
		return testList;
	}

	public void setTestList(List<String> testList) {
		this.testList = testList;
	}

	public UIInput getCountryTelephonePrefix() {
		return countryTelephonePrefix;
	}

	public void setCountryTelephonePrefix(UIInput countryTelephonePrefix) {
		this.countryTelephonePrefix = countryTelephonePrefix;
	}

	public UIInput getNumber() {
		return number;
	}

	public void setNumber(UIInput number) {
		this.number = number;
	}
}
