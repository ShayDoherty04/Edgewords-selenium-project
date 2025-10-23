Feature: Test case 1

Scenario: Assert price with coupon
    Given the user is on the login page
    When the user enters valid log in credentials
    And the user navigates to the shop
    And the user clicks add to cart button
    And the user enters the discount code
    Then total should equal subtotal minus discount plus shipping
    When user clicks logout


