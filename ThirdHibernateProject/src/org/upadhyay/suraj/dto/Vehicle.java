package org.upadhyay.suraj.dto;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="VehicleType")
public class Vehicle
{
	@Id @GeneratedValue
	private int id;
	private String name;
	
	
	public Vehicle() {}

	public Vehicle(String name)
	{
		this.name = name;
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
