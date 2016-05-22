/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.commons.vo;
import java.io.Serializable;

/**
 * 
 * @author Alexander Villamil
 */
public class ImageContainer implements Serializable {

	/**
	 * Constante de serialización
	 */
	private static final long serialVersionUID = 1761450168544209129L;
	
	public ImageContainer(String url) {
        this.url = url;
    }

    public enum ImageContainerValue{
        USER_IMAGE("/userimage"),
        PROPERTY_IMAGE("/propertyimage"),
        DEFAULT("");
        
        private String prefix = "";
        
        private ImageContainerValue(String prefix){
            this.prefix = prefix;
        }

        /**
         * Determina si la ruta relativa cargada hace parte
         * de alguno de los valores constantes de ImageContainer
         * @param prefix
         * 		Ruta relativa cargada
         * @return
         * 		El valor constante correspondiente, DEFAULT si
         * 		no encuentra coincidencia
         */
        public static ImageContainerValue load(String prefix){
            for(ImageContainerValue value : ImageContainerValue.values()){
                if(prefix.equals(value.prefix)){
                    return value;
                }
            }
            return ImageContainerValue.DEFAULT;
        }
    }
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
