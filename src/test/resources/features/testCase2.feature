Feature: Test case 2

Scenario: Check order numbers match
    Given the user is on the cart page with an item in the cart
    When the user navigates to checkout
    And places an order
    Then order number in checkout should match order number in previous orders
