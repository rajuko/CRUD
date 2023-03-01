package com.ganesha;

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

@WebServlet("/Student")

public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:7026/Student?autoReconnect=true&useSSL=false";
		String user="root";
		String pwd="tiger";
		String sid1=request.getParameter("sid");
		String name1=request.getParameter("name");
		String dob1=request.getParameter("dob");
		String mno=request.getParameter("mobile");
		String add1=request.getParameter("add");
		String email1=request.getParameter("email");
		String pwd1=request.getParameter("pwd");
		
		response.setContentType("text/html");
		try {
			
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,user,pwd);
			String q="insert into login values(?,?,?);";
			PreparedStatement st=con.prepareStatement(q);
			st.setString(1, sid1);
			st.setString(2, email1);
			st.setString(3, pwd1);
			
			int i=st.executeUpdate();
			
			if(i>0) {
				
				RequestDispatcher rd=request.getRequestDispatcher("Home");
				rd.forward(request, response);
			}
			else {
				
				out.println("ok");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			out.println(e.getMessage());
			
		}
	
	}

}
