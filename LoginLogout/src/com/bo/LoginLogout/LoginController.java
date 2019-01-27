package com.bo.LoginLogout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Login")
@SuppressWarnings("serial")
public class LoginController extends HttpServlet {

 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String un = request.getParameter("uname");
		String pwd = request.getParameter("pass");
		if (un.equals("pavan")) {
			out.print("Welcome, " + un);
			HttpSession session = request.getSession(true); 															// or create one
			session.setAttribute("user", un);
			session.setMaxInactiveInterval(10);
			response.sendRedirect("home.jsp");
		} 
		else {
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		} 
	}
}