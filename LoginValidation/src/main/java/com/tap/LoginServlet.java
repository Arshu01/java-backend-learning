package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	int count=3;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String Username=req.getParameter("username");
		String Password=req.getParameter("password");
		UserDaoImpl udl=new UserDaoImpl();
		String email=udl.getEmail(Username);
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		String dusername = null;
		String dpassword=null;
		if(email!=null) {
			User user=udl.getUser(email);
			if(user != null) {
				dusername=user.getUsername();
				dpassword=user.getPassword();
				if(Username.equals(dusername) && Password.equals(dpassword)){
					out.println("Congralation, "+dusername+" Logging Sucessful");
				}else{
					count--;
					if(count>0) {
						out.println("Invalid Password, "+count+" Attempts Left");
						RequestDispatcher rd=req.getRequestDispatcher("index.html");
						rd.include(req, resp);
					}else {
						out.println("Account Blocked");
					}
				}
			}else {
				out.println("User Not Found");
			}
	     }
		else {
			out.print("User is not registered with us. Please sign up.");
		RequestDispatcher rd=req.getRequestDispatcher("index.html");
		rd.include(req, resp);
//		out.print("User is not registered with us. Please sign up.");
	}
  }
}
