package org.upadhyay.suraj.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Vehicle
{
	@Id @GeneratedValue
	private int id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "Owner")
	private User owner;
	
	public Vehicle() {}

	public Vehicle(String name)
	{
		this.name = name;
	}
	
	public Vehicle(String name, User owner)
	{
		this.name = name;
		//this.owner = owner;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	
}
