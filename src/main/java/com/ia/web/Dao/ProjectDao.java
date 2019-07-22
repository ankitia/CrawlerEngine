package com.ia.web.Dao;

import java.util.List;

import com.ia.web.Modal.Project;

public interface ProjectDao {

	//Get project list
	List<Project> getProjects(String action);
	
	//Insert new project
	boolean insertProject(Project project);
	
	//Update status
	boolean updateProjectStatus(String status,int projectId);
	
}
