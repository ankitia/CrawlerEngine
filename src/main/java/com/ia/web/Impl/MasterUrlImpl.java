package com.ia.web.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.web.Dao.MasterUrlDao;
import com.ia.web.Modal.MasterUrl;
import com.mysql.jdbc.Statement;


@Component("masterUrlDao")
public class MasterUrlImpl implements MasterUrlDao {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.user", Locale.getDefault());

	@Autowired
	DataSource dataSource;
	
	
	@Override
	public int insertMasterUrl(MasterUrl masterUrl) {
		// TODO Auto-generated method stub
		int status = 0;
		String query = "";
		
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("insertMasterUrl"),Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, masterUrl.getRequestUrl());
			ps.setInt(2,masterUrl.getDatasetId());
			status = ps.executeUpdate();
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return status;
	}

}
