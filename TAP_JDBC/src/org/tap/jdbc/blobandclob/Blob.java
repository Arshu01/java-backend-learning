package org.tap.jdbc.blobandclob;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Blob {
	private static final String URL="jdbc:mysql://localhost:3306/myntra";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	private static final String UPDATE_BLOB ="UPDATE `employee1` SET `dp`=? WHERE `Employee_ID`=? ";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement psmt=null;
		String path = "C:\\Users\\Arshu\\git\\repository2\\TAP_JDBC\\src\\assets\\blob1.jpg";
		try {
			Scanner scan=new Scanner(System.in);
			//Loading the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Connecting DataBase
			con= DriverManager.getConnection(URL,USER,PASSWORD);
			
			//Creating PreparedStatement
			psmt=con.prepareStatement(UPDATE_BLOB);
			System.out.print("Enter id: ");
			int id=scan.nextInt();
			psmt.setInt(2,id);
			
			FileInputStream fs=new FileInputStream(path);
			
			psmt.setBinaryStream(1, fs);
			int res =psmt.executeUpdate();
			System.out.println(res);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (FileNotFoundException e) {
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
