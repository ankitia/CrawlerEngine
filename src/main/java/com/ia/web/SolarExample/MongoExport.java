package com.ia.web.SolarExample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MongoExport {

	
	public static void main(String[] args) {

        String db = "bb";
        String col = "url_status";
        String Host = "localhost";
        String Port = "27017";
        String fileName = "/home/jaynil/Desktop/col.csv";

        String command = "C:\\Program Files\\MongoDB\\Server\\3.4\\bin\\mongoexport.exe --host " + Host + " --port " + Port + " --db " + db + " --collection " + col + " --type=csv --fields _id,email,createdAt, --out " + fileName + "";
        
        command = "mongoexport --host "+Host+" --db "+db+" --collection "+col+" --csv --out /home/jaynil/Desktop/text.csv --fields tld,data.0.status";

        try {
            System.out.println(command);
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
	
}
