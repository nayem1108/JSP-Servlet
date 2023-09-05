package com.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String name
		String email = req.getParameter("email");
		String  password = req.getParameter("password");
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "root");
			
			String sqlString = "select * from user where email=? and password=?";
			
			PreparedStatement ps = connection.prepareStatement(sqlString);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs =  ps.executeQuery();
			
			System.out.println("rs "+ rs);
			if(rs.next()) {
				HttpSession session = req.getSession();
				session.setAttribute("name", rs.getString(2));
				RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
				rd.include(req, resp);
				
			}
			else {
				out.println("<p style='color:red'>Username or Password is not correct.");
				RequestDispatcher  rd = req.getRequestDispatcher("login.jsp"); 
				rd.include(req, resp);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			out.println(e);
			RequestDispatcher  rd = req.getRequestDispatcher("login.jsp"); 
			rd.include(req, resp);
			}
	}
}
