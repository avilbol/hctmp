package com.hallocasa.vo.hcfilter.properties;

/**
 * This value object represents an hallocasa user with basic fields 
 * traduced 
 * @author Alexander Villamil
 */
public class FlatUser {

	private String firstname;
	private String lastname;
	private String userDescription;
	private String imageUrl;
	private String email;
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	
	public String getUserDescription() {
		return userDescription;
	}
	
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
