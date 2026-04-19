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
	
	public void enterSource(String source) {
		redbusPage.getFrom().isDisplayed();
		redbusPage.getFrom().click();
		redbusPage.getFrom().clear();
		redbusPage.getFrom().sendKeys(source);	
		System.out.println("Source Entered");
	}
	
	public void selectSource() {
		redbusPage.getSelectFirst().isDisplayed();
		redbusPage.getSelectFirst().click();
		System.out.println("Source Selected");
	}
	
	public void enterDestination(String destination) {
		redbusPage.getTo().isDisplayed();
		redbusPage.getTo().click();
		redbusPage.getTo().clear();
		redbusPage.getTo().sendKeys(destination);
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
