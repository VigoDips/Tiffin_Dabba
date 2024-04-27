package com.kitchen.Tiffin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;



@Entity
@Table(name = "users", uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
            
    
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private String role;
    @Column
    private String password;
    
   
    
    public User() {
		super();
	}
    
    // Constructors, getters, and setters
	   public User(String firstname, String lastname, String email, String role, String password) {
			super();
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.role = role;
			this.password = password;
		}
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

public String getRole() {
		return role;
	}

public void setRole(String role) {
		this.role = role;
	}

public String getPassword() {
		return password;
	}

public void setPassword(String password) {
		this.password = password;
	}
}

	      
