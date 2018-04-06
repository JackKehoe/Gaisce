package com.jack.project.model;

import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@NamedQueries( {
	@NamedQuery(name = "Mentor.findAll", query = "select o from Mentor o"),
	@NamedQuery(name = "Mentor.findByEmail", query = "select o from Mentor o where o.email=:email"),
})
@Entity
@Table(name="mentor")
public class Mentor {
	

	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String passwordConfirm;
	private String school;
	
	@ManyToMany
    private Set<Role> roles;
	
	public Mentor(){
		
	}
	
	public Mentor(String username, String firstname, String lastname, String email, String password, String passwordConfirm, String school) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.school = school;
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


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	@Transient
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	
	 @ManyToMany
	    @JoinTable(name = "mentor_role", joinColumns = @JoinColumn(name = "mentor_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	    public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }

}
