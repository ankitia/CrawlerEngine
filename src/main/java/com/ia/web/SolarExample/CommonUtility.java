package com.ia.web.SolarExample;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class CommonUtility 
{ 
	public static int NO_OFF_RECORDS = 10;
	public static  String REQUEST_URL = "";
	public static String ENRICH_URL = "http://74.207.234.174:5000";
	public static double TOTAL_RECEORD = 0;
	public static int LOGIN_USER_ID = 0;
	public static int USER_ROLE_ID = 0;
  
	
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
	                hashMap.put(country[1], country[1]);
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
} 
