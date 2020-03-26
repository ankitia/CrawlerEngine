package com.ia.web.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.web.Dao.EmailDAO;
import com.ia.web.Modal.EmailData;
import com.ia.web.Modal.MasterEmail;
import com.mysql.jdbc.Statement;


@Component("emailDAO")
public class EmailImpl implements EmailDAO {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.map", Locale.getDefault());

	@Autowired
	DataSource dataSource;
	
	@Override
	public int insertEmailData(EmailData emailData) {
		// TODO Auto-generated method stub
		int status = 0;
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("insertemailData"),Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, emailData.getEmailNumber());
			ps.setString(2,emailData.getSubject());
			ps.setString(3,emailData.getEmailFrom());
			ps.setString(4,emailData.getEmailTo());
			ps.setString(5,emailData.getReceivedDate());
			ps.setString(6,emailData.getSendDate());
			ps.setString(7,emailData.getContent());
			ps.setInt(8,emailData.getMasterEmailId());
			ps.setString(9,emailData.getContactList());
			ps.setString(10,emailData.getEmailList());
			status = ps.executeUpdate();
			 ResultSet keys = ps.getGeneratedKeys();
			 while (keys.next()) {
				 status = keys.getInt(1);
			    }
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return status;
	}
 
	@Override
	public ArrayList<MasterEmail> getEmailData(int status) {
		ArrayList<MasterEmail> addresses = new ArrayList<>();
		try(Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement(userDao.getString("getEmailData"));
			ResultSet rs = statement.executeQuery();) {
			
			System.out.println(con.isClosed());
			
			while (rs.next()) {
			MasterEmail address = new MasterEmail();
			address.setId(rs.getInt("master_email_id"));
			address.setEmailId(rs.getString("email_id"));
			address.setPassword(rs.getString("password"));
			address.setHost(rs.getString("host"));
			addresses.add(address);
			}
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return addresses;
	}
}
