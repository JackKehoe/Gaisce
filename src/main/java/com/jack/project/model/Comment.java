package com.jack.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries( {
	@NamedQuery(name = "Comment.findAll", query = "select o from Comment o"),
	@NamedQuery(name = "Comment.findById", query = "select o from Comment o where o.id=:id"),
})

@Entity
@Table(name="comment")
public class Comment {
	

	private int id;
	private String content;
	private String timeStamp;
	
	public Comment(){
		
	}
	
	public Comment(String content, String timeStamp) {
		super();
		this.content = content;
		this.timeStamp = timeStamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
