package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.model.User;
import com.java.service.UserService;

public class SignUpServlet extends HttpServlet
{	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String name = (String) req.getParameter("name");
		String username = (String) req.getParameter("username");
		String password = (String) req.getParameter("password");		
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setUsername(username);
		if(userService.addUser(user))
		{
			req.setAttribute("msg", "User: " + name + ", Created Successfully!");
			req.getRequestDispatcher("/login").forward(req, resp);
			return;
		}
		else
		{
			req.setAttribute("msg", "User: " + username + ", already exists! Please try again!");
			req.getRequestDispatcher("/login").forward(req, resp);
			return;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter out = resp.getWriter();
		out.println("<html> <head> <title>Sign Up</title> </head> <body align='center'>");
		out.println("<h2><font-color='orange'>Sign Up</font></h2>");
		
		out.println("<form method='post' action='/Hibernate_6_Login_Signup/signup'>");
		out.println("Name: <input required type='text' name='name'> <br>");
		out.println("Username: <input required type='text' name='username'> <br>");
		out.println("Password: <input required type='password' name='password'> <br>");
		out.println("<input type='submit' value='Create User'> <a href='/Hibernate_6_Login_Signup/login'>Cancel</a> <br>");
		out.println("</form>");
		
		out.println("</body></html>");
		out.close();
	}
}
