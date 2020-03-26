package com.ia.web.Modal;

public class Request 
{

	private String[] urls;
	private String appid;
	private String crawlid;
	private Attribute attrs;
	private String maxdepth;
	private int domain_max_pages;
	private String spiderid;
	/*private HashMap<String,Object> addressAttribute;
	
	
	
	 
	public HashMap<String, Object> getAddressAttribute() {
		return addressAttribute;
	}
	public void setAddressAttribute(HashMap<String, Object> addressAttribute) {
		this.addressAttribute = addressAttribute;
	}*/
	public String getMaxdepth() {
		return maxdepth;
	}
	public int getDomain_max_pages() {
		return domain_max_pages;
	}
	public void setDomain_max_pages(int domain_max_pages) {
		this.domain_max_pages = domain_max_pages;
	}
	public void setMaxdepth(String maxdepth) {
		this.maxdepth = maxdepth;
	}
	public String[] getUrls() {
		return urls;
	}
	public void setUrls(String[] urls) {
		this.urls = urls;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getCrawlid() {
		return crawlid;
	}
	public void setCrawlid(String crawlid) {
		this.crawlid = crawlid;
	}
	public Attribute getAttrs() {
		return attrs;
	}
	public void setAttrs(Attribute attrs) {
		this.attrs = attrs;
	}
	public String getSpiderid() {
		return spiderid;
	}
	public void setSpiderid(String spiderid) {
		this.spiderid = spiderid;
	}
	  
	
}

