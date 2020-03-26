package com.ia.web.Modal;

public class SearchDataList 
{

	private int search_set_id;
	private String name;
	private String projectName;
	private String datasetName;
	private String status;
	private String createdDate;
	
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public int getSearch_set_id() {
		return search_set_id;
	}
	public void setSearch_set_id(int search_set_id) {
		this.search_set_id = search_set_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDatasetName() {
		return datasetName;
	}
	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
