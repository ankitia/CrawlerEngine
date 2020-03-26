package com.ia.web.SolarExample;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ia.web.Dao.DatasetDao;
import com.ia.web.Dao.MasterUrlDao;
import com.ia.web.Dao.ProjectDao;
import com.ia.web.Modal.AddressAttribute;
import com.ia.web.Modal.Attribute;
import com.ia.web.Modal.Dataset;
import com.ia.web.Modal.Request;

@Controller
public class DatasetController {

	@Autowired
	DatasetDao datasetDao;
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	MasterUrlDao masterUrlDao;
	
	@RequestMapping(value = "/dataSet", method = RequestMethod.GET )
	public String scrap(HttpServletRequest request,Model model,HttpSession session) throws SQLException {
		if(context.getAttribute("userDetails")==null) {
			return "redirect:/";
		}
		model.addAttribute("projectList", projectDao.getProjects("all"));
		return "data_set";
	}
	
	@RequestMapping(value = "/manageDataset", method = RequestMethod.GET )
	public String manage_dataset(HttpServletRequest request,Model model,HttpSession session) throws SQLException {
		if(context.getAttribute("userDetails")==null) {
			return "redirect:/";
		}
		
		if(request.getParameter("uploadLimit")!=null) {
			model.addAttribute("uploadLimit",request.getParameter("uploadLimit"));
		}else {
			System.out.println("uploadLimit---> not av");
		}
		
		
		System.out.println("dataSet");
		if(session.getAttribute("userId")!=null) {
			model.addAttribute("manageDataset", datasetDao.getDatasetList((Integer) session.getAttribute("userId")));	
		}else {
			return "redirect:/";
		}
		return "manage_dataset";
	}
	
