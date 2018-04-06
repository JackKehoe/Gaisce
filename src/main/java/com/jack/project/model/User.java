package com.jack.project.model;

import javax.persistence.*;
import com.jack.project.model.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

@NamedQueries( {
	@NamedQuery(name = "User.findAll", query = "select o from User o"),
	@NamedQuery(name = "User.findByEmail", query = "select o from User o where o.email=:email"),
})

@Entity
@Table(name = "user")
public class User {
	
	

	
    private int id;
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String firstname;
	private String lastname;
	private String school;
	@ManyToMany
    private Set<Role> roles;
    
	private List<Skill> skills;
	private List<Goal> goals;
	private List<Report> reports;

	
	public User(){
		
	}
	
	public User(String username,String email, String password, String passwordConfirm, String firstname, String lastname, String school) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.firstname = firstname;
		this.lastname = lastname;
		this.school = school;
	}

	
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public List<Goal> getGoal() {
		return goals;
	}

	public void setGoal(List<Goal> goals) {
		this.goals = goals;
	}
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    @Transient
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public void addSkill(Skill skillForm) {
    	skills.add(skillForm);
    }

	public void addReport(Report reportForm) {
		reports.add(reportForm);
		
	}

}

