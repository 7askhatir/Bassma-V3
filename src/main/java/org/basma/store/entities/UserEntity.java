package org.basma.store.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="users")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1497590107634648409L;
	
	@Id
	@GeneratedValue
	private long id;
	//
	@Column(nullable=false)
	private String userId;
	//
	@Column(nullable=false, length=50)
	private String nomUser;
	//
	@Column(nullable=false, length=50)
	private String prenomUser;
	//
	@Column(nullable=false, length=120, unique=true)
	private String email;
	//
	@Column(nullable=false) 
	private String encyptePassword;
	//
	@Column(nullable=true) 
	private String emailVerificationToken;
	//
	@Column(nullable=false) 
	private Boolean emailVerificationStatuts;
	//
	@Column(nullable=false, length=14)
	private String roleUser;
	
	
	
	public UserEntity(String nomUser, String prenomUser, String email, String roleUser) {
		super();
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.email = email;
		this.roleUser=roleUser;
	}

	
	public UserEntity() {
		super();
		 
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNomUser() {
		return nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
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
	public String getPrenomUser() {
		return prenomUser;
	}

	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}


	public String getRoleUser() {
		return roleUser;
	}


	public void setRoleUser(String roleUser) {
		this.roleUser = roleUser;
	}


}
