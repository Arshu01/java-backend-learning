package org.tap.jdbc.acidproperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Program1 {
	private static final String URL="jdbc:mysql://localhost:3306/tapjdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	private static Scanner scan=new Scanner(System.in);
	private static final String UPDATE_SENDER ="UPDATE `employee` SET `salary`=`salary`-? WHERE `name`=? ";
	private static final String UPDATE_RECEIVER ="UPDATE `employee` SET `salary`=`salary`+? WHERE `name`=? ";
	static Connection con= null;
	static PreparedStatement psmt=null;
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			Display.display(con);
			 transcation();
			 Display.display(con);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void transcation() {
		// TODO Auto-generated method stub
		System.out.println("Enter sender name: ");
		String sender=scan.next();
		System.out.println("Enter receiver name: ");
		String receiver=scan.next();
		System.out.println("Enter amount: ");
		int amount=scan.nextInt();
		updateSender(sender,amount);
		updateReceiver(receiver,amount);
	}

	public static void updateReceiver(String receiver, int amount) {
		// TODO Auto-generated method stub
	try {
			psmt=con.prepareStatement(UPDATE_RECEIVER);
			psmt.setInt(1, amount);
			psmt.setString(2, receiver);
			int result =psmt.executeUpdate();
			System.out.print(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void updateSender(String sender,int amount) {
		// TODO Auto-generated method stub
		try {
			
			psmt=con.prepareStatement(UPDATE_SENDER);
			psmt.setInt(1, amount);
			psmt.setString(2, sender);
			int result =psmt.executeUpdate();
			System.out.print(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
