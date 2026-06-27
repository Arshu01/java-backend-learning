package com.tap.dao;

import com.tap.model.User;

public interface UserDao {
	int addUser(User u);
	String getEmail(String username);
	User getUser(String email);
}
