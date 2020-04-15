package org.upadhyay.suraj.dto;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;


@Entity
public class User 
{
	@Id @GeneratedValue
	private int id;
	private String username;
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Vehicle_List",
				joinColumns = {@JoinColumn(name = "UserID")},
				inverseJoinColumns = {@JoinColumn(name = "VehicleID")})
	private List<Vehicle> v = new LinkedList<>();
	
	public User() {}
	
	public User(String username)
	{
		this.username = username;
	}
	
	public User(int id, String username) 
	{
		this.id = id;
		this.username = username;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}

	public List<Vehicle> getV()
	{
		return v;
	}

	public void setV(List<Vehicle> v)
	{
		this.v = v;
	}
	
}
