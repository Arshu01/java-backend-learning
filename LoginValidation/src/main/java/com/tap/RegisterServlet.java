package com.tap;

import java.io.IOException;
import java.io.PrintWriter;

import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String email=req.getParameter("email");
		User u=new User(username,password,email);
		UserDaoImpl udi =new UserDaoImpl();
		int res=udi.addUser(u);
		PrintWriter out=resp.getWriter();
		if(res==1) {
			out.println(username+" Your Registration is Sucessful (-_-)");
		}
	}
}
