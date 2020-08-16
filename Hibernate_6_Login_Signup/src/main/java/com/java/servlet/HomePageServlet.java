package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.model.User;

public class HomePageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		User user = (User)req.getSession().getAttribute("user");
		if(user != null && user.getUsername() != null)
		{
			PrintWriter out = resp.getWriter();
			
			out.println("<html> <head> <title>Home</title> </head> <body align='center'>");
			out.println("<h1><font-color='orange'>Welcome " + user.getName() + "</font></h1>");
			out.println("<form method='get' action='/Hibernate_6_Login_Signup/login'>");
			out.println("<input type='submit' name='logout' value='Logout'>");
			out.println("</form>");
			out.println("</body></html>");

			out.close();
			return;
		}
		else
		{
			resp.sendRedirect("/Hibernate_6_Login_Signup/login");
			return;
		}
		
	}
}
