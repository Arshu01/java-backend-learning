package org.tap.jdbc.storedprocedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class program2 {
	private static final String URL="jdbc:mysql://localhost:3306/myntra";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	private static Scanner scan=new Scanner(System.in);
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
		   CallableStatement call=con.prepareCall("{call count_employee_salary(?)}");
		   System.out.println("Enter salary: ");
		   call.setInt(1,scan.nextInt());
		   call.registerOutParameter(1, Types.INTEGER);
		   
		   boolean res=call.execute();
		   System.out.println(res);
		   
		   int count=call.getInt(1);
		   System.out.println(count);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
