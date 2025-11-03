Feature: Test case 2

Scenario: Check order numbers match
    Given the user is on the checkout page
    When the user enters valid log in credentials
    And the user clicks dismiss button
    And the user navigates to the shop
    And the user clicks add to cart button
    And user navigates to checkout
    And the user completes checkout form
    Then order number in checkout should match order number in previous orders
