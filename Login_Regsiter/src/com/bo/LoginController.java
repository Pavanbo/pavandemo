package com.bo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginController extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	
	{
	 response.setContentType("text/html");
	 PrintWriter out=response.getWriter();
	 String em=request.getParameter("email");
	 String pass=request.getParameter("password");
	 try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","tiger");
		PreparedStatement ps=con.prepareStatement("select email,password from User where email=? and password=?");
		ps.setString(1, em);
		ps.setString(2, pass);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
		HttpSession session=request.getSession();
		session.setAttribute("email", em);
		session.setAttribute("password", pass);
			response.sendRedirect("welcome.jsp");
			return;
		}
	//	out.println("Email or Password is Wrong");
		out.println("<font color=\"red\">Email or Password is Wrong,please try again!</font>");
		RequestDispatcher req=request.getRequestDispatcher("login.html");
		req.include(request, response);
		
		//return;
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
		
	 	 }
	    
	 }
	 
	 
	 