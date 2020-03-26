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

import com.ia.web.Dao.MapDao;
import com.ia.web.Modal.AddressComponents;
import com.ia.web.Modal.AddressDetails;
import com.ia.web.Modal.MasterAddress;
import com.mysql.jdbc.Statement;


@Component("mapDao")
public class MapImpl implements MapDao {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.map", Locale.getDefault());

	@Autowired
	DataSource dataSource;
	
	@Override
	public int insertAddressDetails(AddressDetails addressDetails) {
		// TODO Auto-generated method stub
		int status = 0;
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("insertAddressDetails"),Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, addressDetails.getGeometry_bounds_northeast_lat());
			ps.setString(2,addressDetails.getGeometry_bounds_northeast_lng());
			ps.setString(3,addressDetails.getGeometry_bounds_southwest_lat());
			ps.setString(4,addressDetails.getGeometry_bounds_southwest_lng());
			ps.setString(5,addressDetails.getGeometry_location_lat());
			ps.setString(6,addressDetails.getGeometry_location_lng());
			ps.setString(7,addressDetails.getGeometry_location_type());
			ps.setString(8,addressDetails.getGeometry_viewport_northeast_lat());
			ps.setString(9,addressDetails.getGeometry_viewport_northeast_lng());
			ps.setString(10,addressDetails.getGeometry_viewport_southwest_lat());
			ps.setString(11,addressDetails.getGeometry_viewport_southwest_lng());
			ps.setString(12,addressDetails.getPlace_id());
			ps.setString(13,addressDetails.getTypes());
			ps.setString(14,addressDetails.getFormatted_address());
			ps.setString(15,addressDetails.getMain_address());
			ps.setInt(16,addressDetails.getMaster_address_id());
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
	public boolean insertAddressComponents(AddressComponents addressComponents) {
		// TODO Auto-generated method stub
		int status = 0;
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("insertAddressComponents"),Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, addressComponents.getAddressDetailId());
			ps.setString(2,addressComponents.getLong_name());
			ps.setString(3,addressComponents.getShort_name());
			ps.setString(4,addressComponents.getTypes());
			status = ps.executeUpdate();
			con.close();
			
			if(status==1)
				return true;
			else 
				return false;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public ArrayList<MasterAddress> getMasterAddress() {
		ArrayList<MasterAddress> addresses = new ArrayList<>();
		try(Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement(userDao.getString("getMasterAddress"));
			ResultSet rs = statement.executeQuery();) {
			while (rs.next()) {
			MasterAddress address = new MasterAddress();
			address.setMasterAddressId(rs.getInt("master_address_id"));
			address.setAddress(rs.getString("address"));
			addresses.add(address);
			}
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return addresses;
	}
}
