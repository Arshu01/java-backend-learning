package org.tap.jdbc.acidproperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Program1 {
	private static final String URL="jdbc:mysql://localhost:3306/tapjdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";
	private static Scanner scan=new Scanner(System.in);
	private static final String UPDATE_SENDER ="UPDATE `employee` SET `salary`=`salary`-? WHERE `name`=? ";
	private static final String UPDATE_RECEIVER ="UPDATE `employee` SET `salary`=`salary`+? WHERE `name`=? ";
	private static final String FETCH_SALARY ="SELECT salary FROM employee WHERE name=?";
	
	static Connection con= null;
	static PreparedStatement psmt=null;
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			
			Display.display(con);
				
			con.setAutoCommit(false);
			
			 transcation();
			 
			 Display.display(con);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    try {
		        if(psmt != null)
		            psmt.close();

		    } catch(SQLException e) {
		        e.printStackTrace();
		    } if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

	private static boolean helper(int x,int y) {
		// TODO Auto-generated method stub
		System.out.println(x+":"+y);
		System.out.println("If you want to continue transction YES/NO");
		return scan.next().equalsIgnoreCase("yes") && x==1 && y==1;
	}

	public static void transcation() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Enter sender name: ");
		String sender=scan.next();
		System.out.println("Enter receiver name: ");
		String receiver=scan.next();
		System.out.println("Enter amount: ");
		
		int amount=scan.nextInt();
		
		int balance = getBalance(sender);

		if(balance < amount) {
		    System.out.println("Insufficient Balance");
		    con.rollback();
		    return;
		}
		
		int x=updateSender(sender,amount);
		int y=updateReceiver(receiver,amount);
		
		if(helper(x,y)) {
			System.out.println("Transcation is succesfull");
			con.commit();
		}else {
			con.rollback();
			System.out.println("Transcation is unsuccesfull");
		}
	}
	
	public static int getBalance(String sender) {

	    int salary = 0;
	    ResultSet  rs=null;

	    try {
	       psmt = con.prepareStatement(FETCH_SALARY);
	        psmt.setString(1, sender);

	        rs = psmt.executeQuery();

	        if(rs.next()) {
	            salary = rs.getInt("salary");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	    	if(psmt != null)
					try {
						psmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }

	    return salary;
	}

	public static int updateReceiver(String receiver, int amount) {
		// TODO Auto-generated method stub
		int result = 0;
	try {
			psmt=con.prepareStatement(UPDATE_RECEIVER);
			psmt.setInt(1, amount);
			psmt.setString(2, receiver);
			result =psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return result;
	}

	public static int updateSender(String sender,int amount) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			
			psmt=con.prepareStatement(UPDATE_SENDER);
			psmt.setInt(1, amount);
			psmt.setString(2, sender);
			result =psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
