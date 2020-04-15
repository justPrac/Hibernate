package org.upadhyay.suraj.dto;

import javax.persistence.Entity;

@Entity
public class TwoWheeler extends Vehicle
{
	private String handle;

	public TwoWheeler()
	{
		super();
	}

	public TwoWheeler(String name, String handle)
	{
		super(name);
		this.handle = handle;
	}

	public String getHandle()
	{
		return handle;
	}

	public void setHandle(String handle)
	{
		this.handle = handle;
	}
	

}
