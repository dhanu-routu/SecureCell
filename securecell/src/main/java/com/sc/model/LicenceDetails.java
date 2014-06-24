package com.sc.model;

public class LicenceDetails
{
	private String uid;
	private int licenseStatus;
	private Long purchaseDate;
	private Long expiryDate;
	private String affiliateName;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getLicenseStatus() {
		return licenseStatus;
	}
	public void setLicenseStatus(int licenseStatus) {
		this.licenseStatus = licenseStatus;
	}
	public Long getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Long purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Long getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Long expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getAffiliateName() {
		return affiliateName;
	}
	public void setAffiliateName(String affiliateName) {
		this.affiliateName = affiliateName;
	}
	
}
