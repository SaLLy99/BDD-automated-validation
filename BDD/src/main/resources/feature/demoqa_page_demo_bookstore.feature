Feature: DemoQa Check BookStore
  Scenario: Check book store
    Given home page is open
    Then click on Go to Book store button
    Then make sure that we have enough  books in book store
    Then return back to home page