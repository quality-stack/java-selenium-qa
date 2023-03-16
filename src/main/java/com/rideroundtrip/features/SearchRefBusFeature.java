package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import com.rideroundtrip.pageobjects.RedbusPage;

public class SearchRefBusFeature {
	WebDriver driver;
	RedbusPage redbusPage;
	
	public SearchRefBusFeature(WebDriver driver) 
	{
		this.driver= driver;
		redbusPage = new RedbusPage(driver);
	}
	
	public void enterSource() {
		redbusPage.getFrom().isDisplayed();
		redbusPage.getFrom().click();
		redbusPage.getFrom().clear();
		redbusPage.getFrom().sendKeys("Delhi");	
		System.out.println("Source Entered");
	}
	
	public void selectSource() throws InterruptedException {
		redbusPage.getSelectFirst().isDisplayed();
		redbusPage.getSelectFirst().click();
		System.out.println("Source Selected");	
		Thread.sleep(3000);
	}
	
	public void enterDestination() {
//		redbusPage.getTo().isDisplayed();
//		redbusPage.getTo().click();
//		redbusPage.getTo().clear();
		redbusPage.getTo().sendKeys("Manali");
		System.out.println("Destination Entered");
	}
	
	public void selectDestination() {
		redbusPage.getSelectFirst().isDisplayed();
		redbusPage.getSelectFirst().click();
		System.out.println("Destination Selected");		
	}
	
	public void clickSearch() {
		redbusPage.getSearchBtn().isDisplayed();
		redbusPage.getSearchBtn().click();
		System.out.println("Search button clicked");
	}

}
