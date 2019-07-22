package com.ia.web.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ia.web.Dao.ProjectDao;
import com.ia.web.Modal.Project;


@Component("projectDao")
public class ProjectImpl implements ProjectDao {

	private static ResourceBundle userDao = ResourceBundle.getBundle("com.ia.web.Impl.user", Locale.getDefault());
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public List<Project> getProjects(String action) {
		// TODO Auto-generated method stub
		List<Project> projects = new ArrayList<>();
		String query = "";
		if(action.equalsIgnoreCase("all")) {
			query = userDao.getString("getAllProjects");
		}else if(action.equalsIgnoreCase("active")) {
			query = userDao.getString("getActiveProjects");
		}
		
		try(Connection con = dataSource.getConnection();	
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();){
			
			while (rs.next()) {
				Project project = new Project();
				project.setProjectId(rs.getInt("project_id"));
				project.setName(rs.getString("name"));
				project.setDesc(rs.getString("description"));
				project.setStatus(rs.getString("status")); 
				projects.add(project);
			}
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public boolean insertProject(Project project) {
		// TODO Auto-generated method stub
		
		try(Connection con = dataSource.getConnection();PreparedStatement ps = con.prepareStatement(userDao.getString("insertProject"))){
			ps.setString(1,project.getName());
			ps.setString(2,project.getDesc());
			ps.setInt(3,project.getUserId());
			int status = ps.executeUpdate();
			con.close();
			if(status>0)
				return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateProjectStatus(String status, int projectId) {
		// TODO Auto-generated method stub
		try(Connection con = dataSource.getConnection();PreparedStatement ps = con.prepareStatement(userDao.getString("updateProjectStatus"))){
			ps.setString(1,status);
			ps.setInt(2,projectId);
			int updateStatus = ps.executeUpdate();
			con.close();
			if(updateStatus>0)
				return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
