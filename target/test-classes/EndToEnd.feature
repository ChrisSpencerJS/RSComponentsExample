Feature: 2x – End to End Test – Homepage to checkout (do not purchase)


  @run
  Scenario: Select product via menu system and results page, progress to secure checkout

    Given that I am on the homepage
    When I select Batteries and Rechargeable Batteries from the All Products menu
    Then I am taken to the Rechargeable Batteries categories page
    When I select the RS Pro brand
    And I select the Power Banks category
    Then my results list is limited to products for RS Pro
    And I get a results page containing RS Stock No 775-7508
    When I add RS Stock No 775-7508 to the basket
    Then I get visual confirmation that 1 product has been successfully added
    When I navigate to my basket
    Then product RS Pro PB-A5200 5000mAh Power Bank Portable Charger should be in the basket
    When I click the Checkout securely button
    Then I am taken to the checkout wizard


  @run
  Scenario: Select product via Quick Order, progress to secure checkout

    Given that I am on the homepage
    When I add the following via Quick Order:
    | 510-2397 | 2 |
    | 453-3807 | 3 |
    Then I get visual confirmation that 2 product has been successfully added
    When I navigate to my basket
    Then product Belden 152m Audio Video Combined Cable should be in the basket
    And product Van Damme 50m Audio Video Combined Cable should be in the basket
    When I click the Checkout securely button
    Then I am taken to the checkout wizard