package org.upadhyay.suraj.dto;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "User")
public class UserDetails 
{
	@Id @GeneratedValue
	private int id;
	private String username;
	@Embedded
	@AttributeOverride(column = @Column(name = "CityName"), name = "city")
	private Address homeAddr;
	
	public Address getHomeAddr()
	{
		return homeAddr;
	}

	public void setHomeAddr(Address homeAddr)
	{
		this.homeAddr = homeAddr;
	}

	public UserDetails() {}
	
	public UserDetails(String username)
	{
		this.username = username;
	}
	
	public UserDetails(int id, String username) 
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
}
