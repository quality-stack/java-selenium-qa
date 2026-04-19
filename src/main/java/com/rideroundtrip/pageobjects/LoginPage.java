package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
    @FindBy(name = "user[email]")
    private WebElement emailTextBox;

    @FindBy(xpath = "//input[@name='commit']")
    private WebElement nextbtn;

    @FindBy(name = "user[password]")
    private WebElement pwdTextBox;

    @FindBy(xpath = "//div[@class='-alert flash']")
    private WebElement invalidmsg;

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    public void login(String username, String password)
    {
        type(emailTextBox, username, "login email");
        click(nextbtn, "login next button");
        type(pwdTextBox, password, "login password");
        click(nextbtn, "login submit button");
    }

    public boolean isInvalidMessageDisplayed()
    {
        return isDisplayed(invalidmsg, "invalid login message");
    }
}
