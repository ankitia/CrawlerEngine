package com.ia.web.Dao;

import java.util.List;

import com.ia.web.Modal.SearchDataList;
import com.ia.web.Modal.SearchSet;


public interface SearchSetDao 
{
	//insert search url set
	public int insertSearchData(SearchSet searchSet);

	public void loadKeyWordFileInDb(String filePath,String datasetId);
	
	public List<SearchDataList> getSearchData(int userId);
	
}
