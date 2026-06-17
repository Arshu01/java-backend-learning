package org.tap.jdbc.acidproperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Display {
	private static final String FETCH_DATA ="SELECT * FROM employee"; 
	
	public static void display(Connection con) {
		
		try {
			PreparedStatement psmt=con.prepareStatement(FETCH_DATA);
			ResultSet result=psmt.executeQuery();
			while(result.next()) {
				int id=result.getInt("id");
				String name=result.getString("name");
				String email=result.getString("email");
				String dept=result.getString("dept");
				int salary=result.getInt("salary");
//				System.out.println(id+" "+name+" "+email+" "+dept+" "+salary);
				
				System.out.printf("%-3d %-7s %-22s %-9s %d\n",id,name,email,dept,salary);
			}
			  result.close();
		      psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
