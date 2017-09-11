package com.hallocasa.services.generalities.imp;

import java.io.IOException;
import java.io.InputStream;

import javax.ejb.Stateless;

import com.avsoft.commons.AvsFileManager;
import com.hallocasa.services.generalities.PreviewSharingService;
import com.hallocasa.services.properties.imp.PropertyServiceImp;

/**
 * Implementation of service for sharing html previews
 * @author Alexander Villamil
 */
@Stateless
public class PreviewSharingServiceImpl implements PreviewSharingService {

	@Override
	public String homePreview() throws IOException {
		InputStream in = PropertyServiceImp.class.getClassLoader().getResourceAsStream("index-preview.html");
		return AvsFileManager.loadInputStreamToString(in);
	}
}
