package com.loggitorBE.loggitorBE.admin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Users {
	   @Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	   private long UserId;
	   private String Name;
	   //@Column(unique=true)
	   private String Email;
	   private String Phone;
	   private String Password;
	   private String login;



	public Users() {
	       super();
	   }

	


	public Users(String name, String email, String phone, String password, String login) {
		super();
		Name = name;
		Email = email;
		Phone = phone;
		Password = password;
		this.login = login;
	}




	public long getUserId() {
		return UserId;
	}



	public void setUserId(long userId) {
		UserId = userId;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}



	public String getPhone() {
		return Phone;
	}



	public void setPhone(String phone) {
		Phone = phone;
	}



	public String getPassword() {
		return Password;
	}



	public void setPassword(String password) {
		Password = password;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}


	


	}