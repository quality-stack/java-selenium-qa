package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

public class LoginPage 
{
	@FindBy(name ="user[email]")
	private @Getter  WebElement EmailTextBox;
	@FindBy(xpath="//input[@name='commit']")
	private @Getter  WebElement nextbtn;
	@FindBy(name ="user[password]")
	private @Getter WebElement PwdTextBox;
	@FindBy(xpath="//input[@name='commit']")
	private @Getter  WebElement signinbtn;
	@FindBy(xpath = "//div[@class='-alert flash']")
	private @Getter WebElement invalidmsg;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getEmailTextBox() {
		return EmailTextBox;
	}

	public void setEmailTextBox(WebElement emailTextBox) {
		EmailTextBox = emailTextBox;
	}

	public WebElement getPwdTextBox() {
		return PwdTextBox;
	}

	public void setPwdTextBox(WebElement pwdTextBox) {
		PwdTextBox = pwdTextBox;
	}

	public WebElement getNextbtn() {
		return nextbtn;
	}

	public void setNextbtn(WebElement nextbtn) {
		this.nextbtn = nextbtn;
	}

	public WebElement getSigninbtn() {
		return signinbtn;
	}

	public void setSigninbtn(WebElement signinbtn) {
		this.signinbtn = signinbtn;
	}

	public WebElement getInvalidmsg() {
		return invalidmsg;
	}

	public void setInvalidmsg(WebElement invalidmsg) {
		this.invalidmsg = invalidmsg;
	}
}
