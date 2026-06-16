package org.tap.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
	
public class Fetch_Data{
	private static final String URL="jdbc:mysql://localhost:3306/tapjdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	// ? this is know as placeholder 
	private static final String FETCH_DATA ="SELECT * FROM employee where dept = ?"; 
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement psmt=null;
		try {
			Scanner scan =new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection(URL,USER,PASSWORD);
			
			psmt=con.prepareStatement(FETCH_DATA);
			System.out.print("Enter dept name: ");
			String deptName=scan.next();
			psmt.setString(1,deptName);
			
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
			if(psmt!=null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
 	
