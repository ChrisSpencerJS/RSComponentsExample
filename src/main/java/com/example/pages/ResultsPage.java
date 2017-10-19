package com.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ResultsPage extends SuperPage
{
    @FindBy(id="results-table")
    private WebElement resultsTable;

    @FindBy(id="bread-box")
    private WebElement breadBox;

    public ResultsPage(WebDriver driver)
    {
        super(driver);
        waitUntilLoaded();
    }

    public Boolean resultsTableContainsSearchedForProduct(String searchTerm)
    {
        Boolean found = false;
        List<WebElement> elements = resultsTable.findElements(By.className("product-name"));
        for(WebElement element : elements)
        {
            if(element.getText().contains(searchTerm))
                found = true;
        }
        return found;
    }

    public void waitUntilLoaded()
    {
        waitForElementToBeClickable(resultsTable);
    }

    public ResultsPage filterBy(String filterText, String type)
    {
        WebElement filter = driver.findElement(By.linkText(filterText));
        filter.click();
        List<WebElement> options = driver.findElements(By.className("checkbox"));
        for(WebElement option : options)
        {
            WebElement span = option.findElement(By.tagName("span"));
            String spanText = span.getText();
            if(span.getText().equals(type))
            {
                WebElement label = option.findElement(By.tagName("label"));
                label.click();
            }
        }
        breadBox.findElement(By.tagName("button")).click();
        waitUntilLoaded();
        return new ResultsPage(driver);
    }

    public Boolean resultsTableFilteredByCorrectBrand(String brandName)
    {
        /*
        Rather than check each row, I'm counting the numbers of visible rows and the number
        of brand name links and ensuring that they're the same
         */
        int visibleRows = 0;
        List<WebElement> rows = resultsTable.findElements(By.className("resultRow"));
        for(WebElement row : rows)
        {
            try
            {
                if(row.isEnabled())
                    visibleRows++;
            }
            catch(StaleElementReferenceException ignored)
            {

            }
        }
        List<WebElement> links = resultsTable.findElements(By.linkText(brandName));
        return visibleRows == links.size();
    }

    public void addToBasket()
    {
        List<WebElement> rows = resultsTable.findElements(By.className("resultRow"));
        for(WebElement row : rows)
        {
            if(row.isEnabled())
            {
                int startQty = this.basketQuantity();
                row.findElement(By.tagName("button")).click();
                waitForElementToBeClickable(row.findElement(By.tagName("button")));
                WebDriverWait wait = new WebDriverWait(driver,30);
                wait.until((WebDriver d) -> (this.basketQuantity() == startQty+1));
                return;
            }

        }
    }
}
