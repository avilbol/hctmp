package com.hallocasa.services.generalities.imp;

import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.hallocasa.dao.i.IDAOLanguage;
import com.hallocasa.entities.EntityLanguage;
import com.hallocasa.services.generalities.LanguageService;
import com.hallocasa.vo.Language;

/**
 * Service for languages allowed in application
 * @author avillamil
 */
@Stateless
public class LanguageServiceImp implements LanguageService {

	@EJB
	IDAOLanguage daoLanguage;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Language> find() {
		List<Language> languageList = new ArrayList<>();
		List<EntityLanguage> entityLangList = daoLanguage.find();
		for(EntityLanguage entLang : entityLangList){
			languageList.add((Language) toValueObject(entLang));
		}
		return languageList;
	}

}
