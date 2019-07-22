package com.ia.web.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ia.web.Modal.Dataset;


public interface DatasetDao 
{
	//Get dataset list
	public ArrayList<Dataset> getDatasetList(int userId);
		
	//Get dataset list
	public ArrayList<Dataset> getDatasetList(int userId,int startLimit);
	
	//insert data set
	public boolean insertDataset(Dataset dataset);
	
	//Last inserted record;
	public int lastInsertedRecord();
	
	//get task id
	public String getTaskId();
	
	//load file in db
	public void loadFile(String filePath,String taskId);
	
	//update url statsus
	public int updateURLStatus(String taskId,String address,String keyContacts,String foundation,String techInstall);
	
	//update dataset status
	public int updateStatus(int dataSetId,String status,int scrapCount) throws SQLException;
	
	//Update user limit
	boolean setPendingRequest(int userId,int count);
	
	/*//Get dataset history log
	public ArrayList<DataSetHistoryLog> getDataSetLog(int dataSetId) throws SQLException;*/
	

	
}
