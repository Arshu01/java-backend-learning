package org.tap.jdbc.storedprocedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Program1 {
	private static final String URL="jdbc:mysql://localhost:3306/myntra";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	private static Scanner scan=new Scanner(System.in);
	public static void main(String[] args) {
		Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			
			CallableStatement call=con.prepareCall("{call cal_Brand(?,?)}");
			
			System.out.print("Enter brand name: ");
			String bname=scan.next();
			
			call.setString(1,bname);
			
			call.registerOutParameter(2 ,Types.INTEGER);
			
			boolean res=call.execute();
			System.out.println(res);
			
			int count=call.getInt(2);
			System.out.println(count);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
  }
}
