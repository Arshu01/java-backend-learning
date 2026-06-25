package org.tap.jdbc.blobandclob;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Clob {
	private static final String URL="jdbc:mysql://localhost:3306/myntra";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	private static final String UPDATE_CLOB ="UPDATE `employee1` SET `de`=? WHERE `Employee_ID`=? ";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement psmt=null;
		String path = "C:\\Users\\Arshu\\git\\repository2\\TAP_JDBC\\src\\org\\tap\\jdbc\\blobandclob\\Clob.java";
		try {
			Scanner scan=new Scanner(System.in);
			//Loading the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Connecting DataBase
			con= DriverManager.getConnection(URL,USER,PASSWORD);
			
			//Creating PreparedStatement
			psmt=con.prepareStatement(UPDATE_CLOB);
			System.out.print("Enter id: ");
			int id=scan.nextInt();
			psmt.setInt(2,id);
			
			FileReader fr=new FileReader(path);
			
			psmt.setCharacterStream(1, fr);
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
