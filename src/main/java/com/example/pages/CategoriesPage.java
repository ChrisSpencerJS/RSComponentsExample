package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CategoriesPage extends SuperPage
{
    @FindBy(id="mainContent")
    private WebElement mainContent;

    @FindBy(id="breadcrumb")
    private WebElement breadCrumbPanel;

    @FindBy(className="brandInner")
    private WebElement topBrands;

    public CategoriesPage(WebDriver driver)
    {
        super(driver);
        waitUntilLoaded();
    }

    public Boolean checkBreadcrumb(String type)
    {
        int found = 0;
        List<WebElement> breadCrumbs = breadCrumbPanel.findElements(By.tagName("li"));
        for(WebElement breadCrumb : breadCrumbs)
        {
            String text = breadCrumb.getText();
            if(breadCrumb.getText().trim().equalsIgnoreCase(type))
                found++;
        }
        if(found > 0)
            return true;
        else
            return false;
    }

    public void selectTopBrand(String brandName)
    {
        topBrands.findElement(By.linkText(brandName)).click();
    }

    public void waitUntilLoaded()
    {
        waitForElementToBeClickable(mainContent);
    }
}
