package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class HomePage extends SuperPage
{
    @FindBy(className="footer")
    private WebElement footer;

    @FindBy(className="quickOrder")
    private WebElement quickOrderForm;

    @FindBy(id="quickOrderBtn")
    private WebElement quickOrderSubmit;

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    public HomePage visit()
    {
        driver.get("http://uk.rs-online.com/web/");
        waitUntilLoaded();
        return this;
    }

    public void AddToQuickOrder(String stockNo1, int qty1, String stockNo2, int qty2)
    {
        List<WebElement> stockNoBoxes = quickOrderForm.findElements(By.name("stocknum"));
        List<WebElement> qtyBoxes = quickOrderForm.findElements(By.name("qty"));
        if(stockNo1 != null)
        {
            stockNoBoxes.get(0).sendKeys(stockNo1);
            qtyBoxes.get(0).sendKeys(String.valueOf(qty1));
        }
        if(stockNo2 != null)
        {
            stockNoBoxes.get(1).sendKeys(stockNo2);
            qtyBoxes.get(1).sendKeys(String.valueOf(qty2));
        }
        quickOrderSubmit.click();
    }

    public void waitUntilLoaded()
    {
        waitForElementToBeClickable(footer);
    }
}