	 /*Data set*/
	 @RequestMapping(value = "/insertDataset"  , method = RequestMethod.POST)
		public String insertDataset(@RequestParam("exampleInputFile") MultipartFile file,HttpServletRequest req,HttpSession session,RedirectAttributes redirectAttributes) {
			
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
			  
			Dataset dataset = new Dataset();
			dataset.setDataSetName(name);
			dataset.setFileName(path);
			dataset.setProcessName(name);
			dataset.setStatus("Pending");
			dataset.setUserId((Integer) session.getAttribute("userId"));
			dataset.setProjectId(Integer.parseInt(projectId));
			dataset.setMarketVariables(marketVariables+"");
			dataset.setAddress(address+"");
			dataset.setProductCount(productCount+"");
			dataset.setTechInstall(techInstall+"");
			dataset.setKeyContacts(keyContacts+"");
			dataset.setRulesAll(rulesAll);
			dataset.setRulesB2B(rulesB2B);
			dataset.setRulesB2c(rulesB2c);
			dataset.setRulesManufacturer(rulesManufacturer);
			dataset.setRulesProduct(rulesProduct);
			dataset.setRulesRetail(rulesRetail);
			dataset.setRulesServices(rulesServices);
			dataset.setRulesProductCount(productCount);
			dataset.setAddressAddress(addressAddress);
			dataset.setAddressEmail(addressEmail);
			dataset.setAddressForm(addressForm);
			dataset.setAddressPhone(addressPhone);
			dataset.setUrlStatus(urlStatus);
			dataset.setMaxDepth(Integer.parseInt(req.getParameter("maxDepth")));
			
			//Integer.parseInt(req.getParameter("productCount"))
			HashMap<String,String> start = CommonUtility.checkUniqueDomains(path);
			
			if(start.size() < 20001) {
				int datasetId = datasetDao.insertDataset(dataset);
		       /* for(Map.Entry entry: start.entrySet()){
		        	MasterUrl masterUrl = new MasterUrl();
					masterUrl.setDatasetId(datasetId);
					masterUrl.setRequestUrl(entry.getKey().toString());
					//masterUrlDao.insertMasterUrl(masterUrl);
		        }*/
			
			System.out.println("Total count::"+start.size());
		        
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
		        
		        
		        /*HashMap<String,Object> hashMap = new HashMap<>();
		        hashMap.put("address", addressDetails);*/
		        
		        Request request = new Request();
		         request.setMaxdepth(req.getParameter("maxDepth"));
		         request.setDomain_max_pages(25);
				 request.setAppid("WEB"+datasetId);
				 request.setCrawlid(datasetId+"");
				 request.setUrls(urls);
				 request.setAttrs(attribute);
				// request.setAddressAttribute(hashMap);
		        
				 ObjectMapper mapper = new ObjectMapper();
				 String payload;
				try {
					payload = mapper.writeValueAsString(request);
					CommonUtility.sendPostRequest(CommonUtility.ENRICH_URL, payload);
					
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return "redirect:manageDataset";
			}else {
				redirectAttributes.addFlashAttribute("uploadLimit", "Maximum 20000 domains allowed.");
				return "redirect:manageDataset";
			}
			
		}
	 
	 @RequestMapping(value="/updateDataSetStatus")
	 @ResponseBody public String updateDataSetStatus(HttpServletRequest request,HttpSession session) throws NumberFormatException, SQLException{
		 return datasetDao.updateStatus(Integer.parseInt(request.getParameter("dataSetId")),request.getParameter("status"),request.getParameter("action"))+"";
		 
	 }
	 
	 
	 
	 /*Data set end*/
	 
	 @RequestMapping(value="exportUrlStatus")
	 @ResponseBody public void exportUrlStatus(HttpServletResponse response,HttpServletRequest request) {
		 	CommonUtility.db = request.getParameter("dataSetID")+"";
		 	String dataSetId = request.getParameter("dataSetID")+"";
		 	String action = request.getParameter("action")+"";
		 	System.out.println("action-------->"+action);
		 	Dataset dataset = datasetDao.getDataSetDetails(Integer.parseInt(dataSetId));
		 	
	        if(!new File("/opt/wce/"+dataSetId+"_"+action).exists()) {
	        	if(dataset!=null) {
	        		
	        		if(action.equalsIgnoreCase("Common")) {
	        			if(Integer.parseInt(dataset.getMarketVariables()) > 0)
				 			CommonUtility.exportMongoOutput("MarketVariables",dataSetId,action);
				 		if(dataset.getAddressEmail() > 0)
				 			CommonUtility.exportMongoOutput("AddressEmail",dataSetId,action);
				 		if(dataset.getAddressPhone() > 0)
				 			CommonUtility.exportMongoOutput("AddressPhone",dataSetId,action);
				 		if(dataset.getAddressForm() > 0)
				 			CommonUtility.exportMongoOutput("AddressForm",dataSetId,action);
				 		if(dataset.getAddressAddress() > 0)
				 			CommonUtility.exportMongoOutput("AddressAddress",dataSetId,action);
				 		if(Integer.parseInt(dataset.getKeyContacts()) > 0)
				 			CommonUtility.exportMongoOutput("KeyContacts",dataSetId,action);
				 		if(Integer.parseInt(dataset.getTechInstall()) > 0)
				 			CommonUtility.exportMongoOutput("TechInstall",dataSetId,action);
	        		}else if(action.equalsIgnoreCase("URLStatus")) {
	        			if(dataset.getUrlStatus() > 0)
				 			CommonUtility.exportMongoOutput("UrlStatus",dataSetId,action);
	        		} 
			 	}
	        }
		 	
		 	ZipUtils appZip = new ZipUtils();
	        appZip.generateFileList(new File("/opt/wce/"+dataSetId+"_"+action));
	        appZip.zipIt("/opt/wce/"+dataSetId+"_"+action+".zip");
		 	
		 	CommonUtility.downloadZip(response, "/opt/wce/"+dataSetId+"_"+action+".zip",dataSetId	);
		 	//CommonUtility.downloadZip(response, "/opt/wce/392.zip");
		 	
	           /* String command = "mongoexport --host "+CommonUtility.Host+" --port "+ CommonUtility.Port +" --db "+CommonUtility.db+" --collection "+CommonUtility.col+" --csv --out "+CommonUtility.fileName+" --fields tld,url,domain,status_code,request_url,response_url,is_inactive,is_redirect";
	            String address = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection AddressData --csv --out /opt/wce/"+request.getParameter("dataSetID")+"/address_data_"+request.getParameter("dataSetID")+".csv --fields tld,url,addressline,city,state_name,state_code,country,zip_code,telephone_number,source_url";
	            String  companypersona = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection CompanyPersona --csv --out mongo_output/3m_url_check/temp/company_details.csv --fields tld,b2b_b2c_prediction,b2b_b2c_category,b2b_b2c_score,product_service_prediction,product_service_category,product_service_score,Manufacturing_retail_prediction,Manufacturing_retail_category,Manufacturing_retail_score";
	            String  emailData = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection EmailData --csv --out mongo_output/3m_url_check/temp/email_data.csv --fields tld,url,email";
	            String formPresent = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection FormPresentData --csv --out mongo_output/3m_url_check/temp/formdata.csv --fields tld,url,form_present,contact_form_fields_text,contact_form_placeholder_text,form_legth,required_form_fields,contact_us_keyword_present,field_max_length,page_info,contact_us_button_count";
	            String nameDesgData = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection NameDesgData --csv --out mongo_output/3m_url_check/temp/name_desg_data.csv --fields tld,url,firstname,lastname,name,designation,email_id,profile_link";
	            String urlStatus = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection StatusData --csv --out mongo_output/3m_url_check/temp/url_active_data.csv --fields tld,url,domain,status_code,request_url,response_url,is_inactive,is_redirect";
	            String techInstall = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection TechInstallData --csv --out mongo_output/3m_url_check/temp/tech_install_data.csv --fields tld,url,is_cart_links,is_payment_links,is_subscription_links,is_giftcard_links,is_donation_links,cart_link_count,payment_link_count,subscription_link_count,giftcard_link_count,donation_link_count,cart_link,payment_links,subscription_links,giftcard_links,donation_links,identified_carts";
	            String telephone = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection TelephoneData --csv --out mongo_output/3m_url_check/temp/telephone_data.csv --fields tld,url,telephone";
	            String urlStatusData  = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection url_status --csv --out mongo_output/3m_url_check/temp/url_status_all.csv --fields tld,url,status,response_url";
	            System.out.println(address);
	            
	            CommonUtility.downloadFile(response, request, address,datasetDao);
	            System.out.println("This is call");*/
	            //CommonUtility.downloadFile(response, request, command);
	            
	         //   System.out.println(command);
	           
	 }
	 
	 
	 @RequestMapping(value="exportUSPSAPI")
	 @ResponseBody public void exportUSPSAPI(HttpServletResponse response,HttpServletRequest request) {
		 	 
		 	String dataSetId = request.getParameter("dataSetID")+"";
		 	String action = request.getParameter("action")+"";
		 	String databaseName = request.getParameter("databaseName")+"";
		 	System.out.println("action-------->"+action+"_"+dataSetId);
		 	
		 	
	        if(!new File("/opt/wce/"+dataSetId+"_"+action).exists()) {
	        		 if(action.equals("API")) {
	        			CommonUtility.exportMongoOutputUSPSAPI("API",dataSetId,action,databaseName);
			 	}else if(action.equalsIgnoreCase("smartystreet_spider")) {
			 		CommonUtility.exportMongoOutputUSPSAPI("smartystreet_spider",dataSetId,action,action+"_"+dataSetId);
			 	}else if(action.equalsIgnoreCase("usps_spider")) {
			 		CommonUtility.exportMongoOutputUSPSAPI("usps_spider",dataSetId,action,action+"_"+dataSetId);
			 	}
	        }
		 	ZipUtils appZip = new ZipUtils();
	        appZip.generateFileList(new File("/opt/wce/"+dataSetId+"_"+action));
	        appZip.zipIt("/opt/wce/"+dataSetId+"_"+action+".zip");
		 	
		 	CommonUtility.downloadZip(response, "/opt/wce/"+dataSetId+"_"+action+".zip",dataSetId);
		 	//CommonUtility.downloadZip(response, "/opt/wce/392.zip");
		 	
	           /* String command = "mongoexport --host "+CommonUtility.Host+" --port "+ CommonUtility.Port +" --db "+CommonUtility.db+" --collection "+CommonUtility.col+" --csv --out "+CommonUtility.fileName+" --fields tld,url,domain,status_code,request_url,response_url,is_inactive,is_redirect";
	            String address = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection AddressData --csv --out /opt/wce/"+request.getParameter("dataSetID")+"/address_data_"+request.getParameter("dataSetID")+".csv --fields tld,url,addressline,city,state_name,state_code,country,zip_code,telephone_number,source_url";
	            String  companypersona = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection CompanyPersona --csv --out mongo_output/3m_url_check/temp/company_details.csv --fields tld,b2b_b2c_prediction,b2b_b2c_category,b2b_b2c_score,product_service_prediction,product_service_category,product_service_score,Manufacturing_retail_prediction,Manufacturing_retail_category,Manufacturing_retail_score";
	            String  emailData = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection EmailData --csv --out mongo_output/3m_url_check/temp/email_data.csv --fields tld,url,email";
	            String formPresent = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection FormPresentData --csv --out mongo_output/3m_url_check/temp/formdata.csv --fields tld,url,form_present,contact_form_fields_text,contact_form_placeholder_text,form_legth,required_form_fields,contact_us_keyword_present,field_max_length,page_info,contact_us_button_count";
	            String nameDesgData = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection NameDesgData --csv --out mongo_output/3m_url_check/temp/name_desg_data.csv --fields tld,url,firstname,lastname,name,designation,email_id,profile_link";
	            String urlStatus = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection StatusData --csv --out mongo_output/3m_url_check/temp/url_active_data.csv --fields tld,url,domain,status_code,request_url,response_url,is_inactive,is_redirect";
	            String techInstall = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection TechInstallData --csv --out mongo_output/3m_url_check/temp/tech_install_data.csv --fields tld,url,is_cart_links,is_payment_links,is_subscription_links,is_giftcard_links,is_donation_links,cart_link_count,payment_link_count,subscription_link_count,giftcard_link_count,donation_link_count,cart_link,payment_links,subscription_links,giftcard_links,donation_links,identified_carts";
	            String telephone = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection TelephoneData --csv --out mongo_output/3m_url_check/temp/telephone_data.csv --fields tld,url,telephone";
	            String urlStatusData  = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection url_status --csv --out mongo_output/3m_url_check/temp/url_status_all.csv --fields tld,url,status,response_url";
	            System.out.println(address);
	            
	            CommonUtility.downloadFile(response, request, address,datasetDao);
	            System.out.println("This is call");*/
	            //CommonUtility.downloadFile(response, request, command); 
	            
	         //   System.out.println(command);
	           
	 }
	 
	 
	 
	 @RequestMapping(value="exportMongoOutput")
	 @ResponseBody public void exportMongoOutput(HttpServletResponse response,HttpServletRequest request) {
		 
		 String action = request.getParameter("action");
		 CommonUtility.db = request.getParameter("dataSetID");
		 
		 String downloadLocation = "/opt/wce/"+request.getParameter("dataSetID")+"/"+request.getParameter("action")+"_"+request.getParameter("dataSetID")+".csv";
		 System.out.println(downloadLocation);
		 //String address = "mongoexport --host 18.204.200.4 --port 50340  --db "+CommonUtility.db+" --collection AddressData --csv --out "+downloadLocation+" --fields tld,url,addressline,city,state_name,state_code,country,zip_code,telephone_number,source_url";
		 String command = "mongoexport --host "+CommonUtility.Host+" --db "+CommonUtility.db+" --collection "+CommonUtility.col+" --csv --out "+CommonUtility.fileName+" --fields tld,data.0.status";
		 
		 if(action.equalsIgnoreCase("emailData")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection EmailData --csv --out "+downloadLocation+" --fields tld,url,email";
		 }else if(action.equalsIgnoreCase("telephoneData")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection TelephoneData --csv --out "+downloadLocation+" --fields tld,url,telephone";
		 }else if(action.equalsIgnoreCase("formData")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection FormPresentData --csv --out "+downloadLocation+" --fields tld,url,form_present,contact_form_fields_text,contact_form_placeholder_text,form_length,required_form_fields"; 
		 }else if(action.equalsIgnoreCase("addressData")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340  --db "+CommonUtility.db+" --collection AddressData --csv --out "+downloadLocation+" --fields tld,url,addressline,city,state_name,state_code,country,zip_code,telephone_number,source_url";
		 }else if(action.equalsIgnoreCase("desgData")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection NameDesgData --csv --out "+downloadLocation+" --fields tld,url,firstname,lastname,name,designation,email_id,profile_link";
		 }
		 try {
	            System.out.println("command-->>"+command);
	            Process process = Runtime.getRuntime().exec(command);
	            int waitFor = process.waitFor();
	            System.out.println("waitFor:: " + waitFor);
	            BufferedReader success = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

	            String s = "";
	            while ((s = success.readLine()) != null) {
	                System.out.println(s);
	            }

	            while ((s = error.readLine()) != null) {
	                System.out.println("Std ERROR : " + s);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	 
	 @RequestMapping(value="getDatasetProjectList")
	 @ResponseBody public ArrayList<Dataset> getDatasetProjectList(HttpServletRequest request) {
		return datasetDao.getDatasetProjectList(Integer.parseInt(request.getParameter("projectId")));
	 }
}
