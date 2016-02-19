package com.hallocasa.viewmodel.user.profile;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hallocasa.view.utils.JSFUtils;

/**
 * View model for public profile page
 *
 * @author Alexander Villamil
 * @since 1.7
 */
@ManagedBean
@ViewScoped
public class PublicProfilePage implements Serializable{

	
	
	/**
	 * Serialization constant
	 */
	private static final long serialVersionUID = 6405708913149271548L;
	
	private String userIdStr;
	
	public void initialize(){
		Object object = JSFUtils.getRequestElement("user");
		System.out.println(this.userIdStr);
	}

	public String getUserIdStr() {
		return userIdStr;
	}

	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}
}
