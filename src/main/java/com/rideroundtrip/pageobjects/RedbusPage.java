package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import lombok.Getter;

/**
 * Page object for the public RedBus search form used by the demo test flow.
 */
public class RedbusPage extends BasePage {
        @FindBy(xpath="//input[@data-message='Please enter a source city']")
        private @Getter  WebElement From;
        @FindBy(xpath ="//input[@data-message='Please enter a destination city']")
        private @Getter WebElement To;
        @FindBy(xpath="//li[@select-id='results[0]']")
        private @Getter  WebElement selectFirst;
        @FindBy(xpath="//input[@id='onward_cal']")
        private @Getter  WebElement Cal;
        @FindBy(id="search_btn")
        private @Getter  WebElement searchBtn;
        /**
         * Binds the page object to the active browser session.
         */
        public RedbusPage(WebDriver driver)
        {
            super(driver);
        }
        public WebElement getFrom() {
            return From;
        }
        public void setFrom(WebElement from) {
            From = from;
        }
        public WebElement getTo() {
            return To;
        }
        public void setTo(WebElement to) {
            To = to;
        }
        public WebElement getSearchBtn() {
            return searchBtn;
        }
        public void setSearchBtn(WebElement searchBtn) {
            this.searchBtn = searchBtn;
        }
        public WebElement getSelectFirst() {
            return selectFirst;
        }
        public void setSelectFirst(WebElement selectFirst) {
            this.selectFirst = selectFirst;
        }

}
