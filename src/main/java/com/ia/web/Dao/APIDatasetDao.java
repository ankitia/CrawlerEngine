package com.ia.web.Dao;

import java.util.ArrayList;

import com.ia.web.Modal.APIDataset;


public interface APIDatasetDao 
{
	//Get API dataset list
	public ArrayList<APIDataset> getAPIDatasetList(int userId);

	//insert API data set
	public int insertAPIDataset(APIDataset apiDataset);
	
	//Update API status
	public int  updateAPIStatus(String datasetId,String action);
	
}
