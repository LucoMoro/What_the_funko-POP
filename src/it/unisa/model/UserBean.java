package it.unisa.model;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ID;
	private String password;
	private String email;

	public UserBean() {
		ID="";
		password="";
		email="";
	}


	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email= email;
	}
	
}
