package com.tickets.api.model;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tickets")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	private String title;

    private String description;

    private String status;
    
    @ManyToOne(
    		cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE }
    		//cascade = CascadeType.ALL
    )
    @JoinColumn(name = "userid")
    private Dbuser user;
    
    
    public Ticket() { }

    public Ticket(String title, String description) {
        this.title = title;
        this.description = description;
    }
    
    public Long getId() {
		return id;
	}
    
    public void setId(Long id) {
    	this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Dbuser getUser() { return user; }
	
    public void setUser(Dbuser user) { this.user = user; }

}
