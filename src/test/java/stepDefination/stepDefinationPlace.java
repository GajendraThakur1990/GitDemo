package stepDefination;

import POJO.AddApiPayload;
import POJO.Location;
import Resources.EnumResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class stepDefinationPlace extends Utils {
    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild  testDataBuild = new TestDataBuild();
    static String place_id;

    @Given("I have a valid place payload with {string} {string} {string})")
    public void i_have_a_valid_place_payload_with(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification()).body(testDataBuild.addPlacePayload(name, language, address  ));

    }


    @When("I send a {string} request to the {string}")
    public void i_send_a_request_to_the(String method, String resource) {

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        EnumResources enumResources=EnumResources.valueOf(resource);
        System.out.println(enumResources.getResource());

        if (method.equalsIgnoreCase("POST")) {
            response = res.when().post(enumResources.getResource());
        }
        else if (method.equalsIgnoreCase("GET"))
            response = res.when().get(enumResources.getResource());
    }
    @Then("I should receive a {int} OK response")
    public void i_should_receive_a_ok_response(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(200,response.getStatusCode());

    }
    @Then("{string} in the response is {string}")
    public void in_the_response_is(String key, String ExpectedValue) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(ExpectedValue,getJsonPath(response,key));
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        // Prepare requestSpect
        place_id= getJsonPath(response,"place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        i_send_a_request_to_the("GET", resource);
        String actualName= getJsonPath(response,"name");
        assertEquals(expectedName,actualName);
    }


    @Given("DeletePlaceAPI Payload")
    public void delete_place_api_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res = given().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(place_id));

    }

}
