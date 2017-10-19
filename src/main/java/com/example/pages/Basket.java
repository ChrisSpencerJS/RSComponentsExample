package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class Basket extends SuperPage
{
    @FindBy(id="checkoutSecurelyAndPuchBtn")
    private WebElement checkoutSecurelyBtn;

    @FindBy(className="descriptionTd")
    private WebElement descriptionColumn;

    public Basket(WebDriver driver)
    {
        super(driver);
        waitUntilLoaded();
    }

    public Boolean basketContainsProduct(String searchTerm)
    {
        Boolean found = false;
        WebElement anchor = descriptionColumn.findElement(By.tagName("a"));
        if(anchor.getText().contains(searchTerm))
            found = true;
        return found;
    }

    public void checkoutSecurely()
    {
        waitForElementToBeClickable(checkoutSecurelyBtn);
        checkoutSecurelyBtn.click();
        WebElement column = driver.findElement(By.className("loginColumnTwo"));
        List<WebElement> inputs = column.findElements(By.tagName("input"));
        for(WebElement input : inputs)
        {
            if(input.isDisplayed())
                input.sendKeys("chris.spencer@example.com");
        }
        driver.findElement(By.id("guestCheckoutForm:guestCheckout")).click();
    }

    public void waitUntilLoaded()
    {
        waitForElementToBeClickable(checkoutSecurelyBtn);
    }
}
