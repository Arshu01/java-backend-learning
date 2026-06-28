package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class Servlet1 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		LocalDate ld=LocalDate.now();
//		PrintWriter out=resp.getWriter();
//		out.println(ld);
//		out.println(ld.getDayOfMonth());
//		out.println(ld.getDayOfYear());
		HttpSession session=req.getSession();
		session.setAttribute("myUsername", req.getParameter("username"));
		session.setAttribute("myPassword", req.getParameter("password"));
		resp.sendRedirect("first.jsp");
	}
}
