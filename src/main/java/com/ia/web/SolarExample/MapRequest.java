package com.ia.web.SolarExample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.ia.web.Modal.AddressComponents;
import com.ia.web.Modal.AddressDetails;

public class MapRequest {

	
	
	public static void main(String[] args) throws Exception {

		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=1512+S+ANGELINE+ST+98108&key=AIzaSyARw0011MInq-jIA8A9ZU8aDCn0wy7M-80";
		//System.out.println(sendGetRequest(url));
		String str = sendGetRequest(url);
		try {
		     Object obj=JSONValue.parse(str);  
		     JSONObject jsonObject = (JSONObject) obj;  
		     JSONArray array = (JSONArray) jsonObject.get("results");
		     
		     System.out.println("Size ::"+ array.size());
		     for (int i = 0; i < array.size(); i++) {
		    	 JSONObject parent =  (JSONObject) array.get(i);
		    	 JSONArray addressComponents = (JSONArray) parent.get("address_components");

		    	 AddressDetails addressDetails = new AddressDetails();
		    	 addressDetails.setFormatted_address(parent.get("formatted_address")+"");
		    	 
		    	 JSONObject geometry = (JSONObject) parent.get("geometry");
		    	 
		    	 /* Bounds start */
		    	 JSONObject bounds = (JSONObject) geometry.get("bounds");
		    	 
		    	 JSONObject southwest = (JSONObject) bounds.get("southwest");
		    	 addressDetails.setGeometry_bounds_southwest_lat(southwest.get("lat")+"");
		    	 addressDetails.setGeometry_bounds_southwest_lng(southwest.get("lng")+"");
		    	 
		    	 JSONObject northeast = (JSONObject) bounds.get("northeast");
		    	 addressDetails.setGeometry_bounds_northeast_lat(northeast.get("lat")+"");
		    	 addressDetails.setGeometry_bounds_northeast_lng(northeast.get("lng")+"");
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
		    	 
		    	 JSONArray types = (JSONArray) parent.get("types");
		    	 for (int j = 0; j < types.size(); j++) {
		    		 addressDetails.setTypes(types.get(j)+"");
				}
		    	 
		    	 System.out.println(parent.get("place_id")+"");
		    	 
		    	 for (int j = 0; j < addressComponents.size(); j++) {
		    		 JSONObject addressComponentsObje = (JSONObject) addressComponents.get(j);
		    		 AddressComponents addressComponents2 = new AddressComponents();
		    		 addressComponents2.setAddressDetailId(0);
		    		 addressComponents2.setLong_name(addressComponentsObje.get("long_name")+"");
		    		 addressComponents2.setShort_name(addressComponentsObje.get("short_name")+"");
		    		 addressComponents2.setTypes(addressComponentsObje.get("types")+"");
		    		 
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
		
}
