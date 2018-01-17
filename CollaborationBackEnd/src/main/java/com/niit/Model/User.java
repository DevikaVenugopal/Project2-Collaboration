package com.niit.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "C_USER")
@Component
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "USERID")
	private int userid;
	
	@Column(name = "Firstname", nullable = false)
	    private String firstname;
	 
	@Column(name = "Lastname", nullable = false)
	    private String lastname;
	
	@Column(name = "Email", nullable = false)
    private String email;
	
    @Column(name = "Password", nullable = false)
    private String password;
    
    @Column(name = "Role", nullable = false)
    private String role;
     
    @Column(name = "Isonline", nullable = false)
    private String isonline;
    
    @Column(name = "Phone", nullable = false)
    private String phone;
    
    @Column(name = "Gender", nullable = false)
    private String gender;
    
    @Column(name = "Status", nullable = false)
    private String status;

	@Column(name = "UserName", nullable = false)
    private String username;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
    
}
