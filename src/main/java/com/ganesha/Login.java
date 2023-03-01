package com.ganesha;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email1;
		String pass1;
		
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:7026/Student?autoReconnect=true&useSSL=false";
		String user="root";
		String pwd="tiger";
		PrintWriter out=response.getWriter();
		
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,user,pwd);
			
			email1=request.getParameter("email");
			pass1=request.getParameter("pass");
			
			
			PreparedStatement pst=conn.prepareStatement("select * from login where email=? and password=?;");
			
			pst.setString(1,email1);
			pst.setString(2, pass1);
			
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				RequestDispatcher rd=request.getRequestDispatcher("Home");
				rd.forward(request, response);
				
			}
			else {
				out.println("<script>");
				out.println("alert('please enter the correct email and password...')");
				out.println("</script>");
			}
	     }
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			out.println(e.getMessage());
		}
		
		catch(Exception e1)
		{
			e1.printStackTrace();
			out.println(e1.getMessage());
		}
	}

}
