package com.main;

import java.util.List;
import java.util.Scanner;
import com.daoimplemention.UserDaoImp;
import com.model.User;

public class Launch {

	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		
		//Inserting row values
		
//		System.out.println("Enter the username");
//		String username=scan.next();
//		
//		System.out.println("Enter the password");
//		String password=scan.next();
//		
//		System.out.println("Enter the email");
//		String email=scan.next();
//		
//		System.out.println("Enter the address");
//		String address=scan.next();
//		
//		System.out.println("Enter the role");
//		String role=scan.next();
//		User u=new User(username,password,email,address,role);
//		UserDaoImp usi =new UserDaoImp();
//		usi.addUser(u);
//		System.out.println("inserted");
		
		// Getting row
		
//		System.out.print("Enter the id to remove row: ");
//		int id=scan.nextInt();
//		UserDaoImp udi =new UserDaoImp();
//		User u=udi.getUser(id);
//		System.out.println(u);
		
		//Updating row
//		System.out.print("Enter the id to update:");
//		int id=scan.nextInt();
//		System.out.print("Enter new Email:");
//		String email=scan.next();
//		UserDaoImp udi =new UserDaoImp();
//		User u=udi.getUser(id);
//		u.setEmail(email);
//		udi.updateUser(u);
//		System.out.println(u);
		
		
		//Deleting row
//		System.out.print("Enter the id to remove row: ");
//		int id=scan.nextInt();
//		UserDaoImp udi =new UserDaoImp();
//		udi.deleteUser(id);
//		System.out.println("Deleted");
		
		//Fetching All database
		UserDaoImp udi =new UserDaoImp();
		List<User> users=udi.getAllUser();
		for (User u : users) {
		    System.out.println(u);
		}
	}

}
