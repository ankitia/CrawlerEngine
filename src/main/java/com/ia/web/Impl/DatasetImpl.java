package com.ia.web.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.web.Dao.DatasetDao;
import com.ia.web.Modal.Dataset;
import com.ia.web.SolarExample.CommonUtility;


@Component("datasetDao")
public class DatasetImpl implements DatasetDao {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.user", Locale.getDefault());

	@Autowired
	DataSource dataSource;
	
	
	@Override
	public ArrayList<Dataset> getDatasetList(int userId) {
		ArrayList<Dataset> datasets = new ArrayList<Dataset>();
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("getDatasetList"));
			ps.setInt(1,userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Dataset dataset = new Dataset();
				dataset.setDataSetId(rs.getInt("data_set_id"));
				dataset.setDataSetName(rs.getString("name"));
				dataset.setFileName(rs.getString("file_name"));
				dataset.setProcessName(rs.getString("process_name"));
				dataset.setStatus(rs.getString("status"));
				dataset.setTotalRecord(rs.getDouble("total_record"));
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
	public ArrayList<Dataset> getDatasetList(int userId,int startLimit) {
		ArrayList<Dataset> datasets = new ArrayList<Dataset>();
		try(Connection con = dataSource.getConnection();) {
			
			PreparedStatement ps = con.prepareStatement(userDao.getString("getDatasetLists"));
			ps.setInt(1, userId);
			ps.setInt(2, (startLimit-1)* CommonUtility.NO_OFF_RECORDS );
			ps.setInt(3, CommonUtility.NO_OFF_RECORDS);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Dataset dataset = new Dataset();
				dataset.setDataSetId(rs.getInt("data_set_id"));
				dataset.setDataSetName(rs.getString("name"));
				dataset.setFileName(rs.getString("file_name"));
				dataset.setProcessName(rs.getString("process_name"));
				dataset.setStatus(rs.getString("status"));
				dataset.setTotalRecord(rs.getDouble("total_record"));
				dataset.setActiveUrl(rs.getString("active_url"));
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
	public boolean insertDataset(Dataset dataset) {
		// TODO Auto-generated method stub
		int status = 0;
		String query = "";
		
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("insertDataset"));
			ps.setInt(1, dataset.getUserId());
			ps.setString(2,dataset.getDataSetName());
			ps.setString(3,dataset.getFileName());
			ps.setString(4,dataset.getProcessName());
			ps.setString(5,dataset.getStatus());
			ps.setDouble(6,dataset.getTotalRecord());
			ps.setInt(10,dataset.getProjectId());
			status = ps.executeUpdate();
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if(status==1)  
			return true;
		else		
			return false;
	}

	@Override
	public int lastInsertedRecord() {
		// TODO Auto-generated method stub
		int dataSetId = 0;
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("lastInsertedRecord"));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				 
				dataSetId = rs.getInt("data_set_id");				 
			}
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataSetId;
	}

	@Override
	public String getTaskId() {
		// TODO Auto-generated method stub
		String taskId = "";
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("getTaskId"));
			ResultSet rs = ps.executeQuery();		
			while (rs.next()) {
				
				if(rs.getString("task")!=null)				
					taskId = "Task_"+rs.getString("task");
				else
					taskId = "Task_1";
			}	
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return taskId;
	}

	@Override
	public void loadFile(String filePath,String taskId) {
		// TODO Auto-generated method stub
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("loadFileInDb"));
			ps.setString(1,filePath);
			ps.setString(2,taskId);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public int updateURLStatus(String taskId,String address,String keyContacts,String foundation,String techInstall) {
		// TODO Auto-generated method stub
		
		System.out.println(address +"--"+keyContacts+"---"+techInstall +"---"+foundation);
		
		PreparedStatement ps = null;
		try(Connection con = dataSource.getConnection();) {
			
			if(address!=null && address!=""){
				ps = con.prepareStatement(userDao.getString("updateURLAddressStatus"));
				ps.setString(1, taskId);
				ps.executeUpdate();	
			} if(keyContacts!=null && keyContacts!="") {
				ps = con.prepareStatement(userDao.getString("updateURLKeyContactsStatus"));
				ps.setString(1, taskId);
				ps.executeUpdate();				
			}if(foundation!=null && foundation!="") {
				ps = con.prepareStatement(userDao.getString("updateURLFoundationStatus"));
				ps.setString(1, taskId);
				ps.executeUpdate();	
			}if(techInstall!=null && techInstall!="") {
				ps = con.prepareStatement(userDao.getString("updateURLTechInstallStatus"));
				ps.setString(1, taskId);
				ps.executeUpdate();	
			}
				ps = con.prepareStatement(userDao.getString("updateURLStatus"));
				ps.setString(1, taskId);
				con.close();
				return ps.executeUpdate();	
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateStatus(int dataSetId, String status,int scrapCount) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try(Connection con = dataSource.getConnection();) {
			ps = con.prepareStatement(userDao.getString("updateStatus"));
			System.out.println(status +" -- --"+dataSetId);
			ps.setString(1,status);
			ps.setInt(2, scrapCount);
			ps.setInt(3, dataSetId);
			return ps.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			if(ps!=null)
				ps.close();
			e.printStackTrace();
		} 
				
		return  0;
	}
	
	 
	

	@Override
	public boolean setPendingRequest(int userId,int count) {
		// TODO Auto-generated method stub
		try(Connection con = dataSource.getConnection();PreparedStatement ps = con.prepareStatement(userDao.getString("setPendingRequest"))){
			ps.setInt(1,count);
			ps.setInt(2,userId);
			int updateStatus = ps.executeUpdate();
			if(updateStatus>0)
				return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	 

	
	
}
