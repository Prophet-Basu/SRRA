package com.bean;

import java.io.Serializable;

public class StudentPersonalDetailsBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long studRoll;
	private long studReg;
	private String studName;
	private String studFName;
	private String studLName;
	private String studPresentAddress;
	private String studPresentAddressCity;
	private String studPresentAddressPin;
	private String studPresentAddressPostOffice;
	private String studPresentAddressDistrict;
	private String studPresentAddressState;
	private String studPermanentAddress;
	private String studPermanentAddressCity;
	private String studPermanentAddressPin;
	private String studPermanentAddressPostOffice;
	private String studPermanentAddressDistrict;
	private String studPermanentAddressState;
	private String studDOB;
	private String studGender;
	private String studBloodGroup;
	private String studLandlineCode;
	private String studLandline;
	private long studMob1;
	private long studMob2;
	private String studEmail;
	private String studFatherName;
	private String studFatherService;
	private String studMotherName;
	private String studMotherService;
	private long studParentMob1;
	private long studParentMob2;
	private String studLocalGuardianName;
	private String studLocalGuardianRelation;
	private String studLocalGuardianService;
	private long studLocalGuardianMob;
	public StudentPersonalDetailsBean(long studRoll, long studReg, String studName, String studFName, String studLName,
			String studPresentAddress, String studPresentAddressCity, String studPresentAddressPin,
			String studPresentAddressPostOffice, String studPresentAddressDistrict, String studPresentAddressState,
			String studPermanentAddress, String studPermanentAddressCity, String studPermanentAddressPin,
			String studPermanentAddressPostOffice, String studPermanentAddressDistrict,
			String studPermanentAddressState, String studDOB, String studGender, String studBloodGroup,
			String studLandlineCode, String studLandline, long studMob1, long studMob2, String studEmail,
			String studFatherName, String studFatherService, String studMotherName, String studMotherService,
			long studParentMob1, long studParentMob2, String studLocalGuardianName, String studLocalGuardianRelation,
			String studLocalGuardianService, long studLocalGuardianMob) {
		super();
		this.studRoll = studRoll;
		this.studReg = studReg;
		this.studName = studName;
		this.studFName = studFName;
		this.studLName = studLName;
		this.studPresentAddress = studPresentAddress;
		this.studPresentAddressCity = studPresentAddressCity;
		this.studPresentAddressPin = studPresentAddressPin;
		this.studPresentAddressPostOffice = studPresentAddressPostOffice;
		this.studPresentAddressDistrict = studPresentAddressDistrict;
		this.studPresentAddressState = studPresentAddressState;
		this.studPermanentAddress = studPermanentAddress;
		this.studPermanentAddressCity = studPermanentAddressCity;
		this.studPermanentAddressPin = studPermanentAddressPin;
		this.studPermanentAddressPostOffice = studPermanentAddressPostOffice;
		this.studPermanentAddressDistrict = studPermanentAddressDistrict;
		this.studPermanentAddressState = studPermanentAddressState;
		this.studDOB = studDOB;
		this.studGender = studGender;
		this.studBloodGroup = studBloodGroup;
		this.studLandlineCode = studLandlineCode;
		this.studLandline = studLandline;
		this.studMob1 = studMob1;
		this.studMob2 = studMob2;
		this.studEmail = studEmail;
		this.studFatherName = studFatherName;
		this.studFatherService = studFatherService;
		this.studMotherName = studMotherName;
		this.studMotherService = studMotherService;
		this.studParentMob1 = studParentMob1;
		this.studParentMob2 = studParentMob2;
		this.studLocalGuardianName = studLocalGuardianName;
		this.studLocalGuardianRelation = studLocalGuardianRelation;
		this.studLocalGuardianService = studLocalGuardianService;
		this.studLocalGuardianMob = studLocalGuardianMob;
	}
	public long getStudRoll() {
		return studRoll;
	}
	public void setStudRoll(long studRoll) {
		this.studRoll = studRoll;
	}
	public long getStudReg() {
		return studReg;
	}
	public void setStudReg(long studReg) {
		this.studReg = studReg;
	}
	public String getStudName() {
		return studName;
	}
	public void setStudName(String studName) {
		this.studName = studName;
	}
	public String getStudFName() {
		return studFName;
	}
	public void setStudFName(String studFName) {
		this.studFName = studFName;
	}
	public String getStudLName() {
		return studLName;
	}
	public void setStudLName(String studLName) {
		this.studLName = studLName;
	}
	public String getStudPresentAddress() {
		return studPresentAddress;
	}
	public void setStudPresentAddress(String studPresentAddress) {
		this.studPresentAddress = studPresentAddress;
	}
	public String getStudPresentAddressCity() {
		return studPresentAddressCity;
	}
	public void setStudPresentAddressCity(String studPresentAddressCity) {
		this.studPresentAddressCity = studPresentAddressCity;
	}
	public String getStudPresentAddressPin() {
		return studPresentAddressPin;
	}
	public void setStudPresentAddressPin(String studPresentAddressPin) {
		this.studPresentAddressPin = studPresentAddressPin;
	}
	public String getStudPresentAddressPostOffice() {
		return studPresentAddressPostOffice;
	}
	public void setStudPresentAddressPostOffice(String studPresentAddressPostOffice) {
		this.studPresentAddressPostOffice = studPresentAddressPostOffice;
	}
	public String getStudPresentAddressDistrict() {
		return studPresentAddressDistrict;
	}
	public void setStudPresentAddressDistrict(String studPresentAddressDistrict) {
		this.studPresentAddressDistrict = studPresentAddressDistrict;
	}
	public String getStudPresentAddressState() {
		return studPresentAddressState;
	}
	public void setStudPresentAddressState(String studPresentAddressState) {
		this.studPresentAddressState = studPresentAddressState;
	}
	public String getStudPermanentAddress() {
		return studPermanentAddress;
	}
	public void setStudPermanentAddress(String studPermanentAddress) {
		this.studPermanentAddress = studPermanentAddress;
	}
	public String getStudPermanentAddressCity() {
		return studPermanentAddressCity;
	}
	public void setStudPermanentAddressCity(String studPermanentAddressCity) {
		this.studPermanentAddressCity = studPermanentAddressCity;
	}
	public String getStudPermanentAddressPin() {
		return studPermanentAddressPin;
	}
	public void setStudPermanentAddressPin(String studPermanentAddressPin) {
		this.studPermanentAddressPin = studPermanentAddressPin;
	}
	public String getStudPermanentAddressPostOffice() {
		return studPermanentAddressPostOffice;
	}
	public void setStudPermanentAddressPostOffice(String studPermanentAddressPostOffice) {
		this.studPermanentAddressPostOffice = studPermanentAddressPostOffice;
	}
	public String getStudPermanentAddressDistrict() {
		return studPermanentAddressDistrict;
	}
	public void setStudPermanentAddressDistrict(String studPermanentAddressDistrict) {
		this.studPermanentAddressDistrict = studPermanentAddressDistrict;
	}
	public String getStudPermanentAddressState() {
		return studPermanentAddressState;
	}
	public void setStudPermanentAddressState(String studPermanentAddressState) {
		this.studPermanentAddressState = studPermanentAddressState;
	}
	public String getStudDOB() {
		return studDOB;
	}
	public void setStudDOB(String studDOB) {
		this.studDOB = studDOB;
	}
	public String getStudGender() {
		return studGender;
	}
	public void setStudGender(String studGender) {
		this.studGender = studGender;
	}
	public String getStudBloodGroup() {
		return studBloodGroup;
	}
	public void setStudBloodGroup(String studBloodGroup) {
		this.studBloodGroup = studBloodGroup;
	}
	public String getStudLandlineCode() {
		return studLandlineCode;
	}
	public void setStudLandlineCode(String studLandlineCode) {
		this.studLandlineCode = studLandlineCode;
	}
	public String getStudLandline() {
		return studLandline;
	}
	public void setStudLandline(String studLandline) {
		this.studLandline = studLandline;
	}
	public long getStudMob1() {
		return studMob1;
	}
	public void setStudMob1(long studMob1) {
		this.studMob1 = studMob1;
	}
	public long getStudMob2() {
		return studMob2;
	}
	public void setStudMob2(long studMob2) {
		this.studMob2 = studMob2;
	}
	public String getStudEmail() {
		return studEmail;
	}
	public void setStudEmail(String studEmail) {
		this.studEmail = studEmail;
	}
	public String getStudFatherName() {
		return studFatherName;
	}
	public void setStudFatherName(String studFatherName) {
		this.studFatherName = studFatherName;
	}
	public String getStudFatherService() {
		return studFatherService;
	}
	public void setStudFatherService(String studFatherService) {
		this.studFatherService = studFatherService;
	}
	public String getStudMotherName() {
		return studMotherName;
	}
	public void setStudMotherName(String studMotherName) {
		this.studMotherName = studMotherName;
	}
	public String getStudMotherService() {
		return studMotherService;
	}
	public void setStudMotherService(String studMotherService) {
		this.studMotherService = studMotherService;
	}
	public long getStudParentMob1() {
		return studParentMob1;
	}
	public void setStudParentMob1(long studParentMob1) {
		this.studParentMob1 = studParentMob1;
	}
	public long getStudParentMob2() {
		return studParentMob2;
	}
	public void setStudParentMob2(long studParentMob2) {
		this.studParentMob2 = studParentMob2;
	}
	public String getStudLocalGuardianName() {
		return studLocalGuardianName;
	}
	public void setStudLocalGuardianName(String studLocalGuardianName) {
		this.studLocalGuardianName = studLocalGuardianName;
	}
	public String getStudLocalGuardianRelation() {
		return studLocalGuardianRelation;
	}
	public void setStudLocalGuardianRelation(String studLocalGuardianRelation) {
		this.studLocalGuardianRelation = studLocalGuardianRelation;
	}
	public String getStudLocalGuardianService() {
		return studLocalGuardianService;
	}
	public void setStudLocalGuardianService(String studLocalGuardianService) {
		this.studLocalGuardianService = studLocalGuardianService;
	}
	public long getStudLocalGuardianMob() {
		return studLocalGuardianMob;
	}
	public void setStudLocalGuardianMob(long studLocalGuardianMob) {
		this.studLocalGuardianMob = studLocalGuardianMob;
	}
	
	
	
		
}
