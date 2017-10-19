package com.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuperPage
{
    protected WebDriver driver;

    @FindBy(id="searchTerm")
    private WebElement searchBox;

    @FindBy(id="btnSearch")
    private WebElement searchButton;

    @FindBy(className="icon-cart")
    private WebElement miniBasket;

    @FindBy(id="js-basketQty")
    private WebElement basketQty;

    @FindBy(id="js-basketAmt")
    private WebElement basketCost;

    public SuperPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToBeClickable(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public ProductPage searchExactProduct(String searchTerm)
    {
        this.searchBox.clear();
        this.searchBox.sendKeys(searchTerm);
        this.searchBox.sendKeys(Keys.ESCAPE);
        this.searchButton.click();

        return new ProductPage(driver);
    }

    public ResultsPage searchProduct(String searchTerm)
    {
        this.searchBox.clear();
        this.searchBox.sendKeys(searchTerm);
        this.searchBox.sendKeys(Keys.ESCAPE);
        this.searchButton.click();

        return new ResultsPage(driver);
    }

    public Boolean basketQuantityEquals(int expectedValue)
    {
        waitForElementToBeVisible(miniBasket);
        int actualValue = Integer.parseInt(basketQty.getText());
        return actualValue == expectedValue;
    }

    public int basketQuantity()
    {
        return Integer.parseInt(basketQty.getText());
    }

    public Float getBasketCost()
    {
        return Float.parseFloat(basketCost.getText());
    }

    public Basket clickMiniBasket()
    {
        miniBasket.click();
        return new Basket(driver);
    }

    public void waitUntilLoaded()
    {
        waitForElementToBeClickable(searchBox);
    }

    public boolean retryFindClick(WebElement element, By by)
    {
        boolean result = false;
        int attempts = 0;
        while(attempts < 10)
        {
            try
            {
                element.findElement(by).click();
                result = true;
                break;
            }
            catch(StaleElementReferenceException e)
            {
            }
            attempts++;
        }
        return result;
    }
}
