package com.jack.project.model;

import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@NamedQuery(name = "School.findAll", query = "select o from School o"),
	@NamedQuery(name = "School.findBySchoolName", query = "select o from School o where o.schoolName=:schoolName"),
})
@Entity
@Table(name = "school")
public class School {
	
	//every entity requires an id, and we can make it auto generated
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String schoolName;
	private String schoolAddress;
	
	public School() {
		
	}
	
	public School(String schoolName, String schoolAddress) {
		super();
		this.schoolName = schoolName;
		this.schoolAddress = schoolAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	
}
	
