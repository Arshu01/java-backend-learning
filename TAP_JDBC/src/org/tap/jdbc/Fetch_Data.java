package org.tap.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	
public class Fetch_Data{
	private static final String URL="jdbc:mysql://localhost:3306/tapjdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	private static final String FETCH_DATA ="SELECT * FROM employee";
	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection(URL,USER,PASSWORD);
			
			stmt=con.createStatement();
			ResultSet result=stmt.executeQuery(FETCH_DATA);
			
			while(result.next()) {
				int id=result.getInt("id");
				String name=result.getString("name");
				String email=result.getString("email");
				String dept=result.getString("dept");
				int salary=result.getInt("salary");
//				System.out.println(id+" "+name+" "+email+" "+dept+" "+salary);
				
				System.out.printf("%-3d %-7s %-22s %-9s %d\n",id,name,email,dept,salary);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
 	
