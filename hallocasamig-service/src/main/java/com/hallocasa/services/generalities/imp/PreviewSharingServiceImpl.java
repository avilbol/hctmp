package com.hallocasa.services.generalities.imp;

import static com.hallocasa.systemproperties.SystemConstants.APP_SERVER_URL;
import static com.hallocasa.systemproperties.SystemProperty.get;
import static com.hallocasa.utils.constants.parsing.HallocasaConvert.toValueObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.avsoft.commons.AvsFileManager;
import com.hallocasa.dao.i.IDAOUser;
import com.hallocasa.dao.i.properties.IDAOProperty;
import com.hallocasa.entities.EntityUser;
import com.hallocasa.entities.properties.EntityProperty;
import com.hallocasa.services.generalities.LocaleNamingService;
import com.hallocasa.services.generalities.LocalizationService;
import com.hallocasa.services.generalities.PreviewSharingService;
import com.hallocasa.services.properties.imp.PropertyServiceImp;
import com.hallocasa.systemproperties.SystemConstants;
import com.hallocasa.systemproperties.SystemProperty;
import com.hallocasa.utils.constants.parsing.flat.FlatProfileParser;
import com.hallocasa.utils.constants.parsing.flat.FlatPropertyParser;
import com.hallocasa.vo.hcfilter.properties.FlatProperty;
import com.hallocasa.vo.hcfilter.properties.FlatUser;
import com.hallocasa.vo.hcfilter.properties.Property;

/**
 * Implementation of service for sharing html previews
 * @author Alexander Villamil
 */
@Stateless
public class PreviewSharingServiceImpl implements PreviewSharingService {

	@EJB
	private IDAOProperty daoProperty;
	
	@EJB
	private IDAOUser daoUser;
	
	@EJB
	private LocaleNamingService localeNamingService;
	
	@EJB
	private LocalizationService localizationService;
	
	/**
	 * Log 
	 */
	private static final Logger LOG = LogManager.getLogger(PreviewSharingServiceImpl.class);
	
	/**
	 * Reference to image url default when the property has no image
	 */
	private static final String NO_PROPERTY_IMAGE_URL = "no-image-property.png";
	
	/**
	 * Reference to image url default when the user has no image
	 */
	private static final String NO_PROFILE_IMAGE_URL = "no-image-user.png";
	
	@Override
	public String homePreview(String locale, String browserLocale) throws IOException {
		InputStream in = PropertyServiceImp.class.getClassLoader().getResourceAsStream("index-preview.html");
		String htmlString = AvsFileManager.loadInputStreamToString(in);
		htmlString = htmlString.replace("#{app.server.url}", SystemProperty.get(SystemConstants.APP_SERVER_URL));
		String standardLocale = localeNamingService.standardize(locale, browserLocale);
		return translateHomeTemplate(htmlString, standardLocale);
	}
	
	@Override
	public String previewPropertyById(String id, String locale, String browserLocale) throws IOException {
		Optional<EntityProperty> entityProperty = daoProperty.findById(id);
		String standardLocale = localeNamingService.standardize(locale, browserLocale);
		if (!entityProperty.isPresent()) {
			InputStream in = PreviewSharingServiceImpl.class.getClassLoader().getResourceAsStream("property-not-found.html");
			String htmlString = AvsFileManager.loadInputStreamToString(in);
			return translatePropertyNotFoundTemplate(htmlString, standardLocale);
		}
		Property property = (Property) toValueObject(entityProperty.get());
		FlatPropertyParser parser = new FlatPropertyParser();
		FlatProperty flatProperty = parser.transform(property, standardLocale);
		InputStream in = PropertyServiceImp.class.getClassLoader().getResourceAsStream("property-preview.html");
		String htmlString = AvsFileManager.loadInputStreamToString(in);
		htmlString = htmlString.replace("#{flatProperty.basicDescription}", flatProperty.getBasicDescription());
		htmlString = htmlString.replace("#{flatProperty.locationDescription}", flatProperty.getLocationDescription());
		htmlString = htmlString.replace("#{flatProperty.title}", flatProperty.getTitle());
		htmlString = htmlString.replace("#{flatProperty.urlImage}", propertyImageUrl(flatProperty));
		return htmlString;
	}
	
