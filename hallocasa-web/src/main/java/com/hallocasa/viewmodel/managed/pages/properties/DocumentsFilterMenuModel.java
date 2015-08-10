package com.hallocasa.viewmodel.managed.pages.properties;

import java.io.Serializable;

import com.hallocasa.business.services.TypeServices;

/**
 * This class represents the presentation model of the filter menu to controls
 * the left menu filter in the properties browsing
 *
 * @author David Mantilla
 */
public class DocumentsFilterMenuModel extends PropertiesFilterMenuModelBase
		implements Serializable {

	private static final long serialVersionUID = 1418452006711333928L;

	public DocumentsFilterMenuModel(TypeServices typeServices) {
		super(typeServices, 1);
		initialize();
	}

	/**
	 * Initialize the filter model
	 */
	private void initialize() {
		// For socioeconomic stratum

	}
}
