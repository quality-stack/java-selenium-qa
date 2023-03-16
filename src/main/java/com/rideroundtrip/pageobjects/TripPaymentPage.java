package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class TripPaymentPage 
{
	@FindBy(xpath ="//p[contains(text(),'You selected a Lyft for this trip. The payer type')]")
	private @Getter  WebElement lyftMessage;
	@FindBy(xpath ="//select[@id='payer_payer_type']")
	private @Getter  WebElement payerType;
	@FindBy(xpath ="//select[@id='payer_program_id']")
	private @Getter  WebElement facilityProgram;
	@FindBy(xpath ="/html[1]/body[1]/div[1]/div[3]/form[1]/div[7]/p[1]")
	private @Getter  WebElement facilityNotes;
	@FindBy(xpath ="//input[@placeholder='E.g. Aetna Better Health']")
	private @Getter  WebElement planName;
	@FindBy(xpath ="//input[@name='payer[medicaid_id]']")
	private @Getter  WebElement medicaidID;
	@FindBy(xpath ="//input[@name='payer[zipcode]']")
	private @Getter  WebElement zipcode;
	@FindBy(xpath ="//input[@id='payer_email']")
	private @Getter  WebElement patientEmail;
	@FindBy(xpath ="//input[@id='payer_card_name']")
	private @Getter  WebElement cardName;
	@FindBy(xpath ="//div[@id='payer-card-number']")
	private @Getter  WebElement cardNumber;
	@FindBy(xpath ="//div[@id='payer-card-expiry']")
	private @Getter  WebElement cardExpiry;
	@FindBy(xpath ="//div[@id='payer-card-cvc']")
	private @Getter  WebElement cardCVC;
	@FindBy(xpath ="//input[@name='payer[carrier]']")
	private @Getter  WebElement carrier;
	@FindBy(xpath ="//input[@name='payer[plan_type]']")
	private @Getter  WebElement planType;
	@FindBy(xpath ="//input[@name='payer[private_id]']")
	private @Getter  WebElement insuranceID;
	@FindBy(xpath = "//button[@id='payer_validator']")
	private @Getter WebElement continuebtn;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[13]/a[1]")
	private @Getter WebElement backbtn;
	

	public TripPaymentPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}


	public WebElement getPayerType() {
		return payerType;
	}


	public void setPayerType(WebElement payerType) {
		this.payerType = payerType;
	}


	public WebElement getFacilityProgram() {
		return facilityProgram;
	}


	public void setFacilityProgram(WebElement facilityProgram) {
		this.facilityProgram = facilityProgram;
	}


	public WebElement getPlanName() {
		return planName;
	}


	public void setPlanName(WebElement planName) {
		this.planName = planName;
	}


	public WebElement getMedicaidID() {
		return medicaidID;
	}


	public void setMedicaidID(WebElement medicaidID) {
		this.medicaidID = medicaidID;
	}


	public WebElement getZipcode() {
		return zipcode;
	}


	public void setZipcode(WebElement zipcode) {
		this.zipcode = zipcode;
	}


	public WebElement getPatientEmail() {
		return patientEmail;
	}


	public void setPatientEmail(WebElement patientEmail) {
		this.patientEmail = patientEmail;
	}


	public WebElement getCardName() {
		return cardName;
	}


	public void setCardName(WebElement cardName) {
		this.cardName = cardName;
	}


	public WebElement getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(WebElement cardNumber) {
		this.cardNumber = cardNumber;
	}


	public WebElement getCardExpiry() {
		return cardExpiry;
	}


	public void setCardExpiry(WebElement cardExpiry) {
		this.cardExpiry = cardExpiry;
	}


	public WebElement getCardCVC() {
		return cardCVC;
	}


	public void setCardCVC(WebElement cardCVC) {
		this.cardCVC = cardCVC;
	}


	public WebElement getCarrier() {
		return carrier;
	}


	public void setCarrier(WebElement carrier) {
		this.carrier = carrier;
	}


	public WebElement getPlanType() {
		return planType;
	}


	public void setPlanType(WebElement planType) {
		this.planType = planType;
	}


	public WebElement getInsuranceID() {
		return insuranceID;
	}


	public void setInsuranceID(WebElement insuranceID) {
		this.insuranceID = insuranceID;
	}


	public WebElement getContinuebtn() {
		return continuebtn;
	}


	public void setContinuebtn(WebElement continuebtn) {
		this.continuebtn = continuebtn;
	}
	
}
