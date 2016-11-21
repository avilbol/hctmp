/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import com.hallocasa.commons.Language;
import com.hallocasa.commons.i18n.MultiLanguageText;
import com.hallocasa.commons.vo.CountryVO;
import com.hallocasa.commons.vo.CurrencyVO;
import com.hallocasa.commons.vo.TelephoneVO;
import com.hallocasa.dataentities.app.test.TestEntity;
import com.hallocasa.model.application.CurrencyGlobalApplication;
import com.hallocasa.model.application.HallocasaApplicationImpl;
import com.hallocasa.services.messaging.exceptions.MailServicesErrorException;
import com.hallocasa.services.messaging.local.MailServices;
import com.hallocasa.view.utils.FormatUtils;

/**
 *
 * @author david
 */
@ManagedBean(name = "testPage")
@ViewScoped
public class TestPage {

    @EJB
    private MailServices mailServices;
    
    private List<TestEntity> testAttrList;
    
    private TelephoneVO telephoneAttr;
    
    private String myAttr;
    
    private Double valueFrom;
    
    private String valueTo;
    
    private List<String> availableElements;
    
    private List<Language> languages;
    
    private MultiLanguageText title = new MultiLanguageText();
    
    private List<String> selectedElements;
    
    private CurrencyVO currency;
    
    private List<CurrencyVO> currencies;
    
    private CountryVO country;
    
    private List<CountryVO> countries;
    
    @Inject
	private HallocasaApplicationImpl  halloCasaApplication;
    
    @Inject
   	private CurrencyGlobalApplication currencyGlobal;
    
    @PostConstruct
    public void init(){
    	currency = new CurrencyVO();
    	country = new CountryVO();
    	country.setId(1l);
    	countries=halloCasaApplication.getCountries();
    	currencies= currencyGlobal.getCurrencies();
    	currency.setId(1);
    	testAttrList = new ArrayList<TestEntity>();
    	testAttrList.add(new TestEntity(1l, "hi"));
    	testAttrList.add(new TestEntity(2l, "hi2"));
        availableElements = new ArrayList<String>();
        availableElements.add("A");
        availableElements.add("B");
        availableElements.add("C");
        myAttr="hi";
        selectedElements = new ArrayList<String>();
        //selectedElements.add("A");
        selectedElements.add("B");
        selectedElements.add("C");
    }

    public void onAction(){
    	
    }
    
    /**
	 * Process change on checkboxes of languages selected
	 */
	public void processLanguagesSelectedChange() {
		if (this.getTitle() == null) {
			setTitle(new MultiLanguageText());
		}
		for (Language lang : this.getLanguages()) {
			if (FormatUtils.isEmptyValue(title.getLangValue(lang))) 
				title.setLangValue(lang, "");
		}
	}

    public void sendTestEmail(ActionEvent event) {
        try {
            List<String> emails = new ArrayList<String>();
            emails.add("dmantil@hotmail.com");
            mailServices.sendMail(MailServices.BuildInMailType.RESET_PASSWORD,
                    Locale.US, emails, new HashMap<String, String>());
            Logger.getLogger(TestPage.class.getName()).log(Level.INFO, "Mail sent succesfully");
        } catch (MailServicesErrorException ex) {
            Logger.getLogger(TestPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sayHi(){
    	int a = 2;
    	System.out.println(a);
    }
    
    public void change(){
    	int a = 2;
    	System.out.println(a);
    }
    
    public List<String> getAvailableElements() {
        return availableElements;
    }

    public void setAvailableElements(List<String> availableElements) {
        this.availableElements = availableElements;
    }

    public List<String> getSelectedElements() {
        return selectedElements;
    }

    public void setSelectedElements(List<String> selectedElements) {
        this.selectedElements = selectedElements;
    }

	public List<TestEntity> getTestAttrList() {
		return testAttrList;
	}

	public void setTestAttrList(List<TestEntity> testAttrList) {
		this.testAttrList = testAttrList;
	}

	public String getMyAttr() {
		return myAttr;
	}

	public void setMyAttr(String myAttr) {
		this.myAttr = myAttr;
	}

	public TelephoneVO getTelephoneAttr() {
		return telephoneAttr;
	}

	public void setTelephoneAttr(TelephoneVO telephoneAttr) {
		this.telephoneAttr = telephoneAttr;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public MultiLanguageText getTitle() {
		return title;
	}

	public void setTitle(MultiLanguageText title) {
		this.title = title;
	}

	public CurrencyVO getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyVO currency) {
		this.currency = currency;
	}

	public List<CurrencyVO> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<CurrencyVO> currencies) {
		this.currencies = currencies;
	}

	public CountryVO getCountry() {
		return country;
	}

	public void setCountry(CountryVO country) {
		this.country = country;
	}

	public List<CountryVO> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryVO> countries) {
		this.countries = countries;
	}

	public Double getValueFrom() {
		return valueFrom;
	}

	public void setValueFrom(Double valueFrom) {
		this.valueFrom = valueFrom;
	}

	public String getValueTo() {
		return valueTo;
	}

	public void setValueTo(String valueTo) {
		this.valueTo = valueTo;
	}
}
