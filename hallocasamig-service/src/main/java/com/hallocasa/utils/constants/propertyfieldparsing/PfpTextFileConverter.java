package com.hallocasa.utils.constants.propertyfieldparsing;

import static com.hallocasa.filemanager.FileManager.replaceMassive;
import static com.hallocasa.systemproperties.SystemConstants.MINI_PROPERTY_IMAGES_PATH;
import static com.hallocasa.systemproperties.SystemConstants.PROPERTY_IMAGES_PATH;
import static com.hallocasa.systemproperties.SystemProperty.get;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.avsoft.commons.AvsFileManager;
import com.hallocasa.filemanager.FileManager;
import com.hallocasa.utils.constants.ImageParameters;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueSpec;

public class PfpTextFileConverter implements PropertyFieldValueConverter {

	private String propertiesPathRoot = get(PROPERTY_IMAGES_PATH);
	private String miniImgPropertiesPathRoot = get(MINI_PROPERTY_IMAGES_PATH);
	
	private static final Logger LOG = LogManager.getLogger(PfpTextFileConverter.class);
	
	@Override
	public PropertyFieldValueSpec toVo(String detail) {
		if(detail == null){
			return null;
		}
		PropertyFieldValueSpec spec = new PropertyFieldValueSpec();
		spec.setStrVal(detail);
		return spec;
	}

	@Override
	public String toEnt(PropertyFieldValueSpec spec, String... details) {
		if(spec == null){
			return null;
		}
		String tmpPrefix = details[1];
		String propId = details[0];
		String pref = tmpPrefix + "-";
		if(isFilename(spec.getStrVal())){
			String pathname = spec.getStrVal();
			replaceMassive(propertiesPathRoot, pathname, pref + pathname);
			replaceMassive(miniImgPropertiesPathRoot, pathname, pref + pathname);
			return spec.getStrVal();
		}
		String fullFilename = FileManager.createFileFromBase64(propertiesPathRoot, 
				spec.getStrVal(), pref + propId);
		LOG.debug("generating fullfilename: " + fullFilename);
		LOG.debug("property images path: " + propertiesPathRoot);
		LOG.debug("minified property images path: " + miniImgPropertiesPathRoot);
		LOG.debug("generating fullfilename: " + fullFilename);
		AvsFileManager.createMinifiedImage(miniImgPropertiesPathRoot, fullFilename, 
				ImageParameters.PROP_DEFAULT_MINIFIED_IMG_WIDTH, 
				ImageParameters.PROP_DEFAULT_MINIFIED_IMG_HEIGHT);
		LOG.debug("image minified");
		String[] parts = fullFilename.split("/");
		return parts[parts.length - 1].replaceAll(pref, "");
	}

	private boolean isFilename(String strVal){
		String[] vluList = strVal.split("-");
		boolean propIdentifierSize = vluList[0].length() == 8;
		if(!propIdentifierSize){
			return false;
		}
		String[] imgList = vluList[1].split("\\.");
		boolean filenameSize = imgList[0].length() == 20;
		return filenameSize;
	}
	
}
