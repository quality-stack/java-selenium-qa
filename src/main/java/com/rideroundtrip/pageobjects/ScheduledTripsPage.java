package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class ScheduledTripsPage 
{
	@FindBy(xpath="//h2[@class='empty-state-heading']")
	private @Getter WebElement defaultTxt;
	@FindBy(xpath="//a[contains(text(),'Scheduled')]")
	private @Getter WebElement scheduledtab;
	@FindBy(xpath="//a[contains(text(),'Completed')]")
	private @Getter WebElement completedtab;
	@FindBy(xpath="//a[contains(text(),'Canceled')]")
	private @Getter WebElement canceledtab;
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[2]/a[1]")
	private @Getter WebElement booktripbtn;
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[2]/h2[1]/div[1]/input[1]")
	private @Getter WebElement calwidget;
	@FindBy(linkText="Patients")
	private @Getter WebElement patientstab;
	
	public ScheduledTripsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getPatientstab() {
		return patientstab;
	}

	public WebElement getBooktripbtn() {
		return booktripbtn;
	}

	public void setBooktripbtn(WebElement booktripbtn) {
		this.booktripbtn = booktripbtn;
	}

}
