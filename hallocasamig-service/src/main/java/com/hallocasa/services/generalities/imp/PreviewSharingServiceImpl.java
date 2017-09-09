package com.hallocasa.services.generalities.imp;

import java.io.File;
import java.io.IOException;

import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;

import com.hallocasa.services.generalities.PreviewSharingService;

/**
 * Implementation of service for sharing html previews
 * @author Alexander Villamil
 */
@Stateless
public class PreviewSharingServiceImpl implements PreviewSharingService {

	@Override
	public String homePreview() throws IOException {
		File htmlTemplateFile = new File("index-preview.html");
		String htmlString = FileUtils.readFileToString(htmlTemplateFile);
		return htmlString;
	}
}
