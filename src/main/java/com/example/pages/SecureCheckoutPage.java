package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecureCheckoutPage extends SuperPage
{
    @FindBy(id="checkoutSecurelyAndPuchBtn")
    private WebElement continueToPayment;

    @FindBy(className="descriptionTd")
    private WebElement descriptionColumn;

    public SecureCheckoutPage(WebDriver driver)
    {
        super(driver);
        waitUntilLoaded();
    }

    public Boolean isCorrectPage()
    {
        return continueToPayment.isDisplayed();
    }

    public void waitUntilLoaded()
    {
        waitForElementToBeClickable(continueToPayment);
    }
}
