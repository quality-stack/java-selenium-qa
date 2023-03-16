package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class SelectPatientPage 
{
	@FindBy(xpath ="//input[@id='patient-search']")
	private @Getter  WebElement searchTextBox;
	@FindBy(linkText ="Add Patient")
	private @Getter  WebElement addPatientBtn;
	@FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[3]")
	private @Getter WebElement searchResult;
	@FindBy (xpath = "//p[@class='empty-state-message']")
	private @Getter WebElement emptyresultmsg;

	public SelectPatientPage(WebDriver driver) 
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getSearchTextBox()
	{
		return searchTextBox;
	}

	public WebElement getEmptyresultmsg()
	{
		return emptyresultmsg;
	}

	public WebElement getSearchResult()
	{
		return searchResult;
	}
	
}
