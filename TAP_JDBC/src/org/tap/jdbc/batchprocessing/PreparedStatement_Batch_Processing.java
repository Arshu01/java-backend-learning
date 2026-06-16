package org.tap.jdbc.batchprocessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;		
	
public class PreparedStatement_Batch_Processing{
	
	private static final String URL="jdbc:mysql://localhost:3306/tapjdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	private static final String INSERT_ROW_EMPLOYEE ="INSERT INTO `employee`(`id`,`name`,`email`,`dept`,`salary`)"
			+ "VALUES(?,?,?,?,?)";
	private static final String DELETE_ROW_EMPLOYEE ="DELETE FROM `employee` WHERE `id`=?"; 
	
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement psmtInsert=null;
		PreparedStatement psmtDelete=null;
		try {
			Scanner scan=new Scanner(System.in);
			//Loading the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Connecting DataBase
			con= DriverManager.getConnection(URL,USER,PASSWORD);
			
			psmtInsert=con.prepareStatement(INSERT_ROW_EMPLOYEE);
			psmtDelete=con.prepareStatement(DELETE_ROW_EMPLOYEE);
			
			do{
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
				
				psmtInsert.setInt(1,id);
				psmtInsert.setString(2,name);
				psmtInsert.setString(3,email);
				psmtInsert.setString(4,dept);
				psmtInsert.setInt(5,salary);
				
				psmtInsert.addBatch();
				System.out.print("Again you want to insert values YES/NO: ");
			}while(scan.next().equals("YES"));
			
			
			int[] result = psmtInsert.executeBatch();  //it will return [1 1 1] because each one --> psmt.executeBatch() return 1 
			for(int i = 0; i < result.length; i++) { 
			    System.out.println("Row " + (i + 1) + " inserted");
			}
			
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
			if(psmtInsert!=null) {
				try {
					psmtInsert.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
 	
