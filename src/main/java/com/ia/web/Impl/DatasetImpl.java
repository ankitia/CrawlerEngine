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
import com.ia.web.Modal.AddressDataMongo;
import com.ia.web.Modal.Dataset;
import com.ia.web.SolarExample.CommonUtility;
import com.mysql.jdbc.Statement;


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
				dataset.setCreatedDate(rs.getString("created_date"));
				dataset.setSkuStatus(rs.getString("sku_status"));
				dataset.setUrlStatusServer(rs.getInt("url_status_server"));
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
	public int insertDataset(Dataset dataset) {
		// TODO Auto-generated method stub
		int status = 0;
		String query = "";
		
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("insertDataset"),Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, dataset.getUserId());
			ps.setString(2,dataset.getDataSetName());
			ps.setString(3,dataset.getFileName());
			ps.setString(4,dataset.getProcessName());
			ps.setString(5,dataset.getStatus());
			ps.setDouble(6,dataset.getTotalRecord());
			ps.setInt(7,dataset.getProjectId());
			ps.setString(8,dataset.getMarketVariables());
			ps.setString(9,dataset.getAddress());
			ps.setString(10,dataset.getKeyContacts());
			ps.setString(11,dataset.getTechInstall());
			ps.setString(12,dataset.getProductCount());
			ps.setInt(13,dataset.getRulesB2B());
			ps.setInt(14,dataset.getRulesB2c());
			ps.setInt(15,dataset.getRulesProduct());
			ps.setInt(16,dataset.getRulesServices());
			ps.setInt(17,dataset.getRulesManufacturer());
			ps.setInt(18,dataset.getRulesRetail());
			ps.setInt(19,dataset.getRulesAll());
			ps.setInt(20,dataset.getRulesProductCount());
			ps.setInt(21,dataset.getAddressEmail());
			ps.setInt(22,dataset.getAddressForm());
			ps.setInt(23,dataset.getAddressPhone());
			ps.setInt(24,dataset.getAddressAddress());
			ps.setInt(25,dataset.getUrlStatus());
			ps.setInt(26,dataset.getMaxDepth());
			
			System.out.println("dataset.getMaxDepth()-----------"+dataset.getMaxDepth());
			
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
	public String updateStatus(int dataSetId, String status,String action) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try(Connection con = dataSource.getConnection();) {
			
			if(action.equalsIgnoreCase("SKU")) {
				ps = con.prepareStatement(userDao.getString("updateSKUStatus"));
			}else if(action.equalsIgnoreCase("URL_STATUS")) {
				ps = con.prepareStatement(userDao.getString("updateURLtatus"));
			}else {
				ps = con.prepareStatement(userDao.getString("updateStatus"));	
			}
			System.out.println("updateStatus-->"+status +" -- --"+dataSetId);
			ps.setString(1,status);
			ps.setInt(2, dataSetId);
			if(ps.executeUpdate()==1) {
				return "{\"status\":\"true\"}";
			}else {
				return "{\"status\":\"false\"}";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			if(ps!=null)
				ps.close();
			e.printStackTrace();
		} 
		return "{\"status\":\"false\"}";
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

	@Override
	public ArrayList<Dataset> getDatasetProjectList(int projectId) {
		ArrayList<Dataset> datasets = new ArrayList<Dataset>();
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("getDatasetProjectList"));
			ps.setInt(1,projectId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Dataset dataset = new Dataset();
				dataset.setDataSetId(rs.getInt("data_set_id"));
				dataset.setDataSetName(rs.getString("name"));
				dataset.setFileName(rs.getString("file_name"));
				dataset.setProcessName(rs.getString("process_name"));
				dataset.setStatus(rs.getString("status"));
				dataset.setTotalRecord(rs.getDouble("total_record"));
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

	@Override
	public int insertDatasetMongo(AddressDataMongo dataset) {
		// TODO Auto-generated method stub
				int status = 0;
				try(Connection con = dataSource.getConnection();) {
					PreparedStatement ps = con.prepareStatement(userDao.getString("insertDatasetMongo"),Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, dataset.getTld());
					ps.setString(2,dataset.getUrl());
					ps.setString(3,dataset.getAddressline());
					ps.setString(4,dataset.getCity());
					ps.setString(5,dataset.getState_name());
					ps.setString(6,dataset.getState_code());
					ps.setString(7,dataset.getCountry());
					ps.setString(8,dataset.getZip_code());
					ps.setString(9,dataset.getTelephone_number());
					ps.setString(10,dataset.getSource_url());
					ps.setInt(11,dataset.getDataSetId());
					
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
	public Dataset getDataSetDetails(int dataSetId) {
		Dataset dataset = new Dataset();
		try(Connection con = dataSource.getConnection();) {
			PreparedStatement ps = con.prepareStatement(userDao.getString("getDataSetDetails"));
			ps.setInt(1,dataSetId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dataset.setDataSetId(rs.getInt("data_set_id"));
				dataset.setDataSetName(rs.getString("name"));
				dataset.setFileName(rs.getString("file_name"));
				dataset.setProcessName(rs.getString("process_name"));
				dataset.setStatus(rs.getString("status"));
				dataset.setTotalRecord(rs.getDouble("total_record"));
				dataset.setCreatedDate(rs.getString("created_date"));
				dataset.setMarketVariables(rs.getString("market_variables"));
				dataset.setAddress(rs.getString("address"));
				dataset.setKeyContacts(rs.getString("key_contacts"));
				dataset.setTechInstall(rs.getString("tech_install"));
				dataset.setProductCount(rs.getString("product_count"));
				dataset.setSkuStatus(rs.getString("sku_status"));
				dataset.setAddressEmail(rs.getInt("address_email"));
				dataset.setAddressForm(rs.getInt("address_form"));
				dataset.setAddressPhone(rs.getInt("address_phone"));
				dataset.setAddressAddress(rs.getInt("address_address"));
				dataset.setUrlStatus(rs.getInt("url_status"));
				dataset.setUrlStatusServer(rs.getInt("url_status_server"));
				
			}
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataset;
		
	}

	 

	
	
}
