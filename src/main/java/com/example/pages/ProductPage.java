package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends SuperPage
{
    @FindBy(className="cartButton")
    private WebElement cartButton;

    public ProductPage(WebDriver driver)
    {
        super(driver);
    }

    public void addProductToBasket()
    {
        cartButton.click();
        waitForElementToBeClickable(cartButton);
    }

    public Boolean checkCorrectProductPage(String product)
    {
        return driver.getTitle().contains(product);
    }
}
