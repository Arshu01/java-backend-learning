package com.daoimplemention;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.dao.UserDao;
import com.model.User;
import com.utility.DbConnection;

public class UserDaoImp implements UserDao{
	String ADD_USER="INSERT INTO `user` (username,password,email,address,role,createdDate,lastLoginDate) values(?,?,?,?,?,?,?)";
	String GET_USER="SELECT * FROM `user` where `userid` =?";
	String UPDATE_USER="UPDATE `user` SET `password`=?, `email`=?,`address`=?,`role`=? WHERE `userid` =?";
	String DELETE_USER="DELETE FROM `user` WHERE `userid` =?";
	String FETCH_ALL_USERS="SELECT * FROM `user`";
	@Override
	public void addUser(User u) {
		Connection connection=DbConnection.getConnection();
		try {
			PreparedStatement prepareStatement=connection.prepareStatement(ADD_USER);
			//HERE AUTOMATIC TAKES USERID SO WE DIDN'T ADD
			prepareStatement.setString(1,u.getUsername());
			prepareStatement.setString(2,u.getPassword());
			prepareStatement.setString(3,u.getEmail());
			prepareStatement.setString(4,u.getAddress());
			prepareStatement.setString(5,u.getRole());
			prepareStatement.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
			prepareStatement.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
			prepareStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int id) {
		Connection connection = DbConnection.getConnection();
		User u=null;
		try {
			PreparedStatement prepareStatement=connection.prepareStatement(GET_USER);
			prepareStatement.setInt(1, id);
			ResultSet result =prepareStatement.executeQuery();
			while(result.next()) {
				int userId=result.getInt(id);
				String username=result.getString("username");
				String password=result.getString("password");
				String email=result.getString("email");
				String address=result.getString("address");
				String role=result.getString("role");
				u = new User(userId,username,password,email,address,role);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public void updateUser(User u) {
			Connection connection=DbConnection.getConnection();
			try {
				PreparedStatement prepareStatement=connection.prepareStatement(UPDATE_USER);
				prepareStatement.setString(1,u.getPassword());
				prepareStatement.setString(2,u.getEmail());
				prepareStatement.setString(3,u.getAddress());
				prepareStatement.setString(4,u.getRole());
				prepareStatement.setInt(5,u.getUserId());
				prepareStatement.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void deleteUser(int id) {
		Connection connection=DbConnection.getConnection();
		try {
			PreparedStatement prepareStatement=connection.prepareStatement(DELETE_USER);
			prepareStatement.setInt(1,id);
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUser() {
		Connection connection = DbConnection.getConnection();
		List<User> users = new ArrayList<>();
		User u=null;
		try {
			PreparedStatement prepareStatement=connection.prepareStatement(FETCH_ALL_USERS);
			ResultSet result =prepareStatement.executeQuery();
			while(result.next()) {
				int userId=result.getInt("userid");
				String username=result.getString("username");
				String password=result.getString("password");
				String email=result.getString("email");
				String address=result.getString("address");
				String role=result.getString("role");
				u = new User(userId,username,password,email,address,role);
				users.add(u);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return users;
	}
	
}
