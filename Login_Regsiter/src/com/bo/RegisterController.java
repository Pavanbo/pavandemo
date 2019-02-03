package com.bo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@SuppressWarnings("serial")
@WebServlet("/register")
public class RegisterController extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
        String userName=request.getParameter("name");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        if(email.isEmpty()||password.isEmpty())
        	
      {
        	out.println("<font color=\"red\">Email or Password can't be blank</font>");
        	RequestDispatcher req=request.getRequestDispatcher("register.html");
    		req.include(request, response);
	    }
    
  else
   {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/company","root","tiger");
			PreparedStatement ps=con.prepareStatement("Insert into User(name,email,password) values(?,?,?);");
			ps.setString(1, userName);
			ps.setString(2, email);
			ps.setString(3, password);
			int x=ps.executeUpdate();
			if(x==0)
			{
				out.println("Probelem with registration");
			}
			else {
				out.println("Successfully registered");
			}
			
		} 
    	
    	catch (Exception e) {
			
			e.printStackTrace();
		}
    	
   
    }
    
}

}