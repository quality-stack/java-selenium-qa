package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rideroundtrip.pageobjects.RedbusPage;

/**
 * Encapsulates the public RedBus search flow used by the sample suite.
 */
public class SearchRefBusFeature {
    /** Emits RedBus search-flow progress for debugging. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRefBusFeature.class);
    /** Driver used by the RedBus search interactions. */
    WebDriver driver;
    /** Page object for the RedBus search form. */
    RedbusPage redbusPage;
    
    /**
     * Creates the feature layer for RedBus search actions.
     */
    public SearchRefBusFeature(WebDriver driver) 
    {
        this.driver= driver;
        redbusPage = new RedbusPage(driver);
    }
    
    /**
     * Enters the requested origin city into the search form.
     */
    public void enterSource(String source) {
        LOGGER.info("Entering source city for RedBus search");
        redbusPage.getFrom().isDisplayed();
        redbusPage.getFrom().click();
        redbusPage.getFrom().clear();
        redbusPage.getFrom().sendKeys(source);	
        LOGGER.debug("Source field populated");
    }
    
    /**
     * Selects the first suggested origin result.
     */
    public void selectSource() {
        LOGGER.info("Selecting source suggestion for RedBus search");
        redbusPage.getSelectFirst().isDisplayed();
        redbusPage.getSelectFirst().click();
        LOGGER.debug("Source suggestion selected");
    }
    
    /**
     * Enters the requested destination city into the search form.
     */
    public void enterDestination(String destination) {
        LOGGER.info("Entering destination city for RedBus search");
        redbusPage.getTo().isDisplayed();
        redbusPage.getTo().click();
        redbusPage.getTo().clear();
        redbusPage.getTo().sendKeys(destination);
        LOGGER.debug("Destination field populated");
    }
    
    /**
     * Selects the first suggested destination result.
     */
    public void selectDestination() {
        LOGGER.info("Selecting destination suggestion for RedBus search");
        redbusPage.getSelectFirst().isDisplayed();
        redbusPage.getSelectFirst().click();
        LOGGER.debug("Destination suggestion selected");		
    }
    
    /**
     * Submits the RedBus search form.
     */
    public void clickSearch() {
        LOGGER.info("Submitting RedBus search");
        redbusPage.getSearchBtn().isDisplayed();
        redbusPage.getSearchBtn().click();
        LOGGER.debug("RedBus search button clicked");
    }

}

