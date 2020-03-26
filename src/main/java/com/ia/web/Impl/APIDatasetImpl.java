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

import com.ia.web.Dao.APIDatasetDao;
import com.ia.web.Modal.APIDataset;
import com.mysql.jdbc.Statement;


@Component("aPIDatasetDao")
public class APIDatasetImpl implements APIDatasetDao{

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.user", Locale.getDefault());

	@Autowired
	DataSource dataSource;
	
	
	@Override
	public ArrayList<APIDataset> getAPIDatasetList(int userId) {
		ArrayList<APIDataset> datasets = new ArrayList<APIDataset>();
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("getAPIDatasetList"));
			ps.setInt(1,userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				APIDataset dataset = new APIDataset();
				dataset.setApiDatasetId(rs.getInt("api_dataset_id"));
				dataset.setName(rs.getString("name"));
				dataset.setFileName(rs.getString("file_name"));
				dataset.setStatus(rs.getString("status"));
				dataset.setTotalRecord(rs.getInt("total_record"));
				dataset.setCreatedDate(rs.getString("created_date"));
				dataset.setApiType(rs.getString("api_type"));
				datasets.add(dataset);
			}
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return datasets;
	}
	
	 
	@Override
	public int insertAPIDataset(APIDataset apiDataset) {
		// TODO Auto-generated method stub
		int status = 0;
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("insertAPIDataset"),Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, apiDataset.getName());
			ps.setString(2,apiDataset.getFileName());
			ps.setString(3,apiDataset.getApiType());
			ps.setInt(4,apiDataset.getProjectId());
			ps.setInt(5, apiDataset.getTotalRecord());
			ps.setInt(6, apiDataset.getUserId());
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
	public int updateAPIStatus(String datasetId, String action) {
		// TODO Auto-generated method stub
		int status = 0;
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("updateAPIStatus"));
			ps.setString(1, action);
			ps.setString(2,datasetId);
			status = ps.executeUpdate();
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return status;
	}
}
