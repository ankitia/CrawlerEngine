package com.ia.web.SolarExample;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.web.multipart.MultipartFile;

import com.ia.web.Dao.DatasetDao;
import com.ia.web.Dao.MapDao;
import com.ia.web.Modal.AddressComponents;
import com.ia.web.Modal.AddressDataMongo;
import com.ia.web.Modal.AddressDetails;

public class CommonUtility 
{ 
	public static int NO_OFF_RECORDS = 10;
	public static  String REQUEST_URL = "";
	public static String ENRICH_URL = "http://18.204.200.4:5343/feeds";
	public static double TOTAL_RECEORD = 0;
	public static int LOGIN_USER_ID = 0;
	public static int USER_ROLE_ID = 0;
	
	
	/*public static String Host = "localhost";
	public static String Port = "27017";
	public static String fileName = "/home/jaynil/Desktop/col.csv";
	public static String command = "mongoexport --host "+Host+" --db "+db+" --collection "+col+" --csv --out /home/jaynil/Desktop/text.csv --fields tld,data.0.status";*/
	public static String db = "228";
	public static String col = "StatusData";
	//public static String Host = "192.168.1.178"; 
	public static String Host = "52.70.46.225";
	public static String Port = "50340";
	//public static String fileName = "/home/jaynil/Desktop/text.csv";
	public static String fileName = "/opt/wce/text.csv";
	public static String command = "mongoexport --host "+CommonUtility.Host+" --port "+ CommonUtility.Port +" --db "+CommonUtility.db+" --collection "+CommonUtility.col+" --csv --out "+CommonUtility.fileName+" --fields tld,data.0.status";
  
	
	 public static double getCsvCount(String filename) throws IOException {
		    InputStream is = new BufferedInputStream(new FileInputStream(filename));
		    try {
		    byte[] c = new byte[1024];
		    double count = 0;
		    int readChars = 0;
		    boolean empty = true;
		    while ((readChars = is.read(c)) != -1) {
		        empty = false;
		        for (int i = 0; i < readChars; ++i) {
		            if (c[i] == '\n') {
		                ++count;
		            }
		        }
		    }
		    return (count == 0 && !empty) ? 1 : count;
		    } finally {
		    is.close();
		   }
		}
	 
	 public static boolean checkUniqueDomain(String csvFile) {
	        String line = "";
	        String cvsSplitBy = ",";
	        int count = 0;
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        	HashMap<String,String> hashMap = new HashMap<>();
	            while ((line = br.readLine()) != null) {
	                // use comma as separator
	                String[] country = line.split(cvsSplitBy);
	                hashMap.put(country[0], country[0]);
	                count++;
	            }
	            if(count==hashMap.size()) {
	            	TOTAL_RECEORD = count;
	            	return true;
	            }
	           // System.out.println("Total =---->"+count +"---"+hashMap.size());
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 return false;
		 
	 }
	 
	 public static HashMap<String,String> checkUniqueDomains(String csvFile) {
	        String line = "";
	        String cvsSplitBy = ",";
	        HashMap<String,String> hashMap = new HashMap<>();
	        int count = 0;
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	            while ((line = br.readLine()) != null) {
	                // use comma as separator
	                String[] country = line.split(cvsSplitBy);
	                hashMap.put( country[0].replaceAll("\"https", "https"), country[0].replaceAll("\"https", "https"));
	                count++;
	            }
	            if(count==hashMap.size()) {
	            	TOTAL_RECEORD = count;
	            	return hashMap;
	            }
	           // System.out.println("Total =---->"+count +"---"+hashMap.size());
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 return hashMap;
		 
	 }
	 
