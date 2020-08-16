package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.service.UserService;

public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		loginPage(resp.getWriter(), null);
	}
	
	public void loginPage(PrintWriter out, String msg)
	{
		out.println("<html> <head> <title>Login</title> </head> <body align='center'>");
		out.println("<h2><font-color='orange'>Login Page</font></h2>");
		
		if(msg != null && msg.length() > 0)
		{
			out.println("<font-color='red'>" + msg + "</font><br>");
		}
		out.println("<form method='post' action='/Hibernate_6_Login_Signup/login'>");
		out.println("Username: <input type='text' name='username'> <br>");
		out.println("Password: <input type='password' name='password'> <br>");
		out.println("<input type='submit' name='login'> <a href='/Hibernate_6_Login_Signup/signup'>Sign Up</a> <br>");
		out.println("</form>");
		out.println("</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String msg = (String) req.getAttribute("msg");
		
		if(msg != null && msg.length() > 0)
		{
			loginPage(resp.getWriter(), msg);
			return;
		}
		else
		{
			String username = (String)req.getParameter("username");
			String password = (String)req.getParameter("password");
			
			if(username != null && username.length() > 0 && 
					password != null && password.length() > 0)
			{
				if(userService.validLogin(username, password))
				{
					req.getSession().setAttribute("user", userService.getUserByUsername(username));
					resp.sendRedirect("/Hibernate_6_Login_Signup/homePage");
				}
				else
				{
					req.setAttribute("msg", "Invalid username/password entered. Please, try again!!");
					req.getRequestDispatcher("/login").forward(req, resp);
					return;
				}
			}
			else
			{
				req.setAttribute("msg", "Please enter a valid username/password!!");
				req.getRequestDispatcher("/login").forward(req, resp);
				return;
			}
		}
	}
}
