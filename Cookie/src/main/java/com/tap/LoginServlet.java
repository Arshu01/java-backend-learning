package com.tap;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		Cookie c1=new Cookie("myUsername", username);           //class me constructor ku pass karthi hi
		Cookie c2=new Cookie("myPassword", password);
		resp.addCookie(c1);
        resp.addCookie(c2);
		resp.sendRedirect("Servlet2");
	}
}
