package com.ia.web.Dao;

import java.util.List;

import com.ia.web.Modal.EmailData;
import com.ia.web.Modal.MasterEmail;

public interface EmailDAO {
	
	//Get email list
	public List<MasterEmail> getEmailData(int status);
	
	//Get all EmailData
	public int insertEmailData(EmailData emailData);
	
 }
