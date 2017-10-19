package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationBar extends SuperPage
{
    @FindBy(linkText="All Products")
    private WebElement AllProductsMenu;

    public NavigationBar(WebDriver driver)
    {
        super(driver);
    }

    public void navigateViaAllProductsMenu(String type, String subType)
    {
        AllProductsMenu.click();
        WebElement productType = driver.findElement(By.linkText(type));
        productType.click();
        WebElement productSubType = driver.findElement(By.linkText(subType));
        productSubType.click();
    }
}
