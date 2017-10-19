package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoriesByBrandPage extends SuperPage
{
    @FindBy(id="mainContent")
    private WebElement mainContent;

    @FindBy(id="breadcrumb")
    private WebElement breadCrumbPanel;

    @FindBy(className="galleryPopularCategory")
    private WebElement popularCategories;

    public CategoriesByBrandPage(WebDriver driver)
    {
        super(driver);
        waitUntilLoaded();
    }

    public void selectPopularCategory(String categoryName)
    {
        driver.findElement(By.linkText(categoryName)).click();
    }

    public void waitUntilLoaded()
    {
        waitForElementToBeClickable(mainContent);
    }
}


