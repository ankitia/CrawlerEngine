package com.ia.web.Dao;

import java.sql.SQLException;
import java.util.List;

import com.ia.web.Modal.User;

public interface HomeDAO {
	
	//Check valid user
	public User checkUser(String userName,String password) throws SQLException;
	
	//Get user deatails
	public User getUserDetails(int userId);
	
	//Get all users
	public List<User> getUserList();
	
 }
