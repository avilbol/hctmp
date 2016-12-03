package com.hallocasa.utils.constants.propertyfieldparsing;

import static com.hallocasa.filemanager.FileManager.replaceMassive;
import static com.hallocasa.systemproperties.SystemConstants.*;
import static com.hallocasa.systemproperties.SystemProperty.get;

import com.hallocasa.filemanager.FileManager;
import com.hallocasa.vo.hcfilter.properties.PropertyFieldValueSpec;

public class PfpTextFileConverter implements PropertyFieldValueConverter {

	private String filePathRoot = get(PROPERTY_IMAGES_PATH);
	
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
			replaceMassive(filePathRoot, pathname, pref + pathname);
			return spec.getStrVal();
		}
		String fullFilename = FileManager.createFileFromBase64(filePathRoot, 
				spec.getStrVal(), pref + propId);
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
