package com.kitchen.Tiffin.web.dto;

public class UserRegistrationDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private String password;
    
    public UserRegistrationDTO() {
        // Default constructor
    }

    public UserRegistrationDTO(String firstname, String lastname, String email, String role, String password) {
        super();
    	this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    // Getters and setters
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