	@Override
	public String previewProfileById(Long id, String locale, String browserLocale) throws IOException {
		Optional<EntityUser> entityProfile = daoUser.find(id);
		String standardLocale = localeNamingService.standardize(locale, browserLocale);
		if (!entityProfile.isPresent()) {
			InputStream in = PreviewSharingServiceImpl.class.getClassLoader().getResourceAsStream("profile-not-found.html");
			String htmlString = AvsFileManager.loadInputStreamToString(in);
			return translatePropertyNotFoundTemplate(htmlString, standardLocale);
		}
		FlatProfileParser parser = new FlatProfileParser();
		FlatUser flatUser = parser.transform(entityProfile.get(), standardLocale);
		InputStream in = PropertyServiceImp.class.getClassLoader().getResourceAsStream("profile-preview.html");
		String htmlString = AvsFileManager.loadInputStreamToString(in);
		htmlString = htmlString.replace("#{flatUser.userDescription}", flatUser.getUserDescription());
		htmlString = htmlString.replace("#{flatUser.email}", flatUser.getEmail());
		htmlString = htmlString.replace("#{flatUser.firstname}", flatUser.getFirstname());
		htmlString = htmlString.replace("#{flatUser.lastname}", flatUser.getLastname());
		htmlString = htmlString.replace("#{flatUser.imageUrl}", profileImageUrl(flatUser));
		return htmlString;
	}
	
	/**
	 * Replace the depending locale texts in html template for its respective translation according to the locale
	 * Used for property not found template
	 * @param htmlString
	 * 		The html template in string format
	 * @param standardLocale
	 * 		The locale to use for translate
	 * @return
	 * 		The html template translated
	 */
	private String translatePropertyNotFoundTemplate(String htmlString, String standardLocale){
		List<String> pnemonicList = Arrays.asList("hallocasa.property.notfound", "hallocasa.property.notfound.explanation");
		return applyReplaceTranslation(htmlString, pnemonicList, standardLocale);
	}
	
	/**
	 * Replace the depending locale texts in html template for its respective translation according to the locale
	 * Used for home template
	 * @param htmlString
	 * 		The html template in string format
	 * @param standardLocale
	 * 		The locale to use for translate
	 * @return
	 * 		The html template translated
	 */
	private String translateHomeTemplate(String htmlString, String standardLocale){
		List<String> pnemonicList = Arrays.asList("hallocasa.previews.welcometohallocasa", "PositionStatement.title");
		return applyReplaceTranslation(htmlString, pnemonicList, standardLocale);
	}
	
	/**
	 * Apply a replace series in a text
	 * @param text
	 * 		The string to apply replaces
	 * @param pnemonicList
	 * 		The pnemonic series to translate
	 * @param standardLocale
	 * 		The reference locale to translate
	 * @return
	 * 		The translated text
	 */
	private String applyReplaceTranslation(String text, List<String> pnemonicList, String standardLocale){
		for(String pnemonic : pnemonicList){
			Map<String, String> localeEntry = localizationService.findByPnemonic(pnemonic);
			String translatedText = localeEntry.get(standardLocale);
			LOG.info(translatedText);
			String expressionToTranslate = "#{"+ pnemonic +" | translate}";
			text = text.replace(expressionToTranslate, translatedText == null ? pnemonic : translatedText);
		}
		return text;
	}
	
	/**
	 * Build the appropriate property image url, including a default image if none has specified
	 * @param flatProperty
	 * 		The flat property to extract the image url
	 * @return
	 * 		The adequate image url
	 */
	private String propertyImageUrl(FlatProperty flatProperty){
		String urlImage = flatProperty.getUrlImage();
		boolean emptyUrlImage = urlImage == null || urlImage.isEmpty();
		String propertyImageSiteUrl = get(APP_SERVER_URL) + "/resources/images/properties/";
		return emptyUrlImage ? NO_PROPERTY_IMAGE_URL : propertyImageSiteUrl + urlImage;
	}
	
	/**
	 * Build the appropriate user image url, including a default image if none has specified
	 * @param flatUser
	 * 		The flat user to extract the image url
	 * @return
	 * 		The adequate image url
	 */
	private String profileImageUrl(FlatUser flatUser){
		String urlImage = flatUser.getImageUrl();
		String profileImagesSiteUrl = get(APP_SERVER_URL) + "/resources/images/users/";
		boolean emptyUrlImage = urlImage == null || urlImage.isEmpty();
		return emptyUrlImage ? NO_PROFILE_IMAGE_URL : profileImagesSiteUrl + urlImage;
	}
}