	 public static String sendPostRequest(String requestUrl, String payload) {
		    try {
		    	
		    	System.out.println("requestUrl--------->"+requestUrl);
		    	System.out.println("payload--------->"+payload);
		    	
		        URL url = new URL(requestUrl);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		        connection.setDoInput(true);
		        connection.setDoOutput(true);
		        connection.setRequestMethod("POST");
		        connection.setRequestProperty("Accept", "application/json");
		        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		        writer.write(payload);
		        writer.close();
		        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		        StringBuffer jsonString = new StringBuffer();
		        String line;
		        while ((line = br.readLine()) != null) {
		                jsonString.append(line);
		        }
		        br.close();
		        connection.disconnect();
		        return jsonString.toString();
		    } catch (Exception e) {
		    		System.out.println(e.getMessage());
		            //throw new RuntimeException(e.getMessage());
		    }
			return payload;

		}
	 
	 public static String fileUpload(String orignalFileName,MultipartFile file) {
		 
		 String path = "";
		 try {
			 	
				orignalFileName = file.getOriginalFilename();
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				path = rootPath + File.separator + "tmpFiles";
				File dir = new File(path);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				path = dir.getAbsolutePath() + File.separator + orignalFileName;
				File serverFile = new File(dir.getAbsolutePath() + File.separator + orignalFileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				//System.out.println(ext+"---path-->"+path+"----"+orignalFileName+"--------------"+dir.getAbsolutePath());
				System.out.println("Server File Location="+ serverFile.getAbsolutePath());
				//return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				//return "You failed to upload " + name + " => " + e.getMessage();
				System.out.println("Error ---"+e);
			}
		 
		 return path;
	 }
	 
	 
	 public static void mapRequest(String address,int masterAddressId,MapDao mapDao) {
		 //String url = "https://maps.googleapis.com/maps/api/geocode/json?address=1512+S+ANGELINE+ST+98108&key=AIzaSyARw0011MInq-jIA8A9ZU8aDCn0wy7M-80";
		 
		 //String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+address+"&key=AIzaSyARw0011MInq-jIA8A9ZU8aDCn0wy7M-80";
		 String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+address+"&key=AIzaSyAUNTGxJ1ZMtY9GrOwEWwTsQ5sLysT8Xs0";
			//System.out.println(sendGetRequest(url));
			String str = sendGetRequest(url);
			try {
			     Object obj=JSONValue.parse(str);  
			     JSONObject jsonObject = (JSONObject) obj;  
			     JSONArray array = (JSONArray) jsonObject.get("results");
			     
			     System.out.println("Size ::"+ array.toString());
			     for (int i = 0; i < array.size(); i++) {
			    	 JSONObject parent =  (JSONObject) array.get(i);
			    	 JSONArray addressComponents = (JSONArray) parent.get("address_components");

			    	 AddressDetails addressDetails = new AddressDetails();
			    	 addressDetails.setFormatted_address(parent.get("formatted_address")+"");
			    	 
			    	 JSONObject geometry = (JSONObject) parent.get("geometry");
			    	 
			    	 System.out.println(1);
			    	 
			    	 /* Bounds start */
			    	 JSONObject bounds = (JSONObject) geometry.get("bounds");
			    	 
			    	 JSONObject southwest = null;
			    	 JSONObject northeast = null;
			    	 
			    	 try {
			    		 if(bounds.get("southwest")!=null) {
				    		 System.out.println(5);	 
				    		 southwest = (JSONObject) bounds.get("southwest");
				    		 System.out.println(4);	 
				    		 if(southwest.get("lat")!=null)
				    			 addressDetails.setGeometry_bounds_southwest_lat(southwest.get("lat")+"");
				    		 if(southwest.get("lng")!=null)
				    			 addressDetails.setGeometry_bounds_southwest_lng(southwest.get("lng")+"");	 
				    	 }
			    		 

				    	 if(bounds.get("northeast")!=null) {
				    		 northeast = (JSONObject) bounds.get("northeast");
				    		 
				    		 if(northeast.get("lat")!=null)
				    			 addressDetails.setGeometry_bounds_northeast_lat(northeast.get("lat")+"");
				    		 if(northeast.get("lng")!=null)
					    	 	addressDetails.setGeometry_bounds_northeast_lng(northeast.get("lng")+"");
				    	 }
			    		 
			    	 }catch (Exception e) {
						// TODO: handle exception
					}
			    	 
			    	 
			    	 
			    	 
			    	 System.out.println(3); 
			    	 
			    	 /* Bounds end */
			    	 
			    	 
			    	 JSONObject location = (JSONObject) geometry.get("location");
			    	 addressDetails.setGeometry_location_lat(location.get("lat")+"");
			    	 addressDetails.setGeometry_location_lng(location.get("lng")+"");
			    	 
			    	 addressDetails.setGeometry_location_type(geometry.get("location_type")+"");
			    	 
			    	 
			    	 JSONObject viewport = (JSONObject) geometry.get("viewport");
			    	 
			    	 southwest = (JSONObject) viewport.get("southwest");
			    	 addressDetails.setGeometry_viewport_southwest_lat(southwest.get("lat")+"");
			    	 addressDetails.setGeometry_viewport_southwest_lng(southwest.get("lng")+"");
			    	 
			    	 southwest = (JSONObject) viewport.get("northeast");
			    	 addressDetails.setGeometry_viewport_northeast_lat(southwest.get("lat")+"");
			    	 addressDetails.setGeometry_viewport_northeast_lng(southwest.get("lng")+"");
			    	 
			    	 
			    	 addressDetails.setPlace_id(parent.get("place_id")+"");
			    	 addressDetails.setMain_address(address);
			    	 JSONArray types = (JSONArray) parent.get("types");
			    	 for (int j = 0; j < types.size(); j++) {
			    		 addressDetails.setTypes(types.get(j)+"");
					}
			    	 
			    	 addressDetails.setMaster_address_id(masterAddressId);
			    	 
			    	 int addressId = mapDao.insertAddressDetails(addressDetails);
			    	 
			    	 System.out.println(parent.get("place_id")+"");
			    	 
			    	 for (int j = 0; j < addressComponents.size(); j++) {
			    		 JSONObject addressComponentsObje = (JSONObject) addressComponents.get(j);
			    		 AddressComponents addressComponents2 = new AddressComponents();
			    		 addressComponents2.setAddressDetailId(addressId);
			    		 addressComponents2.setLong_name(addressComponentsObje.get("long_name")+"");
			    		 addressComponents2.setShort_name(addressComponentsObje.get("short_name")+"");
			    		 addressComponents2.setTypes(addressComponentsObje.get("types")+"");
			    		 mapDao.insertAddressComponents(addressComponents2);
					}
			    	 
				}
			      
			     /*String name = (String) jsonObject.get("name");  
			     double salary = (Double) jsonObject.get("salary"); */ 
			     
			}catch (Exception err){
			     System.out.println("Error"+ err.toString());
			}
	 }
	 
	 public static String sendGetRequest(String requestUrl) {
		    try {
		        URL url = new URL(requestUrl);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		        connection.setDoInput(true);
		        connection.setDoOutput(true);
		        connection.setRequestMethod("GET");
		        connection.setRequestProperty("Accept", "application/json");
		        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		        //writer.write(payload);
		        writer.close();
		        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		        StringBuffer jsonString = new StringBuffer();
		        String line;
		        while ((line = br.readLine()) != null) {
		                jsonString.append(line);
		        }
		        br.close();
		        connection.disconnect();
		        return jsonString.toString();
		    } catch (Exception e) {
		            throw new RuntimeException(e.getMessage());
		    } 

		}
	 
	 public static void downloadFile(HttpServletResponse response,HttpServletRequest request,String query,DatasetDao datasetDao) {
		 
		 try {
					 Process process = Runtime.getRuntime().exec(query);
			         
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
				 
				 try {
				 /*System.out.println("dataSetID--->"+request.getParameter("dataSetID"));*/
				 //response.setHeader("Content-disposition","attachment; filename="+request.getParameter("dataSetID")+".csv"); // Used to name the download file and its format
			  //File my_file = new File(CommonUtility.fileName); // We are downloading .txt file, in the format of doc with name check - check.doc
				 boolean my_file = new File("/opt/wce/"+request.getParameter("dataSetID")+"/address_data_"+request.getParameter("dataSetID")+".csv").mkdir();
				 
			 /* OutputStream out = response.getOutputStream();
			  FileInputStream in = new FileInputStream(my_file);
			  byte[] buffer = new byte[4096];
			  int length;
			  while ((length = in.read(buffer)) > 0){
			     out.write(buffer, 0, length);
			  }
			  in.close();
			  out.flush();*/
			  
			  
			  String csvFile = "/opt/wce/"+request.getParameter("dataSetID")+"/address_data_"+request.getParameter("dataSetID")+".csv";
		        String line = "";
		        String cvsSplitBy = ",";
		        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
		        	int i = 0;
		            while ((line = br.readLine()) != null) {
		                // use comma as separator
		                String[] country = line.split(cvsSplitBy);
		                System.out.println("Country [code= " + country[1] + " , name=" + country[2] + "]");
		                AddressDataMongo dataMongo =  new AddressDataMongo();
		                int arraySize = country.length;
		                if(i>0) {
			                if(arraySize>0 && country[0]!=null)
			                	dataMongo.setTld(country[0]);
			                if(arraySize>1 && country[1]!=null)
			                	dataMongo.setUrl(country[1]);
			                if(arraySize>2 && country[2]!=null)
			                	dataMongo.setAddressline(country[2]);
			                if(arraySize>3 && country[3]!=null)
			                	dataMongo.setCity(country[3]);
			                if(arraySize>4 && country[4]!=null)
			                	dataMongo.setState_name(country[4]);
			                if(arraySize>5 && country[5]!=null)
			                	dataMongo.setState_code(country[5]);
			                if(arraySize>6 && country[6]!=null)
			                	dataMongo.setCountry(country[6]);
			                if(arraySize>7 && country[7]!=null)
			                	dataMongo.setZip_code(country[7]);
			                if(arraySize>8 && country[8]!=null)
			                	dataMongo.setTelephone_number(country[8]);
			                if(arraySize>9 && country[9]!=null)
			                	dataMongo.setSource_url(country[9]);
			                	dataMongo.setDataSetId(Integer.parseInt(request.getParameter("dataSetID")));
		                
			                	datasetDao.insertDatasetMongo(dataMongo);
		                }
		                i++;
		            }

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	 } catch (Exception e) {
         e.printStackTrace();
     }
	 
	 }
	 
	 public static void exportMongoOutput(String action,String dataSetID,String path) {
		 String downloadLocation = "/opt/wce/"+dataSetID+"_"+path+"/"+action+"_"+dataSetID+".csv";
		 System.out.println(downloadLocation);
		 String command = "mongoexport --host "+CommonUtility.Host+" --db "+CommonUtility.db+" --collection "+CommonUtility.col+" --csv --out "+CommonUtility.fileName+" --fields tld,data.0.status";
		 
		 
		 if(action.equalsIgnoreCase("MarketVariables")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection CompanyPersona --csv --out "+downloadLocation+" --fields tld,b2b_b2c_prediction,b2b_b2c_category,b2b_b2c_score,product_service_prediction,product_service_category,product_service_score,Manufacturing_retail_prediction,Manufacturing_retail_category,Manufacturing_retail_score";
		 }else if(action.equalsIgnoreCase("AddressEmail")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection EmailData --csv --out "+downloadLocation+" --fields tld,url,email";
		 }else if(action.equalsIgnoreCase("AddressPhone")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection TelephoneData --csv --out "+downloadLocation+" --fields tld,url,telephone";
		 }else if(action.equalsIgnoreCase("AddressForm")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection FormPresentData --csv --out "+downloadLocation+" --fields tld,url,form_present,contact_form_fields_text,contact_form_placeholder_text,form_legth,required_form_fields,contact_us_keyword_present,field_max_length,page_info,contact_us_button_count";
		 }else if(action.equalsIgnoreCase("AddressAddress")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection AddressData --csv --out "+downloadLocation+" --fields tld,url,addressline,city,state_name,state_code,country,zip_code,telephone_number,source_url";
		 }else if(action.equalsIgnoreCase("KeyContacts")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection NameDesgData --csv --out "+downloadLocation+" --fields tld,url,firstname,lastname,name,designation,email_id,profile_link";
		 }else if(action.equalsIgnoreCase("TechInstall")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection TechInstallData --csv --out "+downloadLocation+" --fields tld,url,is_cart_links,is_payment_links,is_subscription_links,is_giftcard_links,is_donation_links,cart_link_count,payment_link_count,subscription_link_count,giftcard_link_count,donation_link_count,cart_link,payment_links,subscription_links,giftcard_links,donation_links,identified_carts";
		 }else if(action.equalsIgnoreCase("UrlStatus")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+CommonUtility.db+" --collection url_status --csv --out "+downloadLocation+" --fields tld,url,domain,status_code,request_url,response_url,is_inactive,is_redirect";
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
	 
	 public static void exportMongoOutputUSPSAPI(String action,String dataSetID,String path,String databaseName) {
		 String downloadLocation = "/opt/wce/"+dataSetID+"_"+path+"/"+action+"_"+dataSetID+".csv";
		 System.out.println(downloadLocation);
		 String command = "mongoexport --host "+CommonUtility.Host+" --db "+CommonUtility.db+" --collection "+CommonUtility.col+" --csv --out "+CommonUtility.fileName+" --fields tld,data.0.status";
		 
		 
		if(action.equalsIgnoreCase("API")) {
			 command = "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+databaseName+" --collection raw_data --csv --out "+downloadLocation+" --fields response_url,input_address,input_index,candidate_index,delivery_line_1,last_line,delivery_point_barcode,primary_number,street_name,street_suffix,city_name,default_city_name,state_abbreviation,zipcode,plus4_code,delivery_point,delivery_point_check_digit,record_type,zip_type,county_fips,county_name,carrier_route,congressional_district,building_default_indicator,rdi,elot_sequence,elot_sort,latitude,longitude,precision,time_zone,utc_offset,dst,dpv_match_code,dpv_footnotes,dpv_cmra,dpv_vacant,active,footnotes";
		 }else if(action.equalsIgnoreCase("smartystreet_spider")){
			 command =  "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+databaseName+" --collection raw_data --csv --out "+downloadLocation+" --fields response_url,input_index,candidate_index,delivery_line_1,last_line,delivery_point_barcode,primary_number,street_name,street_suffix,city_name,default_city_name,state_abbreviation,zipcode,plus4_code,delivery_point,delivery_point_check_digit,record_type,zip_type,county_fips,county_name,carrier_route,congressional_district,building_default_indicator,rdi,elot_sequence,elot_sort,latitude,longitude,precision,time_zone,utc_offset,dst,dpv_match_code,dpv_footnotes,dpv_cmra,dpv_vacant,active,footnotes";
		 }else if(action.equalsIgnoreCase("usps_spider")) {
			 command =  "mongoexport --host "+CommonUtility.Host+" --port 50340 --db "+databaseName+" --collection raw_data --csv --out "+downloadLocation+" --fields response_url,url,input_address,input_city,input_state,input_zip4,input_zip5,address2,city,zip4,zip5,delivery_point,return_text,carrier_route,footnotes,dpv_confirmation,dpv_cmra,dpv_footnotes,business,central_delivery_point,vacant,error_number,error_source";
		 }
		 System.out.println("Command :::"+command);
		 
		 try {
	            System.out.println("command API-->>"+command);
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
	 
	 public static void downloadZip(HttpServletResponse response,String my_file,String datasetId) {
		
		 response.setHeader("Content-disposition","attachment; filename="+datasetId+".zip"); // Used to name the download file and its format
		 OutputStream out;
		try {
			out = response.getOutputStream();
		  FileInputStream in = new FileInputStream(my_file);
		  byte[] buffer = new byte[4096];
		  int length;
		  while ((length = in.read(buffer)) > 0){
		     out.write(buffer, 0, length);
		  }
		  in.close();
		  out.flush();
		  
		}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
	 }
	 
} 
