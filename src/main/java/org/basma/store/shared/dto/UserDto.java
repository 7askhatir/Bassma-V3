package org.basma.store.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4886306822331107228L;
	private long id;
	private String userId;
	private String nomUser;
	private String prenomUser;
	private String email;
	private String password;
	private String encyptePassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatuts=false;
	private String roleUser;
	
 
 
	public UserDto(String nomUser, String prenomUser, String email, String password, String roleUser) {
		super();
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.email = email;
		this.password = password;
		this.roleUser=roleUser;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getPrenomUser() {
		return prenomUser;
	}
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
 
	public String getEncyptePassword() {
		return encyptePassword;
	}
	public void setEncyptePassword(String encyptePassword) {
		this.encyptePassword = encyptePassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean getEmailVerificationStatuts() {
		return emailVerificationStatuts;
	}
	public void setEmailVerificationStatuts(Boolean emailVerificationStatuts) {
		this.emailVerificationStatuts = emailVerificationStatuts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleUser() {
		return roleUser;
	}
	public void setRoleUser(String roleUser) {
		this.roleUser = roleUser;
	}
	
}