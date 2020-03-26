package com.ia.web.SolarExample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ia.web.Dao.EmailDAO;
import com.ia.web.Modal.MasterEmail;

@Controller
public class EmailController {

	@Autowired
	EmailDAO emailDAO;
	
	
	@RequestMapping(value="/insertEmailData")
	@ResponseBody public String insertEmailData(Model model) {
		 
		List<MasterEmail> emails = emailDAO.getEmailData(0);
		System.out.println(emails.size());
		for (int i = 0; i < emails.size(); i++) {
			
			if(i==3)
				CheckingMails.check(emails.get(i).getHost(), "pop3", emails.get(i).getEmailId(), emails.get(i).getPassword(),emailDAO,emails.get(i).getId());
			
			
		}
		
		
		 return "project";
	 }
	
	 
	
	
}
