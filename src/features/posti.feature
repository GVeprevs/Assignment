Feature: posti

  @positive @ui
  Scenario: Check functionality of Service points page
    Given user navigates to "/service-points-on-map"
    Then user accepts all cookies
    When user enters a street address and zip code to find nearest pickup points
    And user should see dynamic view of highlighted service point and nearest point address "Kalevalantie 3, 02135 ESPOO"
    Then user unselect pickup points and parcel lockers, to have only Postal outlets
    And user should see dynamic view of highlighted service point and nearest point address "Ahventie 2, 02170 ESPOO"
    Then user fetches his own location to check nearest service point and validates the action
    Then user checks that he can zoom in & out the map
    Then users that he can open map in full screen


  @positive @ui
  Scenario: Check functionality of Parcels and tracking page
    Given user navigates to "/private/letters-and-mail"
    Then user accepts all cookies
    When user inputs tracking number and searches for info about his shipment
    Then user checks and validates notice text that shipment is not found
    Then user clicks a button and navigates to "/letters-and-mail"
    And press on button to send a letter
    And user validates that he can send letters & postcards without login
    Then user press on button to calculate the letter sending price and navigates to "/private/letters-and-mail/send-a-letter-or-postcard/letter-price-calculator"
    And user chooses letter details to calculate accurate price and press button to check results
    Then user validates that results are present after calculation

