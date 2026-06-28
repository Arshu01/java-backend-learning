package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Servlet2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies=req.getCookies();
		PrintWriter out=resp.getWriter();
		for(Cookie c:cookies) {
		        out.println(c.getName() +"  " + c.getValue());
		}
	}
}
