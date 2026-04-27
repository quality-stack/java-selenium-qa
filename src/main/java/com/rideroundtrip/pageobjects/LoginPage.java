package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the RoundTrip login form.
 */
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

    /**
     * Binds the page object to the active browser session.
     */
    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    /**
     * Completes the login form and submits the sign-in flow.
     */
    public void login(String username, String password)
    {
        type(emailTextBox, username, "login email");
        click(nextbtn, "login next button");
        type(pwdTextBox, password, "login password");
        click(nextbtn, "login submit button");
    }

    /**
     * Returns whether the invalid-login banner is visible.
     */
    public boolean isInvalidMessageDisplayed()
    {
        return isDisplayed(invalidmsg, "invalid login message");
    }
}
