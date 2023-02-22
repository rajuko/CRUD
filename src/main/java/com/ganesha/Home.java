package com.ganesha;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		Statement st=null;
		ResultSet rs1=null;
		
		PrintWriter out=response.getWriter();
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:7026/Student?autoReconnect=true&useSSL=false";
		String user="root";
		String pwd="tiger";
		
		String query1="select sid,name,mobile,address from newlogin;";
		
		response.setContentType("text/html");
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,pwd);
			st=conn.createStatement();
		    rs1=st.executeQuery(query1);
	   	   
			out.println("<html>");
			out.println("<body>");
			out.print("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\r\n"
					+ " \r\n"
					+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n"
					+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
					+ "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
			out.println("<br><br>");
			out.println("<link rel='stylesheet' href='style.css'>");
			out.println("<h2 class='text-primary'>CURD OPERATIONS</h2>");
			out.println("<div class'jumbotron text-center'>");
			out.println("<a href='NewForm.html' class='btn btn-success'>Add Student</a>");
			out.println("<a href='Login.html' class='btn btn-success'>Logot</a>");
			out.println("<br><br><br><br>");
			out.println("<table border='1' width='500px' class='table table-hover table-striped'>");
			out.println("<tr>");
			out.println("<td>StudentId</td>");
			out.println("<td>Name</td>");
			out.println("<td>Mobile</td>");
			out.println("<td>Address</td>");
			out.println("<td>Edit</td>");
			out.println("<td>Delete</td>");
			out.println("</tr>");
			while(rs1.next()) {
				out.println("<tr>");
				out.println("<td>" + rs1.getString(1) + "</td>");
				out.println("<td>" + rs1.getString(2) + "</td>");
				out.println("<td>" + rs1.getString(3) + "</td>");
				out.println("<td>" + rs1.getString(4) + "</td>");
				out.println("<td>" + "<button class='btn btn-success data-toggle='model' data-target='#successModel'>" + "<a href='Update?sid=" + rs1.getString(1) + "'>Update</a>" +"</button> " +"</td>");
				out.println("<td>"+"<button class='btn btn-success data-toggle='model' data-target='#successModel'>" + "<a href='Delete?sid=" + rs1.getString(1) + "'>Delete</a>" + "</td>");
				out.println("</tr>");
			}
			    out.println("</table>");
			    out.println("</div>");
			    out.println("</div>");
			    out.println("</body>");
			    out.println("</html>");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			out.println(e.getMessage());
			
		}
	}
}

