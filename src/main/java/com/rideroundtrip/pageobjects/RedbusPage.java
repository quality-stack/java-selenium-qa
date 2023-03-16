package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class RedbusPage {
		private @FindBy(xpath="//input[@data-message='Please enter a source city']")
		@Getter  WebElement From;
		@FindBy(name ="//input[@data-message='Please enter a destination city']")
		private @Getter WebElement To;		
		@FindBy(xpath="//li[@select-id='results[0]']")
		private @Getter  WebElement selectFirst;
		@FindBy(xpath="//input[@id='onward_cal']")
		private @Getter  WebElement Cal;		
		@FindBy(xpath="search_btn")
		private @Getter  WebElement searchBtn;		
		public RedbusPage(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
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
