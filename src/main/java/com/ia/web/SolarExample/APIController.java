package com.ia.web.SolarExample;

import java.sql.SQLException;
import java.util.HashMap;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ia.web.Dao.APIDatasetDao;
import com.ia.web.Dao.ProjectDao;
import com.ia.web.Modal.APIDataset;
import com.ia.web.Modal.AddressAttribute;
import com.ia.web.Modal.Attribute;
import com.ia.web.Modal.Request;


@Controller
public class APIController {


	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	APIDatasetDao aPIDatasetDao;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="/manageSmartystreets")
	 public String manageSmartystreets(HttpServletRequest request,Model model,HttpSession session) {
		if(context.getAttribute("userDetails")==null) {
			return "redirect:/";
		}
		
		if(request.getParameter("uploadLimit")!=null) {
			model.addAttribute("uploadLimit",request.getParameter("uploadLimit"));
		}
		
		model.addAttribute("getAPIDatasetList", aPIDatasetDao.getAPIDatasetList((Integer) session.getAttribute("userId")));
		
		 return "api_dataset_list";
	 }
	
	
	@RequestMapping(value = "/apiDataSet", method = RequestMethod.GET )
	public String apiDataSet(HttpServletRequest request,Model model,HttpSession session) throws SQLException {
		if(context.getAttribute("userDetails")==null) {
			return "redirect:/";
		}
		model.addAttribute("projectList", projectDao.getProjects("all"));
		return "api_dataset";
	}
	
	 @RequestMapping(value = "/insertAPIDataset"  , method = RequestMethod.POST)
		public String insertAPIDataset(@RequestParam("exampleInputFile") MultipartFile file,HttpServletRequest req,HttpSession session,RedirectAttributes redirectAttributes) {
			
		 System.out.println("marketVariables--->"+req.getParameter("marketVariables"));
			
		 	int marketVariables = 0,address=0,keyContacts=0,techInstall=0,productCount=0,urlStatus=0;
		 	int rulesB2B=0,rulesB2c=0,rulesProduct=0,rulesServices=0,rulesManufacturer=0,rulesRetail=0,rulesAll=0;
		 	int addressForm=0,addressEmail=0,addressAddress=0,addressPhone=0;
		 	
		 	if(req.getParameter("marketVariables")!=null && req.getParameter("marketVariables").equalsIgnoreCase("on")) {
		 		marketVariables =1;
		 	}
		 	if(req.getParameter("address")!=null && req.getParameter("address").equalsIgnoreCase("on")) {
		 		address =1;
		 	}
		 	if(req.getParameter("keyContacts")!=null && req.getParameter("keyContacts").equalsIgnoreCase("on")) {
		 		keyContacts =1;
		 	}
		 	if(req.getParameter("techInstall")!=null && req.getParameter("techInstall").equalsIgnoreCase("on")) {
		 		techInstall =1;
		 	}
		 	if(req.getParameter("productCount")!=null && req.getParameter("productCount").equalsIgnoreCase("on")) {
		 		productCount=1;
		 	}
		 	if(req.getParameter("b2b")!=null && req.getParameter("b2b").equalsIgnoreCase("on")) {
		 		rulesB2B=1;
		 	}
		 	if(req.getParameter("b2c")!=null && req.getParameter("b2c").equalsIgnoreCase("on")) {
		 		rulesB2c=1;
		 	}
		 	if(req.getParameter("product")!=null && req.getParameter("product").equalsIgnoreCase("on")) {
		 		rulesProduct=1;
		 	}
		 	if(req.getParameter("services")!=null && req.getParameter("services").equalsIgnoreCase("on")) {
		 		rulesServices=1;
		 	}
		 	if(req.getParameter("manufacturer")!=null && req.getParameter("manufacturer").equalsIgnoreCase("on")) {
		 		rulesManufacturer=1;
		 	}
		 	if(req.getParameter("retail")!=null && req.getParameter("retail").equalsIgnoreCase("on")) {
		 		rulesRetail=1;
		 	}
		 	if(req.getParameter("all")!=null && req.getParameter("all").equalsIgnoreCase("on")) {
		 		rulesAll=1;
		 	}
		 	
		 	if(req.getParameter("addressEmail")!=null && req.getParameter("addressEmail").equalsIgnoreCase("on")) {
		 		addressEmail=1;
		 	}
		 	if(req.getParameter("addressTelephone")!=null && req.getParameter("addressTelephone").equalsIgnoreCase("on")) {
		 		addressPhone=1;
		 	}
		 	if(req.getParameter("addressForm")!=null && req.getParameter("addressForm").equalsIgnoreCase("on")) {
		 		addressForm=1;
		 	}
		 	if(req.getParameter("addressAddress")!=null && req.getParameter("addressAddress").equalsIgnoreCase("on")) {
		 		addressAddress=1;
		 	}
		 	if(req.getParameter("urlStatus")!=null && req.getParameter("urlStatus").equalsIgnoreCase("on")) {
		 		urlStatus=1;
		 	}
		 	
		 	
			String name = req.getParameter("dataSetName");
			String projectId = req.getParameter("projectId");
			String path = "";
			String orignalFileName = file.getName();
			if (!file.isEmpty()) {
				path = CommonUtility.fileUpload(orignalFileName, file);	
			} else {
				//return "You failed to upload " + name + " because the file was empty.";
				System.out.println("File is empty");
			}
			  
			APIDataset dataset = new APIDataset();
			dataset.setName(name);
			dataset.setFileName(path);
			dataset.setStatus("Pending");
			dataset.setUserId((Integer) session.getAttribute("userId"));
			dataset.setProjectId(Integer.parseInt(projectId));
			dataset.setApiType(req.getParameter("apiType"));
			
			HashMap<String,String> start = CommonUtility.checkUniqueDomains(path);
			
			if(start.size() < 50001) {
			
			int datasetId = aPIDatasetDao.insertAPIDataset(dataset);
		     
		        
		        AddressAttribute addressDetails = new AddressAttribute();
		        addressDetails.setAddress(addressAddress);
		        addressDetails.setPhone(addressPhone);
		        addressDetails.setEmail(addressEmail);
		        addressDetails.setForm(addressForm);
		        String[] urls = start.keySet().toArray(new String[start.size()]);
		        Attribute attribute = new Attribute();
		        attribute.setAddress(addressDetails);
		        attribute.setKey_contact(keyContacts);
		        attribute.setMarket_variables(marketVariables);
		        attribute.setSku(productCount);
		        attribute.setTech_install(techInstall);
		        attribute.setUrl_status(urlStatus);
		        attribute.setSocial_data(0);
		        
		        Request request = new Request();
		         request.setMaxdepth(req.getParameter("maxDepth"));
		         request.setDomain_max_pages(25);
				 request.setAppid("WEB"+datasetId);
				 request.setCrawlid(req.getParameter("apiType")+"_"+datasetId+"");
				 request.setUrls(urls);
				 request.setAttrs(attribute);
				 request.setSpiderid(req.getParameter("apiType"));
				// request.setAddressAttribute(hashMap);
		        
				 
				 ObjectMapper mapper = new ObjectMapper();
				 String payload;
				try {
					payload = mapper.writeValueAsString(request);
					CommonUtility.sendPostRequest(CommonUtility.ENRICH_URL, payload);
					System.out.println(6);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return "redirect:manageSmartystreets";
			}else {
				redirectAttributes.addFlashAttribute("uploadLimit", "Maximum 50000 domains allowed.");
				return "redirect:manageSmartystreets";
			}
		}
	 
	 
	 @RequestMapping(value = "updateAPIStatus")
	 @ResponseBody public void updateAPIStatus(String dataSetId,String status,String action) {
		 
		 System.out.println("Dataset ID "+dataSetId);
		 
		 aPIDatasetDao.updateAPIStatus(dataSetId, "Done");
		 
	 }
	
}
