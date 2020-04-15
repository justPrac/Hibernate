package org.upadhyay.suraj.dto;

import javax.persistence.Entity;

@Entity
public class FourWheeler extends Vehicle
{

	private String steering;
	
	public FourWheeler()
	{
		super();
		this.steering = null;
	}
	
	public FourWheeler(String name, String steering)
	{
		super(name);
		this.steering = steering;
	}
	
	public String getSteering()
	{
		return steering;
	}
	
	public void setSteering(String steering)
	{
		this.steering = steering;
	}
}
