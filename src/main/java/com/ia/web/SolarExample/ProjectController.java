package com.ia.web.SolarExample;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ia.web.Dao.ProjectDao;
import com.ia.web.Modal.Project;

@Controller
public class ProjectController {

	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	ServletContext context;
	
	
	@RequestMapping(value="/project")
	 public String project(Model model) {
		if(context.getAttribute("userDetails")==null) {
			return "redirect:/";
		}
		 return "project";
	 }
	
	@RequestMapping(value="/manageProject")
	 public String manageProject(Model model) {
		if(context.getAttribute("userDetails")==null) {
			return "redirect:/";
		}
		 model.addAttribute("projectList", projectDao.getProjects("all"));
		 return "manage_project";
	 }
	
	 @RequestMapping(value="/insertProject")
	 @ResponseBody public List<Project> insertProject(HttpServletRequest request,HttpSession session){
		 
		 Project project = new Project();
		 project.setName(request.getParameter("name"));
		 project.setDesc(request.getParameter("desc"));
		 project.setUserId((Integer) session.getAttribute("userId"));
		 
		 projectDao.insertProject(project);
		 
		 return projectDao.getProjects("all");
		 
	 }
	 
	 @RequestMapping(value="/updateProjectStatus")
	 @ResponseBody public String updateProjectStatus(HttpServletRequest request,HttpSession session){
		 return projectDao.updateProjectStatus(request.getParameter("status"),Integer.parseInt(request.getParameter("projectId")))+"";
		 
	 }
	
	
}
