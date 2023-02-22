package com.ganesha;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Update")
public class Update extends HttpServlet {
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
			PreparedStatement pst=conn.prepareStatement("select sid,name,mobile,address from Newlogin where sid=?;");
			pst.setString(1, sid1);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				out.println("<html>");
				out.println("<body>");
				out.println("<link rel='stylesheet' href='New.css'>");
				out.println("<div class='main'>");
				out.println("<div class='register'>");
				out.println("<h2>Register Here</h2>");
				out.println("<form action='Updateds' id='register' method='post'>");
				out.println("<label>Student Id </label>");
				out.println("<br>");
				out.println("<input type='text' name='sid' id='name' value='" + rs.getString(1) + "'>");
				out.println("<br><br>");
				out.println("<label>Name </label>");
				out.println("<br>");
				out.println("<input type='text' name='name' id='name' value='" + rs.getString(2) + "'>");
				out.println("<br><br>");
				out.println("<label>Mobile no </label>");
				out.println("<br>");
				out.println("<input type='number' name='mobile' id='name' value='" +rs.getString(3) + "'>");
				out.println("<br><br>");
				out.println("<label>Address </label>");
				out.println("<br>");
				out.println("<input type='text' name='add' id='name' value='" +rs.getString(4) + "'>");
				out.println("<br><br>");
				out.println("<input type='submit' value='saveedit' name='submit' id='submit'>");
				out.println("</form>");

				out.println("</div>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			out.println(e.getMessage());
		}
	}

	
}


