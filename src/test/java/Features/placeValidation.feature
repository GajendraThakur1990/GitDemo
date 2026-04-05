Feature: Validate place
  @AddPlace @Regression
  Scenario Outline: Validate place is being added using AddPlaceAPI

    Given I have a valid place payload with "<name>" "<language>" "<address>")
    When I send a "POST" request to the "addPlaceAPI"
    Then I should receive a 200 OK response
    And "status" in the response is "OK"
    And "scope" in the response is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name   | language | address |
      | Place1 | English  | Street  |

  @DeletePlace @Regression
  Scenario: Verify if DeletePlaceAPI functionality is working

    Given DeletePlaceAPI Payload
    When I send a "POST" request to the "deletePlaceAPI"
    Then I should receive a 200 OK response
    And "status" in the response is "OK"
