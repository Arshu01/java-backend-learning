package org.tap.jdbc.storedprocedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class program3 {
	private static final String URL="jdbc:mysql://localhost:3306/myntra";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	private static Scanner scan=new Scanner(System.in);
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
		   CallableStatement call=con.prepareCall("{call showEmployees(?)}");
		   System.out.println("Enter salary: ");
		   call.setInt(1,scan.nextInt());
		   
		   boolean res=call.execute();
		   System.out.println(res);
		   
		   ResultSet result=call.getResultSet();
			
			while(result.next()) {
				String E_FNAME=result.getString("E_FNAME");
				String E_LNAME=result.getString("E_LNAME");
				String E_EMAIL=result.getString("E_EMAIL");
				Double E_PHONE_NO=result.getDouble("E_PHONE_NO");
				Date HIRE_DATE=result.getDate("HIRE_DATE");
				int SALARY=result.getInt("SALARY");
				Float COMMISSION_PCT=result.getFloat("COMMISSION_PCT");
				int D_ID=result.getInt("D_ID");
//				System.out.println(id+" "+name+" "+email+" "+dept+" "+salary);
				
				System.out.printf("%-10s %-10s %-25s %-15s %-15s %-10d %-10.2f %-5d%n",E_FNAME,E_LNAME,E_EMAIL,E_PHONE_NO,HIRE_DATE,SALARY, COMMISSION_PCT, D_ID);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
