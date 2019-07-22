package com.ia.web.SolarExample;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.ia.web.Dao.HomeDAO;
import com.ia.web.Dao.ProjectDao;
import com.ia.web.Modal.Project;
import com.ia.web.Modal.User;

@Controller
public class HomeController {

	@Autowired
	HomeDAO homeDao;
	
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="/")
	public String home(Model model,HttpSession session) {
	
	      
		return "login";
	}
	
	//Check valid user
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST )
	public String checkUser(HttpServletRequest request,Model model,HttpSession session) throws SQLException {
		 User user = homeDao.checkUser(request.getParameter("Email"), request.getParameter("password"));		
		 if(user!=null) {		 
			 session.setAttribute("userName", request.getParameter("Email"));
			 session.setAttribute("userId", user.getUserId());
			 CommonUtility.LOGIN_USER_ID = user.getUserId();
			 CommonUtility.USER_ROLE_ID = user.getUserRoleId();
			 
			 System.out.println("getUserId----->"+user.getUserId()); 
			 
			 User updateUser = homeDao.getUserDetails(user.getUserId());			 
			 context.setAttribute("userDetails",updateUser);
			 
			 //return "redirect:front";
			 return "dashboard";
		 }else {
			 model.addAttribute("message","Please enter valid username and password");
			 return "login";
		 }	
	}
	
	@RequestMapping(value = "/logout")
	public RedirectView logout(HttpSession session,HttpServletRequest request) {
		session.setAttribute("userName",null);  
		return new RedirectView(request.getContextPath()+"/");
	}
	
	@RequestMapping(value = "/dataSet", method = RequestMethod.GET )
	public String scrap(HttpServletRequest request,Model model,HttpSession session) throws SQLException {
		model.addAttribute("projectList", projectDao.getProjects("all"));
		return "data_set";
	}
	
	@RequestMapping(value = "/manageDataset", method = RequestMethod.GET )
	public String manage_dataset(HttpServletRequest request,Model model,HttpSession session) throws SQLException {
		  
		System.out.println("dataSet");
		return "manage_dataset";
	}
	
	@RequestMapping(value="/project")
	 public String project(Model model) {
		 
		 
		 
		 return "project";
	 }
	
	@RequestMapping(value="/manageProject")
	 public String manageProject(Model model) {
		 
		 model.addAttribute("projectList", projectDao.getProjects("all"));
		 
		 return "manage_project";
	 }
	 
	 @RequestMapping(value="/manageSearch")
	 public String manage_searchengine(Model model) {
		 
		 return "manage_searchengine";
	 }
	
	@RequestMapping(value="/searchEngine")
	 public String searchengine(Model model) {
		 
		model.addAttribute("projectList", projectDao.getProjects("all"));
		 
		 return "searchengine";
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
