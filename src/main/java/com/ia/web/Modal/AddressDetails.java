package com.ia.web.Modal;

public class AddressDetails {

	private int addressDetailId;
	private String geometry_bounds_northeast_lat;
	private String geometry_bounds_northeast_lng;
	private String geometry_bounds_southwest_lat;
	private String geometry_bounds_southwest_lng;
	private String geometry_location_lat;
	private String geometry_location_lng;
	private String geometry_location_type;
	private String geometry_viewport_northeast_lat;
	private String geometry_viewport_northeast_lng;
	private String geometry_viewport_southwest_lat;
	private String geometry_viewport_southwest_lng;
	private String place_id;
	private String types;
	private String formatted_address;
	private String main_address;
	private int master_address_id;
	
	
	
	public int getMaster_address_id() {
		return master_address_id;
	}
	public void setMaster_address_id(int master_address_id) {
		this.master_address_id = master_address_id;
	}
	public String getMain_address() {
		return main_address;
	}
	public void setMain_address(String main_address) {
		this.main_address = main_address;
	}
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	public int getAddressDetailId() {
		return addressDetailId;
	}
	public void setAddressDetailId(int addressDetailId) {
		this.addressDetailId = addressDetailId;
	}
	public String getGeometry_bounds_northeast_lat() {
		return geometry_bounds_northeast_lat;
	}
	public void setGeometry_bounds_northeast_lat(String geometry_bounds_northeast_lat) {
		this.geometry_bounds_northeast_lat = geometry_bounds_northeast_lat;
	}
	public String getGeometry_bounds_northeast_lng() {
		return geometry_bounds_northeast_lng;
	}
	public void setGeometry_bounds_northeast_lng(String geometry_bounds_northeast_lng) {
		this.geometry_bounds_northeast_lng = geometry_bounds_northeast_lng;
	}
	public String getGeometry_bounds_southwest_lat() {
		return geometry_bounds_southwest_lat;
	}
	public void setGeometry_bounds_southwest_lat(String geometry_bounds_southwest_lat) {
		this.geometry_bounds_southwest_lat = geometry_bounds_southwest_lat;
	}
	public String getGeometry_bounds_southwest_lng() {
		return geometry_bounds_southwest_lng;
	}
	public void setGeometry_bounds_southwest_lng(String geometry_bounds_southwest_lng) {
		this.geometry_bounds_southwest_lng = geometry_bounds_southwest_lng;
	}
	public String getGeometry_location_lat() {
		return geometry_location_lat;
	}
	public void setGeometry_location_lat(String geometry_location_lat) {
		this.geometry_location_lat = geometry_location_lat;
	}
	public String getGeometry_location_lng() {
		return geometry_location_lng;
	}
	public void setGeometry_location_lng(String geometry_location_lng) {
		this.geometry_location_lng = geometry_location_lng;
	}
	public String getGeometry_location_type() {
		return geometry_location_type;
	}
	public void setGeometry_location_type(String geometry_location_type) {
		this.geometry_location_type = geometry_location_type;
	}
	public String getGeometry_viewport_northeast_lat() {
		return geometry_viewport_northeast_lat;
	}
	public void setGeometry_viewport_northeast_lat(String geometry_viewport_northeast_lat) {
		this.geometry_viewport_northeast_lat = geometry_viewport_northeast_lat;
	}
	public String getGeometry_viewport_northeast_lng() {
		return geometry_viewport_northeast_lng;
	}
	public void setGeometry_viewport_northeast_lng(String geometry_viewport_northeast_lng) {
		this.geometry_viewport_northeast_lng = geometry_viewport_northeast_lng;
	}
	public String getGeometry_viewport_southwest_lat() {
		return geometry_viewport_southwest_lat;
	}
	public void setGeometry_viewport_southwest_lat(String geometry_viewport_southwest_lat) {
		this.geometry_viewport_southwest_lat = geometry_viewport_southwest_lat;
	}
	public String getGeometry_viewport_southwest_lng() {
		return geometry_viewport_southwest_lng;
	}
	public void setGeometry_viewport_southwest_lng(String geometry_viewport_southwest_lng) {
		this.geometry_viewport_southwest_lng = geometry_viewport_southwest_lng;
	}
	public String getPlace_id() {
		return place_id;
	}
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
}
