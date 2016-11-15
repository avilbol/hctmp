package com.hallocasa.utils.constants.propertyfieldparsing;

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
		spec.setStrVal(FileManager.encodeToBase64(filePathRoot, detail));
		return spec;
	}

	@Override
	public String toEnt(PropertyFieldValueSpec spec, String... details) {
		if(spec == null){
			return null;
		}
		String tmpPrefix = details[1];
		String propId = details[0];
		String fullFilename = FileManager.createFileFromBase64(filePathRoot, 
				spec.getStrVal(), tmpPrefix + "-" + propId);
		String[] parts = fullFilename.split("/");
		return parts[parts.length - 1].replaceAll(tmpPrefix, "");
	}

}
