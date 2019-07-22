package com.ia.web.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.web.Dao.HomeDAO;
import com.ia.web.Modal.User;



@Component("homeDao")
public class HomeIMPL implements HomeDAO {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.user", Locale.getDefault());
	
	
	@Autowired
	DataSource dataSource;
	
	
	
	@Override
	public User checkUser(String userName, String password) throws SQLException {
		// TODO Auto-generated method stub
		int userId = 0;
		User user = new User();
				
		try(Connection con = dataSource.getConnection();){			 
			PreparedStatement statement = con.prepareStatement(userDao.getString("checkUser")); 
			statement.setString(1,userName);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();	
				while (rs.next()) {
					userId = rs.getInt("user_id");					
					user.setUserId(rs.getInt("user_id"));
					user.setFname(rs.getString("fname"));
					user.setLimit(rs.getInt("limit"));
					user.setLname(rs.getString("lname"));
					user.setServerIp(rs.getString("server_ip"));
					user.setUserName(rs.getString("username"));
					user.setUsageLimit(rs.getInt("usage_limit"));
					user.setUserRoleId(rs.getInt("role_id"));
					user.setPendingRequest(rs.getString("pending_request")==null?0:rs.getInt("pending_request"));
				}	
				con.close();
			}catch (Exception e) {
				 
				e.printStackTrace();
			}
		return user;
	}

	
 
	

	@Override
	public User getUserDetails(int userId) {
		// TODO Auto-generated method stub
		User user = new User();
		try(Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement(userDao.getString("getUserDetails"));){			 
			statement.setInt(1,userId);
			ResultSet rs = statement.executeQuery();	
				while (rs.next()) {
					user.setUserId(rs.getInt("user_id"));
					user.setFname(rs.getString("fname"));
					user.setLimit(rs.getInt("limit"));
					user.setLname(rs.getString("lname"));
					user.setServerIp(rs.getString("server_ip"));
					user.setUserName(rs.getString("username"));
					user.setUsageLimit(rs.getInt("usage_limit"));
					user.setUserRoleId(rs.getInt("role_id"));
					user.setPendingRequest(rs.getString("pending_request")==null?0:rs.getInt("pending_request"));
				}
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		return user;
		
		
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<>();
		try(Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement(userDao.getString("getUserList"));
			ResultSet rs = statement.executeQuery();) {
			
			while (rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setFname(rs.getString("fname"));
			user.setLimit(rs.getInt("limit"));
			user.setLname(rs.getString("lname"));
			user.setServerIp(rs.getString("server_ip"));
			user.setUserName(rs.getString("username"));
			user.setUsageLimit(rs.getInt("usage_limit"));
			user.setPassword(rs.getString("password"));
			user.setUserRoleId(rs.getInt("role_id"));
			user.setPendingRequest(rs.getString("pending_request")==null?0:rs.getInt("pending_request"));
			users.add(user);
			
			}
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return users;
	}

}