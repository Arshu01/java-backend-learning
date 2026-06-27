package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.tap.dao.UserDao;
import com.tap.model.User;
import com.utility.DbConnection;
public class UserDaoImpl implements UserDao{
	private final static String ADD_USER="INSERT INTO `user`(username,password,email) values(?,?,?)";
	private final static String GET_EMAIL="SELECT email FROM `user` where `username` =?";
	private final static String GET_USER="SELECT * FROM `user` where `email` =?";
	@Override
	public  int addUser(User u) {
		Connection connection=DbConnection.getConnection();
		PreparedStatement prepareStatement = null;
		int res=0;
		try {
			prepareStatement=connection.prepareStatement(ADD_USER);
			//HERE AUTOMATIC TAKES USERID SO WE DIDN'T ADD
			prepareStatement.setString(1,u.getUsername());
			prepareStatement.setString(2,u.getPassword());
			prepareStatement.setString(3,u.getEmail());
			res=prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(prepareStatement!=null) {
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	
	@Override
	public String getEmail(String username) {
	    Connection connection = DbConnection.getConnection();
	    PreparedStatement prepareStatement = null;
	    String email = null;
	    try {
	    	prepareStatement=connection.prepareStatement(GET_EMAIL);
	    	prepareStatement.setString(1, username);
	        ResultSet rs = prepareStatement.executeQuery();
	        if(rs.next()) {
	            email = rs.getString("email");
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
			if(prepareStatement!=null) {
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	    return email;
	}
	
	
	@Override
	public User getUser(String email)  {
		PreparedStatement prepareStatement = null;
		Connection connection = DbConnection.getConnection();
		User u=null;
		try {
			prepareStatement=connection.prepareStatement(GET_USER);
			prepareStatement.setString(1,email);
			ResultSet result =prepareStatement.executeQuery();
			if(result.next()) {
				int userId=result.getInt("userid");
				String username=result.getString("username");
				String password=result.getString("password");
				String emaill=result.getString("email");
				u = new User(userId,username,password,emaill);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			if(prepareStatement!=null) {
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return u;
	}
}	
