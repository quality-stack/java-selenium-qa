package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class SetupTripPage
{
	@FindBy(xpath ="//label[@id='one-way-label']//div[1]")
	private @Getter  WebElement onewaybtn;
	@FindBy(xpath="//label[@id='roundtrip-label']//div[1]")
	private @Getter  WebElement roundtripbtn;
	@FindBy(xpath ="/html[1]/body[1]/div[1]/div[3]/form[1]/div[3]/label[1]/div[1]")
	private @Getter WebElement repeatcheckbox;
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[3]/form[1]/div[5]/div[1]/div[1]/div[1]")
	private @Getter  WebElement pickupbtn;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[6]/div[1]/div[1]/div[1]")
	private @Getter WebElement dropbtn;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]")
	private @Getter WebElement residencetab;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/p[1]")
	private @Getter WebElement noresidencemsg;
	@FindBy (xpath = "/html[1]/body[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]")
	private @Getter WebElement selectlocation;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/div[1]/div[3]/button[1]")
	private @Getter WebElement addlocationbtn;
	@FindBy (xpath = "//input[@id='full_address']")
	private @Getter WebElement enteraddress;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]")
	private @Getter WebElement facilitytab;
	@FindBy(xpath = "//input[@id='outbound-date-calendar']")
	private @Getter WebElement outboundcaldate;
	@FindBy(xpath = "//input[@id='outbound_trip_time']")
	private @Getter WebElement outboundtime;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[8]/div[1]/div[3]/label[1]/div[1]")
	private @Getter WebElement outboundwillcall;
	@FindBy(xpath = "//input[@id='return-date-calendar']")
	private @Getter WebElement returncaldate;
	@FindBy(xpath = "//input[@id='return_trip_time']")
	private @Getter WebElement returntime;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[9]/div[3]/label[1]/div[1]")
	private @Getter WebElement returnwillcall;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[11]/button[1]")
	private @Getter WebElement continuebtn;
	@FindBy(xpath = "//a[@class='link -back']")
	private @Getter WebElement backbtn;

	public SetupTripPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getOnewaybtn() {
		return onewaybtn;
	}

	public WebElement getRoundtripbtn() {
		return roundtripbtn;
	}

	public WebElement getRepeatcheckbox() {
		return repeatcheckbox;
	}

	public WebElement getPickupbtn() {
		return pickupbtn;
	}

	public WebElement getNoresidencemsg() {
		return noresidencemsg;
	}

	public WebElement getAddlocationbtn() {
		return addlocationbtn;
	}

	public WebElement getselectlocation() {
		return selectlocation;
	}

	public WebElement getDropbtn() {
		return dropbtn;
	}

	public WebElement getFacilitytab() {
		return facilitytab;
	}	
}
