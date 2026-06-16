package org.tap.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
	
public class Inserting_New_Row {
	
	private static final String URL="jdbc:mysql://localhost:3306/tapjdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	private static final String INSERT_ROW_EMPLOYEE ="INSERT INTO `employee`(`id`,`name`,`email`,`dept`,`salary`)"
			+ "VALUES(?,?,?,?,?)";
	
	
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement psmt=null;
		try {
			Scanner scan=new Scanner(System.in);
			//Loading the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Connecting DataBase
			con= DriverManager.getConnection(URL,USER,PASSWORD);
			
			//Creating PreparedStatement
			psmt=con.prepareStatement(INSERT_ROW_EMPLOYEE);
			System.out.print("Enter id: ");
			int id=scan.nextInt();
			System.out.print("Enter name: ");
			String name = scan.next();
			System.out.print("Enter email: ");
			String email = scan.next();
			System.out.print("Enter dept: ");
			String dept = scan.next();
			System.out.print("Enter salary :");
			int salary=scan.nextInt();
			
			psmt.setInt(1,id);
			psmt.setString(2,name);
			psmt.setString(3,email);
			psmt.setString(4,dept);
			psmt.setInt(5,salary);
			//Execute Query
			int result=psmt.executeUpdate();
			
			System.out.println(result);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
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
 	