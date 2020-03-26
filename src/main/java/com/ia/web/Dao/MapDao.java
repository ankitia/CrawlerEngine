package com.ia.web.Dao;

import java.util.ArrayList;

import com.ia.web.Modal.AddressComponents;
import com.ia.web.Modal.AddressDetails;
import com.ia.web.Modal.MasterAddress;

public interface MapDao {

	public ArrayList<MasterAddress> getMasterAddress();
	
	public int insertAddressDetails(AddressDetails addressDetails);
	
	public boolean insertAddressComponents(AddressComponents addressComponents);
	
	
}
