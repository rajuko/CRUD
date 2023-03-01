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


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:7026/Student?autoReconnect=true&useSSL=false";
		String user="root";
		String pwd="tiger";
		String sid1=request.getParameter("sid");
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,user,pwd);
			PreparedStatement pst=conn.prepareStatement("delete from newlogin where sid=?;");
			PreparedStatement pst1=conn.prepareStatement("delete from login where sid=?;");
			pst1.setString(1, sid1);
			
			pst1.executeUpdate();
			pst.setString(1, sid1);
			
			pst.executeUpdate();
			out.print("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\r\n"
					+ " \r\n"
					+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n"
					+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
					+ "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
			out.println("<br><br>");
			
			out.println("<div class='alert alert-success' role='alert'>"
					+ " <h3 align='center'> Record deleted successfully...!</h3>"
					+ "</div>");
		
			 
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			out.println(e.getMessage());
		}

		catch(Exception e1) {
		     e1.printStackTrace();
		     out.println(e1.getMessage());
		}
	}	

}
