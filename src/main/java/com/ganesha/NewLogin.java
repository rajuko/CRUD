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


@WebServlet("/NewLogin")
public class NewLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:7026/Student?autoReconnect=true&useSSL=false";
		String user="root";
		String pwd="tiger";
		String sid1=request.getParameter("sid");
		String email1=request.getParameter("email");
		String pwd1=request.getParameter("password");
		
		response.setContentType("text/html");
		try {
			
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,user,pwd);
			String q="insert into login values(?,?,?)";
			PreparedStatement st=con.prepareStatement(q);
			st.setString(1, sid1);
			st.setString(2, email1);
			st.setString(3, pwd1);
			
			int i=st.executeUpdate();
			
		    RequestDispatcher rd=request.getRequestDispatcher("Test");
			rd.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
			out.print(e);
			
		}
	}

}
