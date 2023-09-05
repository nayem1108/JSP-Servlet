package com.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String city = req.getParameter("city");
		
		
		if(name.equals("") || email.equals("") || password.equals("") || gender.equals("")|| city.equals("")) {
			out.println("<p style='color:red'>Fields are empty. Fill it and try again....</p>");
			RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			rd.include(req, resp);
		}
		else {	
			String queryString = "INSERT INTO user(name, email, password, gender, city) values(?, ?, ?, ?, ?)";
			try {
				
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "root");
				
				PreparedStatement pStatement = connection.prepareStatement(queryString);
				
				pStatement.setString(1, name);
				pStatement.setString(2, email);
				pStatement.setString(3, password);
				pStatement.setString(4, gender);
				pStatement.setString(5, city);
				
				int count = pStatement.executeUpdate();
				
				if(count>0) {
					
					out.println("<p style='color:green'>Registration Successful..</p>");
					RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
					rd.include(req, resp);
				}
				else {
					out.println("<p style='color:red'>Something went wrong... Check data and try again</p>");
					RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
					rd.include(req, resp);
				}
				pStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				out.println("<p style='color: red'>Exception Occured : "+e.getMessage()+"</p>");
				RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				rd.include(req, resp);
			}
		}
		
	}
}
