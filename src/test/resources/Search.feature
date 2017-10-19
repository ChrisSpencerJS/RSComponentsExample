Feature: 3x – Search filter results page test

  @run
  Scenario: Search for product using partial product name
    Given that I am on the homepage
    When I do a general search for 5-Axis Industrial Robot Arm with Electric 2 Finger Gripper
    Then I am taken to a results page containing ST Robotics R12-5-E2
    When I add to basket from the results page
    Then I get visual confirmation that 1 product has been successfully added
    When I navigate to my basket
    Then product ST Robotics R12-5-E2 should be in the basket

  @run
  Scenario: Search for product using manufacturer part no.
    Given that I am on the homepage
    When I do an exact search for R12-5-E2
    Then I am taken to the product page for ST Robotics R12-5-E2
    When I add to basket from the product page
    Then I get visual confirmation that 1 product has been successfully added
    When I navigate to my basket
    Then product ST Robotics R12-5-E2 should be in the basket

  @run
  Scenario: Search for product using keyword and result page filters
    Given that I am on the homepage
    When I do a general search for Detector Switches
    And I filter by Brand for TE Connectivity
    Then my results list is limited to products for TE Connectivity
    When I add to basket from the results page
    Then I get visual confirmation that 1 product has been successfully added
    When I navigate to my basket
    Then product TE Connectivity Tact Switch should be in the basket