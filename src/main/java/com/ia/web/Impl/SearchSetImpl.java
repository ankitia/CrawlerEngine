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

import com.ia.web.Dao.SearchSetDao;
import com.ia.web.Modal.Dataset;
import com.ia.web.Modal.SearchDataList;
import com.ia.web.Modal.SearchSet;
import com.ia.web.SolarExample.CommonUtility;
import com.mysql.jdbc.Statement;


@Component("searchSetDao")
public class SearchSetImpl implements SearchSetDao {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.user", Locale.getDefault());

	@Autowired
	DataSource dataSource;
	
	
	@Override
	public int insertSearchData(SearchSet  searchSet) {
		// TODO Auto-generated method stub
		int status = 0;
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("insertSearchData"),Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, searchSet.getName());
			ps.setInt(2,searchSet.getProjectId());
			ps.setInt(3,searchSet.getDataSetId());
			ps.setInt(4,searchSet.getUserId());
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
	public void loadKeyWordFileInDb(String filePath,String dataSetId) {
		// TODO Auto-generated method stub
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("loadKeyWordFileInDb"));
			ps.setString(1,filePath);
			ps.setString(2,dataSetId);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	@Override
	public List<SearchDataList> getSearchData(int userId) {
		// TODO Auto-generated method stub
		ArrayList<SearchDataList> datasets = new ArrayList<SearchDataList>();
		try(Connection con = dataSource.getConnection();) {
			
			PreparedStatement ps = con.prepareStatement(userDao.getString("getSearchData"));
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				SearchDataList dataset = new SearchDataList();
				dataset.setSearch_set_id(rs.getInt("search_set_id"));
				dataset.setName(rs.getString("name"));
				dataset.setProjectName(rs.getString("pname"));
				dataset.setDatasetName(rs.getString("dname"));
				dataset.setStatus(rs.getString("status"));
				dataset.setCreatedDate(rs.getString("created_date"));
				datasets.add(dataset);
			}
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return datasets;
	}
}
