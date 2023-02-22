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


@WebServlet("/Updateds")
public class Updateds extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:7026/Student?autoReconnect=true&useSSL=false";
		String user="root";
		String pwd="tiger";
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,user,pwd);
			String sid=request.getParameter("sid");
			String name=request.getParameter("name");
			String mobile=request.getParameter("mobile");
			String add=request.getParameter("add");
			PreparedStatement pst=conn.prepareStatement("update newlogin set name=?,mobile=?,address=? where sid=?;");
		    pst.setString(1, name);
		    pst.setString(2, mobile);
		    pst.setString(3, add);
		    pst.setString(4, sid);
		    
		    int i=pst.executeUpdate();
		    
		    RequestDispatcher rd=request.getRequestDispatcher("Home");
			rd.forward(request, response);
		}
		catch(Exception e) {
			out.println("okk");
		}
	}

}
