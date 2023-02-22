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


@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:7026/Student?autoReconnect=true&useSSL=false";
		String user="root";
		String pwd="tiger";
		String sid1=request.getParameter("sid");
		String name1=request.getParameter("name");
		String mno=request.getParameter("mobile");
		String add1=request.getParameter("add");
		String email1=request.getParameter("email");
		String pwd1=request.getParameter("password");
		
		response.setContentType("text/html");
		try {
			
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,user,pwd);
			String q1="insert into newlogin values(?,?,?,?,?,?)";
			PreparedStatement st1=con.prepareStatement(q1);
			st1.setString(1, sid1);
			st1.setString(2, name1);
			st1.setString(3, mno);
			st1.setString(4, add1);
			st1.setString(5, email1);
			st1.setString(6, pwd1);
			
			int i=st1.executeUpdate();
			
            if(i>0) {
				
				RequestDispatcher rd=request.getRequestDispatcher("Home");
				rd.forward(request, response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			out.print(e);
		}
	}

}
