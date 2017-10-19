package com.example;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.example.pages.*;

public class StepDefinitions
{
    private WebDriver driver;

    @Before
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("^that I am on the homepage$")
    public void that_I_am_on_the_homepage()
    {
        new HomePage(driver).visit();
    }

    @When("^I do an exact search for ([^\"]*)$")
    public void run_an_exact_search(String searchTerm)
    {
        new HomePage(driver).searchExactProduct(searchTerm);
    }

    @When("^I do a general search for ([^\"]*)$")
    public void run_a_general_search(String searchTerm)
    {
        HomePage home = new HomePage(driver);
        ResultsPage resultsPage = home.searchProduct(searchTerm);
        resultsPage.waitUntilLoaded();
    }

    @Then("^I am taken to the product page for ([^\"]*)$")
    public void I_am_taken_to_the_product_page_for_R_E(String productName)
    {
        ProductPage page = new ProductPage(driver);
        Assert.assertTrue(page.checkCorrectProductPage(productName));
    }

    @Then("^I am taken to a results page containing ([^\"]*)$")
    public void I_am_taken_to_a_results_page_containing_R_E(String searchTerm)
    {
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertTrue(resultsPage.resultsTableContainsSearchedForProduct(searchTerm));
    }

    @When("^I add to basket from the product page$")
    public void I_add_a_product_to_the_basket_from_product_page()
    {
        new ProductPage(driver).addProductToBasket();

    }

    @When("^I add to basket from the results page$")
    public void I_add_a_product_to_the_basket_from_results_page() throws InterruptedException
    {
        new ResultsPage(driver).addToBasket();
    }

    @Then("^I get visual confirmation that (\\d+) product has been successfully added$")
    public void I_get_visual_confirmation_that_the_product_has_been_successfully_added(int numberAdded)
    {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.basketQuantityEquals(numberAdded));
    }

    @When("^I navigate to my basket$")
    public void I_navigate_to_my_basket()
    {
        HomePage home = new HomePage(driver);
        Basket basket = home.clickMiniBasket();
    }

    @Then("^product ([^\"]*) should be in the basket$")
    public void that_product_appears_in_the_basket(String product)
    {
        new Basket(driver).basketContainsProduct(product);
    }

    @When("^I filter by ([^\"]*) for ([^\"]*)$")
    public void filter_results(String filter, String text)
    {
        new ResultsPage(driver).filterBy(filter, text);
    }

    @Then("^my results list is limited to products for ([^\"]*)$")
    public void my_results_list_is_limited_to_products_for_R_E(String company)
    {
        new ResultsPage(driver).resultsTableFilteredByCorrectBrand(company);
    }

    @When("^I select ([^\"]*) and ([^\"]*) from the All Products menu$")
    public void I_select_RE_and_RE_from_the_All_Products_menu(String type, String subType)
    {
        new NavigationBar(driver).navigateViaAllProductsMenu(type, subType);
    }

    @Then("^I am taken to the ([^\"]*) categories page$")
    public void I_am_taken_to_the_RE_categories_page(String type)
    {
        new CategoriesPage(driver).checkBreadcrumb(type);
    }

    @When("^I select the ([^\"]*) brand$")
    public void I_select_the_RE_brand(String brand)
    {
        new CategoriesPage(driver).selectTopBrand(brand);
    }

    @When("^I select the ([^\"]*) category$")
    public void the_RE_category(String category)
    {
        new CategoriesByBrandPage(driver).selectPopularCategory(category);
    }

    @Then("^I get a results page containing RS Stock No ([^\"]*)$")
    public void I_get_a_results_page_containing_RS_Stock_No_(String product)
    {
        new ResultsPage(driver).resultsTableContainsSearchedForProduct(product);
    }

    @When("^I add RS Stock No ([^\"]*) to the basket$")
    public void I_add_RS_Stock_No_to_the_basket(String stockNo) throws InterruptedException {
        new ResultsPage(driver).addToBasket();
    }

    @When("^I click the Checkout securely button$")
    public void I_click_the_Checkout_securely_button()
    {
        new Basket(driver).checkoutSecurely();
    }

    @Then("^I am taken to the checkout wizard$")
    public void I_am_taken_to_the_checkout_wizard()
    {
        Assert.assertTrue(new SecureCheckoutPage(driver).isCorrectPage());
    }

    @When("^I add the following via Quick Order:$")
    public void I_enter_the_following_via_Quick_Order(DataTable dt)
    {
        List<List<String>> data = dt.raw();
        List<String> quickOrderFirst = data.get(0);
        List<String> quickOrderSecond = data.get(1);
        new HomePage(driver).AddToQuickOrder(quickOrderFirst.get(0),Integer.parseInt(quickOrderFirst.get(1)),
                                             quickOrderSecond.get(0),Integer.parseInt(quickOrderSecond.get(1)));
    }

    @After
    public void cleanUp()
    {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
