package com.hallocasa.services.generalities;

import java.io.IOException;

/**
 * Service for load previews of site
 * @author Alexander Villamil
 */
public interface PreviewSharingService {

	/**
	 * Generates a preview for homesite
	 * @throws IOException 
	 */
	public String homePreview() throws IOException;
	
}
