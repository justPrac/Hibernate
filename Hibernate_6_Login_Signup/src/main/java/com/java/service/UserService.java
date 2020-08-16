package com.java.service;

import com.java.dao.UserDao;
import com.java.model.User;

public class UserService
{
	private UserDao userDao;
	
	public UserService()
	{
		this.userDao = new UserDao();
	}
	
	public User getUserByUsername(String username)
	{
		return userDao.getUserByUsername(username);		
	}

	public User getUserById(int id)
	{
		return userDao.getUserById(id);		
	}
	
	public boolean saveUser(User user)
	{
		return userDao.saveUser(user);
	}

	public boolean deleteUser(int id)
	{
		return userDao.deleteUser(id);
	}

	public boolean updateUser(User user, int id)
	{
		return userDao.updateUser(user, id);
	}

	public boolean addUser(User user)
	{
		return userDao.addUser(user);
	}
	
	public boolean validLogin(String username, String password)
	{
		User user = userDao.getUserByUsername(username);
		
		return(user != null && user.getPassword().equals(password));
	}

}
