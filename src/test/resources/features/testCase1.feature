Feature: Test case 1

Scenario: Assert price with coupon
    Given the user is on the cart page with an item in the cart
    When the user clicks apply coupon with a valid code
    Then total should equal subtotal minus discount plus shipping



