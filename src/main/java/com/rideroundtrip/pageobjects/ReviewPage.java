package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class ReviewPage
{
	@FindBy(xpath = "/html[1]/body[1]/div[1]/form[1]/div[7]/div[1]/div[2]/div[1]/div[1]/div[2]/h2[1]")
	private @Getter WebElement verifyIcon;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/form[1]/div[1]/div[2]/button[1]")
	private @Getter WebElement bookTripbtn;
	@FindBy(xpath = "//div[@id='passenger-notifications']//div[@class='checkbox']")
	private @Getter  WebElement passengerNotificationcheckbox;
	@FindBy(xpath = "//h2[contains(text(),'Lyft rides require a phone number')]")
	private @Getter WebElement lyftpopup;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/form[1]/div[2]/div[6]/div[1]/div[1]/div[1]/a[1]")
	private @Getter WebElement okaybtn;
	@FindBy(xpath = "/html[1]/body[1]/div[1]/form[1]/div[8]/div[2]/a[1]")
	private @Getter WebElement backbtn;	

	public ReviewPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
}
