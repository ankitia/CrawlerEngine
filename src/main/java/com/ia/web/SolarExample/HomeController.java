package com.ia.web.SolarExample;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ia.web.Dao.HomeDAO;
import com.ia.web.Dao.MapDao;
import com.ia.web.Dao.ProjectDao;
import com.ia.web.Dao.SearchSetDao;
import com.ia.web.Modal.AddressDetails;
import com.ia.web.Modal.Attribute;
import com.ia.web.Modal.MasterAddress;
import com.ia.web.Modal.Request;
import com.ia.web.Modal.SearchSet;
import com.ia.web.Modal.User;

@Controller
public class HomeController {

	@Autowired
	HomeDAO homeDao;
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	SearchSetDao searchSetDao;
	
	@Autowired
	MapDao mapDao;
	
	@RequestMapping(value="/")
	public String home(Model model,HttpSession session) {
	
		System.out.println("userId--->"+session.getAttribute("userName"));
		
		if(session.getAttribute("userName")!=null) {
			return "redirect:dashboard";
		} 
	      
		return "login";
	}
	
	//Check valid user
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST )
	public String checkUser(HttpServletRequest request,Model model,HttpSession session) throws SQLException {
		 User user = homeDao.checkUser(request.getParameter("Email"), request.getParameter("password"));		
		 if(user!=null && user.getUserId() > 0) {		 
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
	
	@RequestMapping(value="/dashboard")
	 public String dashboard(Model model,HttpSession session) {
		 
		 model.addAttribute("getSearchData",searchSetDao.getSearchData(Integer.parseInt(session.getAttribute("userId")+"")));	
		 
		 return "dashboard";
	 }
	
	@RequestMapping(value = "/logout")
	public RedirectView logout(HttpSession session,HttpServletRequest request) {
		
		session.setAttribute("userName",null);  
		return new RedirectView(request.getContextPath()+"/");
	}
	
	 @RequestMapping(value="/manageSearch")
	 public String manage_searchengine(Model model,HttpSession session) {
		 
		 model.addAttribute("getSearchData",searchSetDao.getSearchData(Integer.parseInt(session.getAttribute("userId")+"")));	
		 
		 return "manage_searchengine";
	 }
	
	@RequestMapping(value="/searchEngine")
	 public String searchengine(Model model) {
		if(context.getAttribute("userDetails")==null) {
			return "redirect:/";
		}
		model.addAttribute("projectList", projectDao.getProjects("all"));
		 
		 return "searchengine";
	 }
	 
	
	@RequestMapping(value="/insertSearchEngine")
	 public String insertSearchEngine(@RequestParam("exampleInputFile") MultipartFile file,SearchSet searchSet,Model model,HttpSession session) {
		 
		 System.out.println(searchSet.getName()+"--"+searchSet.getDataSetId());
		
		 searchSet.setUserId(Integer.parseInt(session.getAttribute("userId")+""));	
		 
		 
		 String path = "";
			String orignalFileName = file.getName();
			if (!file.isEmpty()) {
				path = CommonUtility.fileUpload(orignalFileName, file);	
			} else {
				//return "You failed to upload " + name + " because the file was empty.";
				System.out.println("File is empty");
			}
		 //searchSet.set
		 
		 int searchSetId = searchSetDao.insertSearchData(searchSet);
		
		 searchSetDao.loadKeyWordFileInDb(path, searchSetId+"");
		 
		 return "redirect:manageSearch";
	 }
	
	@RequestMapping(value="/insertSearchSet")
	 public String insertSearchSet(SearchSet searchSet,Model model) {
		 
		 
		 return "searchengine";
	 }
	
	
	
	
	 @RequestMapping(value="sampleRequest")
	 @ResponseBody public Request sampleRequest() {
		 
		 
		 String[] urls = {"Apple.com", "Banana.com", "Orange.com", "Grapes.com"};
		 
		 Attribute attribute = new Attribute();
		 //attribute.setAddress(1);
		 attribute.setKey_contact(0);
		 attribute.setMarket_variables(1);
		 attribute.setSku(0);
		 attribute.setTech_install(1);
		 
		 Request request = new Request();
		 request.setAppid("1");
		 request.setCrawlid("2");
		 request.setUrls(urls);
		 request.setAttrs(attribute);
		 
		 // "http://192.168.1.178:5343/feeds";
		 ObjectMapper mapper = new ObjectMapper();
		 String payload;
		try {
			payload = mapper.writeValueAsString(request);
			CommonUtility.sendPostRequest(CommonUtility.ENRICH_URL, payload);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return request;
	 }
	 

	 @RequestMapping(value="/sampleMapData")
	 @ResponseBody	public String sampleMapData(AddressDetails addressDetails) {
		 //CommonUtility.mapRequest("6348+34TH+AVE+SW,+SEATTLE,+WA,+98126",1, mapDao);
		  
		 ArrayList<MasterAddress> addresses = mapDao.getMasterAddress();
		 for (int i = 0; i < addresses.size(); i++) {
			 System.out.println(addresses.get(i).getAddress());
			 /*if(i < 2) {*/
				 CommonUtility.mapRequest(addresses.get(i).getAddress(),addresses.get(i).getMasterAddressId(), mapDao);	 
			 /*}*/
		}
			return "login";
		}
	 
}
