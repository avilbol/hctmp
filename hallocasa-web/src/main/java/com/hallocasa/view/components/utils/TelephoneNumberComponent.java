package com.hallocasa.view.components.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import com.hallocasa.commons.vo.CountryTelephonePrefixVO;
import com.hallocasa.commons.vo.TelephoneVO;
import com.hallocasa.services.location.local.TelephoneServices;
import com.hallocasa.view.components.base.BaseComponent;

/**
 * Backing bean for tlephone number component
 * 
 * @author Alexander Villamil
 */
@FacesComponent("telephoneNumberComponent")
@ViewScoped
public class TelephoneNumberComponent extends BaseComponent {

	private enum Attributes {
		value, readOnly
	}

	@EJB
	TelephoneServices telephoneServices;
	
	List<CountryTelephonePrefixVO> prefixList;
	
	List<String> testList;
	
	TelephoneVO telephoneObj;

	
	@Override
	@PostConstruct
	protected void initialize() {
		
	}

	@Override
	protected void saveComponent(FacesContext facesContext, HashMap<String, Object> map) {
		map.put("prefixList", prefixList);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void restoreComponent(FacesContext facesContext, HashMap<String, Object> map) {
		prefixList = (List<CountryTelephonePrefixVO>) map.get("prefixList");
	}

	public void onPreRender() throws IOException {
		testList = Arrays.asList(new String[]{"1","2","3"});
		if(prefixList == null){
			prefixList = telephoneServices.getCountryPrefixList();
		}
		telephoneObj = (TelephoneVO) getAttributes().get("value");
		if(!(boolean)getAttributes().get("readOnly") && telephoneObj == null){
			telephoneObj = new TelephoneVO();
			getAttributes().put("value", telephoneObj);
		}
	}
	
	public List<CountryTelephonePrefixVO> completeText(String query) {
        List<CountryTelephonePrefixVO> results = new ArrayList<>();
        for(CountryTelephonePrefixVO ctpVO : prefixList){
        	if(String.valueOf(ctpVO.getPrefix()).contains(query)){
        		results.add(ctpVO);
        	}
        }
        return results;
    }
	
	public String loadItemLabel(CountryTelephonePrefixVO ctpVO){
		if(ctpVO == null){
			return null;
		}
		return ctpVO.getPrefix() + " - " + ctpVO.getName();
	}

	public List<CountryTelephonePrefixVO> getPrefixList() {
		return prefixList;
	}

	public void setPrefixList(List<CountryTelephonePrefixVO> prefixList) {
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
}
